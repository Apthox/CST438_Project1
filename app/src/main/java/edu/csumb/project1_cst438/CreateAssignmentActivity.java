package edu.csumb.project1_cst438;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateAssignmentActivity extends AppCompatActivity {

    private static final String TAG = "CreateAssignmentAct";

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat dateAndTimeFormat =
            new SimpleDateFormat("dd/MM/yyyy HH:mm");

    EditText mTitle;
    EditText mDateAssigned;
    EditText mDueDate;
    EditText mDueTime;
    EditText mDescription;
    EditText mPossibleScore;

    Button mAssignToToday;
    Button mAddAssignment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_assignment);

        mTitle = (EditText) findViewById(R.id.title_et);
        mDateAssigned = (EditText) findViewById(R.id.date_assigned_et);
        mDueDate = (EditText) findViewById(R.id.due_date_et);
        mDueTime = (EditText) findViewById(R.id.due_time_et);
        mDescription = (EditText) findViewById(R.id.description_et);
        mPossibleScore = (EditText) findViewById(R.id.possible_score_et);

        mAssignToToday = findViewById(R.id.today_btn);
        mAddAssignment = findViewById(R.id.add_assignment_btn);

        // TO DO: add Room functionality here

        mAssignToToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putTodayDateString();
                // refreshDisplay(); room stuff... maybe? most likely in the "add assign btn"
            }
        });

        mAddAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gather the information of the text fields
                System.out.println(grabAssignmentsData());
            }
        });
    }

    public void putTodayDateString() {
        String todaysDate = dateFormat.format(new Date());
        mDateAssigned.setText(todaysDate, TextView.BufferType.EDITABLE);
    }

    public Assignment grabAssignmentsData() {
        String title = mTitle.getText().toString();
        String description = mDescription.getText().toString();
        // currently empty due to no db connected

        Date dateAssigned = generateDateFromString(mDateAssigned.getText().toString());
        Date dueDateAndTime = generateDateFromString(
                mDueDate.getText().toString(), mDueTime.getText().toString());
        Float possibleScore = Float.parseFloat(mPossibleScore.getText().toString());

        Assignment assignment = new Assignment(title, dateAssigned, dueDateAndTime,
                description, possibleScore);

        // TODO: add room code here...
        return assignment;
    }

    // TODO: question to whoever finds this. Should we keep this implementation?
    // 2 functions that do almost the same, also is the way they handle this adequate
    public Date generateDateFromString(String date){
        Date dateFound = null; // is this the best way to handle it?
        try{
            dateFound = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateFound;
    }

    public Date generateDateFromString(String date, String time){
        Date dateFound = null; // is this the best way to handle it?
        try{
            dateFound = dateAndTimeFormat.parse(date + " " + time);
            Log.i(TAG, dateFound.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateFound;
    }
}
