package edu.csumb.project1_cst438.Model;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import edu.csumb.project1_cst438.R;

public class ACT_Detailed_Course_Editable extends AppCompatActivity {
    TextView mDisplay;
    CourseDao mCourseDao;
    Button mEditButton,mDeleteButton;
    private String selectedCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__detailed__course__editable);

        Bundle bundle = getIntent().getExtras();


        mDisplay = findViewById(R.id.selectedDisplay);
        mCourseDao = Room.databaseBuilder(this,AppDatabase.class,AppDatabase.COURSE_TABLE)
                .allowMainThreadQueries()
                .build()
                .getCourseDao();
        //find the course in db
        if (bundle!=null){
            selectedCourse = bundle.getString("selectedCourse");
            Course temp = mCourseDao.getCourseByTitle(selectedCourse);

        mDisplay.setText(temp.toString());
        }

    }

    public void onDelete(View view){
        new AlertDialog.Builder(this)
                .setTitle("Delete Entry")
                .setMessage("Are you certain you'd like to delete this course?")

                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //where to do when deletion occurs
                        //Toast.makeText(ACT_Detailed_Course_Editable.this, "yay", Toast.LENGTH_SHORT).show();
                        mCourseDao.delete(mCourseDao.getCourseByTitle(selectedCourse));
                        Intent intent = new Intent(ACT_Detailed_Course_Editable.this,ACT_Initial_Course_Display.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(android.R.string.no,null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }
    public void onEdit(View view){

    }
}
