package edu.csumb.project1_cst438;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateAssignmentActivity extends AppCompatActivity {

    private static final String TAG = "CreateAssignmentAct";

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");

    EditText mTitle;
    EditText mDateAssigned;
    EditText mDueDate;
    EditText mDueTime;
    EditText mDescription;
    EditText mPossibleScore;
    Spinner mCategory;

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
        mCategory = (Spinner) findViewById(R.id.category_spn);

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
                Log.i(TAG, "addAssignment button has ran");
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
        //String category = mCategory.getSelectedItem().toString();

        Date dateAssigned = generateDateFromString(mDateAssigned.getText().toString());
        Date dueDate = generateDateFromString(mDueDate.getText().toString());

        Time dueTime = generateTimeFromString(mDueTime.getText().toString());
        Float possibleScore = Float.parseFloat(mPossibleScore.getText().toString());
        Log.i(TAG, "grabAssignmentData has grabbed all relevant information from user");

        Assignment assignment = new Assignment(title, dateAssigned, dueDate, dueTime,
                description, possibleScore);
        Log.i(TAG, "new assignment made from user input");
        Log.i(TAG, assignment.toString());

        return assignment;
        // TODO: add room code here...
    }

    // TODO: may need to make changes here to prevent problems should the date be invalid
    public Date generateDateFromString(String date){
        Date dateFound = null; // is this the best way to handle it?
        try{
            dateFound = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateFound;
    }

    public Time generateTimeFromString(String time) {
        Time timeFound = null; // is this the best way to handle it?
        try{
            timeFound = (Time) timeFormat.parse(time);
            System.out.println(timeFound);
            Log.i(TAG, "The information should be just above");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "The information should be just above");
        return timeFound;
    }

}
