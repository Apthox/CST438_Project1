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
    private Integer selectedCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__detailed__course__editable);

        Bundle bundle = getIntent().getExtras();

        mDeleteButton = findViewById(R.id.button_delete_course);
        mEditButton = findViewById(R.id.button_edit_course);
        mDisplay = findViewById(R.id.selectedDisplay);
        mCourseDao = Room.databaseBuilder(this,AppRoom.class, AppRoom.COURSE_TABLE)
                .allowMainThreadQueries()
                .build()
                .getCourseDao();
        //find the course in db
        if (bundle!=null){
            selectedCourse = bundle.getInt("selectedCourse");
            Course temp = mCourseDao.getCourseByID(selectedCourse);
            mDisplay.setText(temp.toString());
        }

        final ACT_Detailed_Course_Editable instance = this;

        Button view_button = findViewById(R.id.ViewButton);

        view_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(instance, ACT_Initial_Category_Display.class);
                startActivity(intent);
            }
        });

    }

    public void onDelete(View view){
        new AlertDialog.Builder(this)
                .setTitle("Delete Entry")
                .setMessage("Are you certain you'd like to delete this course?")

                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //where to do when deletion occurs
                        mCourseDao.delete(mCourseDao.getCourseByID(selectedCourse));
                        Intent intent = new Intent(ACT_Detailed_Course_Editable.this,ACT_Initial_Course_Display.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(android.R.string.no,null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }
    public void onEdit(View view){
        //send id in bundle

        Intent intent = new Intent(this,ACT_Edit_Course.class);
        intent.putExtra("refID", selectedCourse);
        startActivity(intent);


    }
}
