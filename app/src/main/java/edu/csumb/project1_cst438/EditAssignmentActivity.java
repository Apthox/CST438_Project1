package edu.csumb.project1_cst438;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import edu.csumb.project1_cst438.Model.AppRoom;
import edu.csumb.project1_cst438.Model.Assignment;
import edu.csumb.project1_cst438.Model.AssignmentDao;

public class EditAssignmentActivity extends AppCompatActivity {

    private static final String TAG = "EditAssignmentAct";

    EditText mTitle;
    EditText mDateAssigned;
    EditText mDueDate;
    EditText mDueTime;
    EditText mDescription;
    EditText mPossibleScore;

    Button mUpdateAssignmentBtn;
    Button mCancelAssignmentCreation;

    AssignmentDao mAssignmentDao;
    Assignment mCurrentAssignment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assignment);

        mTitle = (EditText) findViewById(R.id.title_et);
        mDateAssigned = (EditText) findViewById(R.id.date_assigned_et);
        mDateAssigned.setInputType(InputType.TYPE_NULL);

        mDueDate = (EditText) findViewById(R.id.due_date_et);
        mDueDate.setInputType(InputType.TYPE_NULL);

        mDueTime = (EditText) findViewById(R.id.due_time_et);
        mDueTime.setInputType(InputType.TYPE_NULL);

        mDescription = (EditText) findViewById(R.id.description_et);
        mPossibleScore = (EditText) findViewById(R.id.possible_score_et);

        mUpdateAssignmentBtn = findViewById(R.id.update_assignment_btn);
        mCancelAssignmentCreation = (Button) findViewById(R.id.cancel_btn);

        mAssignmentDao = Room.databaseBuilder(this, AppRoom.class, AppRoom.dbName)
                .allowMainThreadQueries()
                .build()
                .assignmentDao();

        // retrieve the assignment set from the previous activity
        mCurrentAssignment = retrieveAssignment();

        // populate the activity with the information from the assignment passed
        populateActivity();

        // date picker for date assigned
        mDateAssigned.setOnClickListener(generateDateListener(mDateAssigned));

        // date picker for date due
        mDueDate.setOnClickListener(generateDateListener(mDueDate));

        // time picker for time due
        mDueTime.setOnClickListener(generateTimeListener(mDueTime));

        // listener for button to grab info, make an object and send to db
        mUpdateAssignmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gather the information, update assignment locally and update the roomdb
                updateAssignmentInformation();
                mAssignmentDao.update(mCurrentAssignment);
                // toast to inform user of the action
                Toast.makeText(getApplicationContext(), "Assignment updated", Toast.LENGTH_LONG).show();
                finish(); // return to the previous activity to continue working
            }
        });

        // listener for button to cancel and return to previous activity
        mCancelAssignmentCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private View.OnClickListener generateDateListener(final EditText target) {
        View.OnClickListener dateListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                // date picker dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditAssignmentActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // for whatever reason the system stores months 0-11, thus monthOfYear + 1
                                target.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        };
        return dateListener;
    }

    private View.OnClickListener generateTimeListener(final EditText target) {
        View.OnClickListener timeListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int mHour = cldr.get(Calendar.HOUR_OF_DAY);
                int mMinute = cldr.get(Calendar.MINUTE);

                // time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(EditAssignmentActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                target.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        };
        return timeListener;
    }

    // retrieves the information given by the user and updates the assignment passed to this activity
    private void updateAssignmentInformation() {
        mCurrentAssignment.setTitle(mTitle.getText().toString());
        mCurrentAssignment.setDescription(mDescription.getText().toString());
        mCurrentAssignment.setDateAssignedFromString(mDateAssigned.getText().toString());
        mCurrentAssignment.setDueDateFromString(mDueDate.getText().toString());
        mCurrentAssignment.setDueTimeFromString(mDueTime.getText().toString());
        mCurrentAssignment.setPossibleScore(Float.parseFloat(mPossibleScore.getText().toString()));
    }

    private void populateActivity() {
        mTitle.setText(mCurrentAssignment.getTitle());
        mDateAssigned.setText(mCurrentAssignment.getStringDateAssigned());
        mDueDate.setText(mCurrentAssignment.getStringDueDate());
        mDueTime.setText(mCurrentAssignment.getStringDueTime());
        mDescription.setText(mCurrentAssignment.getDescription());
        mPossibleScore.setText(String.valueOf(mCurrentAssignment.getPossibleScore()));
    }

    private Assignment retrieveAssignment () {
        return (Assignment) getIntent().getSerializableExtra("Assignment");

    }
}
