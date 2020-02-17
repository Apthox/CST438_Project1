package edu.csumb.project1_cst438.Model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import edu.csumb.project1_cst438.InputFilterMinMaxId;
import edu.csumb.project1_cst438.R;

public class ACT_Add_Course extends AppCompatActivity {
    TextView mCalenderPop, mEnd;
    DatePickerDialog.OnDateSetListener mDatePick;
    DatePickerDialog.OnDateSetListener mDatePick2;
    EditText mTitle, mStart,mInstructor, mID, mDescription;
    Button mSubmit;
    CourseDao mCourseDao;
    DateFormat formatter = new SimpleDateFormat("yyyy,MM,dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_act_add);

        mCalenderPop = findViewById(R.id.date_start);
        mDescription = findViewById(R.id.courseDescription);
        mEnd = findViewById(R.id.date_end);
        //mEnd.addTextChangedListener(fmat);
        mID = findViewById(R.id.courseID);
        mID.setFilters(new InputFilter[]{new InputFilterMinMaxId("0","999")});
        //mStart = findViewById(R.id.courseStartDate);
        //mStart.addTextChangedListener(fmat);
        mInstructor = findViewById(R.id.courseInstructor);
        mTitle = findViewById(R.id.courseTitleEntry);
        mSubmit = findViewById(R.id.button_submit);

        mCalenderPop.setOnClickListener(generateDateListener(mCalenderPop));
        mEnd.setOnClickListener(generateDateListener(mEnd));

        mCourseDao = Room.databaseBuilder(this,AppDatabase.class,AppDatabase.COURSE_TABLE)
                .allowMainThreadQueries()
                .build()
                .getCourseDao();


//        mCalenderPop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar cal = Calendar.getInstance();
//                int year = cal.get(Calendar.YEAR);
//                int month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DAY_OF_MONTH);
//                DatePickerDialog dateDialog = new DatePickerDialog(
//                        ACT_Add_Course.this,
//                        android.R.style.Theme_Holo_Dialog_MinWidth,
//                        mDatePick,
//                        year,month,day);
//                dateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dateDialog.show();
//
//            }
//        });
//        mEnd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar cal2 = Calendar.getInstance();
//                int year2 = cal2.get(Calendar.YEAR);
//                int month2 = cal2.get(Calendar.MONTH);
//                int day2 = cal2.get(Calendar.DAY_OF_MONTH);
//                DatePickerDialog dateDialog2 = new DatePickerDialog(
//                        ACT_Add_Course.this,
//                        android.R.style.Theme_Holo_Dialog_MinWidth,
//                        mDatePick,
//                        year2,month2,day2);
//                dateDialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dateDialog2.show();
//
//            }
//        });
        mDatePick = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d("Ruh-roh", "onDateSet: yyyy/mm/dd" + year + '/' + month + '/' + day);
                String date = year + "/" + month + "/" + day;

                mCalenderPop.setText(date);

            }
        };
        mDatePick2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d("Ruh-roh", "onDateSet: yyyy/mm/dd" + year + '/' + month + '/' + day);
                String date = year + "/" + month + "/" + day;

                mEnd.setText(date);

            }
        };
    }
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
                                // for whatever reason the system stores months 0-11, thus monthOfYear + 1
                                target.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        };
        return dateListener;
    }
    //redo this section, no longer relevant
    public void onSubmit(View view){
        Date sDate = new Date(0000,0,0);
        Date eDate = new Date(0000,0,0);
        try {
            sDate = formatter.parse(mStart.getText().toString());
            eDate = formatter.parse(mEnd.getText().toString());

        }catch (java.text.ParseException e){
            e.printStackTrace();
        }

        Course course = new Course(mTitle.getText().toString(),mInstructor.getText().toString(),mDescription.getText().toString(),sDate,eDate,mID.getId());
        mCourseDao.insert(course);
//        Toast toast = Toast.makeText(this, "Stored", Toast.LENGTH_SHORT);
//        toast.show();
        Intent back = new Intent(this,ACT_Initial_Course_Display.class);
        startActivity(back);

    }
//    TextWatcher fmat = new TextWatcher() {
//            private String current = "";
//            private String yyyyMMdd = "YYYYMMDD";
//            private Calendar calendar = Calendar.getInstance();
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                //nope
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (!s.toString().equals(current)){
//                    String clean = s.toString().replaceAll("[^\\d.]|\\.","");
//                    String cleanCurrent = current.replaceAll("[^\\d.]|\\.","");
//                    int clean2 = clean.length();
//                    int select = clean2;
//                    for (int i = 2; i <= clean2&&i < 6;i += 2){
//                        select++;
//                    }
//                    if (clean.equals(cleanCurrent)){
//                        select--;
//                    }
//                    if (clean.length() < 8){
//                        clean = clean + yyyyMMdd.substring(clean.length());
//                    }else{
//                        int day = Integer.parseInt(clean.substring(6,8));
//                        int month = Integer.parseInt(clean.substring(4,6));
//                        int year = Integer.parseInt(clean.substring(0,4));
//
//                        month = month < 1 ? 1 : month > 12 ? 12 : month;
//                        calendar.set(Calendar.MONTH,month-1);
//                        year = (year<1900)?1900:(year>2100)?2100:year;
//                        calendar.set(Calendar.YEAR,year);
//                        day = (day>calendar.getActualMaximum(Calendar.DATE))? calendar.getActualMaximum(Calendar.DATE):day;
//                        clean = String.format("%02d%02d%02d",year,month,day);
//                    }
//                    clean = String.format("%s/%s/%s", clean.substring(0,4), clean.substring(4,6), clean.substring(6,8));
//                    select = select<0?0:select;
//                    current = clean;
//                    mStart.setText(current);
//                    mStart.setSelection(select<current.length()?select:current.length());
//                    mEnd.setText(current);
//                    mEnd.setSelection(select<current.length()?select:current.length());
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        };

}
