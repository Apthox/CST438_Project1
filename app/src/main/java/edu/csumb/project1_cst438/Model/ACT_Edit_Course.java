package edu.csumb.project1_cst438.Model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import edu.csumb.project1_cst438.R;

public class ACT_Edit_Course extends AppCompatActivity {
    TextView mStart, mEnd;
    EditText mTitle, mInstructor, mID, mDescription;
    private Integer refId;
    boolean taken;
    CourseDao mCourseDao;
    List<Course>mCourseList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__edit__course);
        mTitle = findViewById(R.id.editCourseTitle);
        mInstructor = findViewById(R.id.editCourseInstructor);
        mDescription = findViewById(R.id.editCourseDesc);
        mStart = findViewById(R.id.editStart);
        mEnd = findViewById(R.id.editId);
        mID = findViewById(R.id.editId);
        mStart.setOnClickListener(generateDateListener(mStart));
        mEnd.setOnClickListener(generateDateListener(mEnd));
        Bundle b = getIntent().getExtras();
        refId = b.getInt("refID");
        taken = false;
        mCourseDao = Room.databaseBuilder(this,AppDatabase.class,AppDatabase.COURSE_TABLE)
                .allowMainThreadQueries()
                .build()
                .getCourseDao();
        mCourseList = mCourseDao.getAllCourses();

    }

    public void onEdit(View view){
        //logic
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
            String mTmpTitle = mTitle.getText().toString();
            String mTmpInstructor = mInstructor.getText().toString();
            String mTmpDescription = mDescription.getText().toString();
            String mTmpStartDate = mStart.getText().toString();
            String mTmpEndDate = mEnd.getText().toString();
            Integer mTmpCourseID = Integer.parseInt(mID.getText().toString());
            //temporarily remove the currently being edited course
            Course editted = new Course(mTmpTitle, mTmpInstructor, mTmpDescription, mTmpStartDate, mTmpEndDate, mTmpCourseID);
            Course holdingCell = mCourseDao.getCourseByID(refId);
            mCourseDao.delete(mCourseDao.getCourseByID(refId));
            for (Course c : mCourseList) {
                if (c.getCourseID().equals(mTmpCourseID)) {
                    taken = true;
                }
            }
            //actual action of button
            if (taken) {
                new AlertDialog.Builder(this)
                        .setTitle("CourseIdError")
                        .setMessage("This Course ID is Taken, Please Change It")
                        .setPositiveButton(android.R.string.yes, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                mCourseDao.insert(holdingCell);
            } else {
                mCourseDao.insert(editted);
            }
        }

    }
    private View.OnClickListener generateDateListener(final TextView target){
        View.OnClickListener dateListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(ACT_Edit_Course.this,
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
}
