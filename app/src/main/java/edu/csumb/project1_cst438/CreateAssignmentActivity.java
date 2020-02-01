package edu.csumb.project1_cst438;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
                // refreshDisplay(); room stuff...
            }
        });

        mAddAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gather the information of the text fields
            }
        });
    }

    public void putTodayDateString() {
        String todaysDate = dateFormat.format(new Date());
        mDateAssigned.setText(todaysDate, TextView.BufferType.EDITABLE);
    }

    public void grabAssignmentsData() {
        String title = mTitle.getText().toString();
        String description = mDescription.getText().toString();
        String category = mCategory.getSelectedItem().toString();

        Date dateAssigned = generateDateFromString(mDateAssigned.getText().toString());
        Date dueDate = generateDateFromString(mDueDate.getText().toString());

        Time dueTime = generateTimeFromString(mDueTime.getText().toString());
        Float possibleScore = Float.parseFloat(mPossibleScore.getText().toString());

        Assignment assignment = new Assignment(title, dateAssigned, dueDate, dueTime,
                description, possibleScore, category);

        // TO DO: add room code here...
    }

    // TO DO: may need to make changes here to prevent problems should the date be invalid
    public Date generateDateFromString(String date){
        try{
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // what should return here?...
    }

    public Time generateTimeFromString(String time) {
        try{
            return (Time) timeFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // what should return here?...
    }

}
