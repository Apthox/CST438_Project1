package edu.csumb.project1_cst438;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.csumb.project1_cst438.Model.AppRoom;
import edu.csumb.project1_cst438.Model.Assignment;
import edu.csumb.project1_cst438.Model.AssignmentDao;

public class DisplayAssignmentInfo extends AppCompatActivity {

    TextView mTitle;
    TextView mDateAssigned;
    TextView mDueDate;
    TextView mDueTime;
    TextView mDescription;
    TextView mScoreEarned;
    TextView mPossibleScore;

    Assignment mCurrentAssignment;

    Button mSubmitBtn;
    Button mEditBtn;
    Button mDeleteBtn;
    Button mCancelBtn;

    AssignmentDao mAssignmentDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_assignment_info);

        mTitle = (TextView) findViewById(R.id.assignment_title_tv);
        mDateAssigned = (TextView) findViewById(R.id.assignment_date_assigned_tv);
        mDueDate = (TextView) findViewById(R.id.assignment_due_date_tv);
        mDueTime = (TextView) findViewById(R.id.assignment_due_time_tv);
        mDescription = (TextView) findViewById(R.id.assignment_description_tv);
        mScoreEarned = (EditText) findViewById(R.id.assignment_score_earned_et);
        mPossibleScore = (TextView) findViewById(R.id.assignment_max_score_tv);

        // receive the incoming assignment and display it
        mCurrentAssignment = getIncomingAssignment();

        mAssignmentDao = Room.databaseBuilder(this, AppRoom.class, AppRoom.dbName)
                .allowMainThreadQueries()
                .build()
                .assignmentDao();

        mSubmitBtn = (Button) findViewById(R.id.submit_assignment_btn);
        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mScoreEarned.getText().toString().replaceAll("\\s+","") != "") {
                    // grab user input
                    String userScore = mScoreEarned.getText().toString();
                    // update local assignment
                    mCurrentAssignment.setScoreEarned(Float.parseFloat(userScore));
                    // update the roomdb with user input
                    mAssignmentDao.update(mCurrentAssignment);
                    // inform user of the action
                    Toast.makeText(getApplicationContext(), "Assignment score has been updated", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Sorry, no score found", Toast.LENGTH_LONG).show();
                }
            }
        });

        // buttons here for cancel, edit, and delete
        mCancelBtn = (Button) findViewById(R.id.cancel_btn);
        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mEditBtn = (Button) findViewById(R.id.edit_assignment_btn);
        mEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // take user to updateAssignmentActivity
                Intent intent = new Intent(DisplayAssignmentInfo.this, EditAssignmentActivity.class);
                intent.putExtra("Assignment", mCurrentAssignment);
                startActivity(intent);
            }
        });

        mDeleteBtn = (Button) findViewById(R.id.delete_assignment_btn);
        mDeleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // inform user of the action
                Toast.makeText(getApplicationContext(), "Assignment Deleted", Toast.LENGTH_LONG).show();
                // call delete this assignment from the roomdb
                mAssignmentDao.delete(mCurrentAssignment);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // find assignment id
        int currentAssignmentId = mCurrentAssignment.getAssignmentId();
        // retrieve assignment form roomdb
        mCurrentAssignment =  mAssignmentDao.getAssignmentFromId(currentAssignmentId);
        // re enter the information for the assignment into the
        populateDisplay(mCurrentAssignment);
    }

    private void populateDisplay(Assignment assignment) {
        // call this upon loading this activity
        mTitle.setText(assignment.getTitle());
        mDateAssigned.setText(assignment.getStringDateAssigned());
        mDueDate.setText(assignment.getStringDueDate());
        mDueTime.setText(assignment.getStringDueTime());
        mDescription.setText(assignment.getDescription());
        mPossibleScore.setText(String.valueOf(assignment.getPossibleScore()));

        // check that assignment earned score is set, if yes show it
        if(assignment.getScoreEarned() != -1) {
            mScoreEarned.setText(String.valueOf(assignment.getScoreEarned()));
        }
    }

    private Assignment getIncomingAssignment() {
        Assignment assignmentFound = null;
        if(getIntent().hasExtra("Assignment")) {
            Assignment assignment = (Assignment) getIntent().getSerializableExtra("Assignment");
            populateDisplay(assignment);
            assignmentFound = assignment;
        }
        // here a null may be returned
        // this will be addressed by removing the item from multiAssignmentDisplay
        // therefore returning null should never happen
        return assignmentFound;
    }
}
