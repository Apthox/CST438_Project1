package edu.csumb.project1_cst438.Model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import edu.csumb.project1_cst438.InputFilterMinMaxId;
import edu.csumb.project1_cst438.MainActivity;
import edu.csumb.project1_cst438.R;

public class ACT_Add_Course extends AppCompatActivity {
    TextView mStart, mEnd;
    EditText mTitle, mInstructor, mID, mDescription;
    Button mDone, mAdd;
    CourseDao mCourseDao;
    boolean taken;
    //DateFormat formatter = new SimpleDateFormat("yyyy,MM,dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_act_add);

        mStart = findViewById(R.id.date_start);
        mDescription = findViewById(R.id.courseDescription);
        mEnd = findViewById(R.id.date_end);
        mAdd = findViewById(R.id.button2);
        mID = findViewById(R.id.courseID);
        mID.setFilters(new InputFilter[]{new InputFilterMinMaxId("0","999")});

        mInstructor = findViewById(R.id.courseInstructor);
        mTitle = findViewById(R.id.courseTitleEntry);
        mDone = findViewById(R.id.button_submit);
        taken=false;
        mStart.setOnClickListener(generateDateListener(mStart));
        mEnd.setOnClickListener(generateDateListener(mEnd));

        mCourseDao = Room.databaseBuilder(this,AppRoom.class,AppRoom.COURSE_TABLE)
                .allowMainThreadQueries()
                .build()
                .getCourseDao();

    }
    /*
    This is the multi-use calender pop up, also where date will be pulled from
     */
    private View.OnClickListener generateDateListener(final TextView target){
        View.OnClickListener dateListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(ACT_Add_Course.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                target.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        };
        return dateListener;
    }
    /*
    This is what will add the course to the db. All logic for each fields entry conditions
    are also here.
     */
    public void onAdd(View view){

        //Logic portion

        //Test if issues occur, including blanks
        if (mID.getText().toString().equals("")||mTitle.getText().toString().equals("")||mInstructor.getText().toString().equals("")||mDescription.getText().toString().equals("")||mStart.getText().toString().equals("")||mEnd.getText().toString().equals("")){
            //a blank was entered, dialog confirm
            new AlertDialog.Builder(this)
                    .setTitle("Empty Entry Detected")
                    .setMessage("One or More Entries Were Left Blank, Fix That")
                    .setPositiveButton(android.R.string.yes,null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }else {
            //Retrieve all values
        String mTmpTitle = mTitle.getText().toString();
        String mTmpInstructor = mInstructor.getText().toString();
        String mTmpDescription = mDescription.getText().toString();
        String mTmpStartDate = mStart.getText().toString();
        String mTmpEndDate = mEnd.getText().toString();
        Integer mTmpCourseID = Integer.parseInt(mID.getText().toString());
            List<Course> mCourseList = mCourseDao.getAllCourses();

            //Checking ID matches
            for (Course c : mCourseList) {
                if (c.getCourseID().equals(mTmpCourseID)) {
                    taken = true;
                }
            }
            if (taken) {
                new AlertDialog.Builder(this)
                        .setTitle("CourseIdError")
                        .setMessage("This Course ID is Taken, Please Change It")
                        .setPositiveButton(android.R.string.yes, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            } else if (!taken) {
                //Insert Portion
                //title,instructor,description,start date, end date,courseID
                Course course = new Course(mTmpTitle, mTmpInstructor, mTmpDescription, mTmpStartDate, mTmpEndDate, mTmpCourseID, MainActivity.uid);
                mCourseDao.insert(course);
                //clear screen???
                mTitle.getText().clear();
                mInstructor.getText().clear();
                mDescription.getText().clear();
                mID.getText().clear();
            }
        }


    }
    /*
    This is the return button or like "im done entering stuff button"
     */
    public void onDone(View view){

        Intent back = new Intent(this,ACT_Initial_Course_Display.class);
        startActivity(back);

    }


}
