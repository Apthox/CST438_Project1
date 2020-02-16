package edu.csumb.project1_cst438;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayAssignmentInfo extends AppCompatActivity {

    TextView mTitle;
    TextView mDateAssigned;
    TextView mDueDate;
    TextView mDueTime;
    TextView mDescription;
    TextView mScoreEarned;
    TextView mPossibleScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_assignment_info);

        mTitle = (TextView) findViewById(R.id.assignment_title_tv);
        mDateAssigned = (TextView) findViewById(R.id.assignment_date_assigned_tv);
        mDueDate = (TextView) findViewById(R.id.assignment_due_date_tv);
        mDueTime = (TextView) findViewById(R.id.assignment_due_time_tv);
        mDescription = (TextView) findViewById(R.id.assignment_description_tv);
        mScoreEarned = (TextView) findViewById(R.id.assignment_score_earned_tv);
        mPossibleScore = (TextView) findViewById(R.id.assignment_max_score_tv);

        // buttons here for edit, and delete

    }

    private void populateDisplay(Assignment assignment) {
        // call this upon loading this activity
        mTitle.setText(assignment.getTitle());
        mDateAssigned.setText(assignment.getStringDateAssigned());
        mDueDate.setText(assignment.getStringDueDate());
        mDueTime.setText(assignment.getStringDueTime());
        mDescription.setText(assignment.getDescription());
        mPossibleScore.setText(String.valueOf(assignment.getPossibleScore()));
    }
}
