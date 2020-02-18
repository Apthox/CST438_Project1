package edu.csumb.project1_cst438;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateAssignmentActivity extends AppCompatActivity {

    private static final String TAG = "CreateAssignmentAct";

    EditText mTitle;
    EditText mDateAssigned;
    EditText mDueDate;
    EditText mDueTime;
    EditText mDescription;
    EditText mPossibleScore;

    Button mAddAssignment;
    Button mCancelAssignmentCreation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_assignment);

        mTitle = (EditText) findViewById(R.id.title_et);
        mDateAssigned = (EditText) findViewById(R.id.date_assigned_et);
        mDateAssigned.setInputType(InputType.TYPE_NULL);

        mDueDate = (EditText) findViewById(R.id.due_date_et);
        mDueDate.setInputType(InputType.TYPE_NULL);

        mDueTime = (EditText) findViewById(R.id.due_time_et);
        mDueTime.setInputType(InputType.TYPE_NULL);

        mDescription = (EditText) findViewById(R.id.description_et);
        mPossibleScore = (EditText) findViewById(R.id.possible_score_et);

        mAddAssignment = findViewById(R.id.add_assignment_btn);
        mCancelAssignmentCreation = findViewById(R.id.cancel_btn);

        // TODO: add Room functionality here

        // date picker for date assigned
        mDateAssigned.setOnClickListener(generateDateListener(mDateAssigned));

        // date picker for date due
        mDueDate.setOnClickListener(generateDateListener(mDueDate));

        // time picker for time due
        mDueTime.setOnClickListener(generateTimeListener(mDueTime));

        // listener for button to grab info, make an object and send to db
        mAddAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gather the information of the text fields
                System.out.println(retrieveAssignmentWithData()); // this is for testing
                // grab all the data and make an object
                // communicate with the db to upload object
                // move up one level
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateAssignmentActivity.this,
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
                TimePickerDialog timePickerDialog = new TimePickerDialog(CreateAssignmentActivity.this,
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

    private Assignment retrieveAssignmentWithData() {
        String title = mTitle.getText().toString();
        String description = mDescription.getText().toString();
        String dateAssigned = mDateAssigned.getText().toString();
        String dueDate = mDueDate.getText().toString();
        String dueTime = mDueTime.getText().toString();

        Float possibleScore = Float.parseFloat(mPossibleScore.getText().toString());

        Assignment assignment = new Assignment(title, dateAssigned, dueDate, dueTime,
                description, possibleScore);

        // TODO: add room code here... ?
        return assignment;
    }
}
