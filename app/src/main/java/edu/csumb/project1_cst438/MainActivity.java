package edu.csumb.project1_cst438;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

import edu.csumb.project1_cst438.Model.ACT_Initial_Course_Display;
import edu.csumb.project1_cst438.Model.AppDatabase;
import edu.csumb.project1_cst438.Model.Course;
import edu.csumb.project1_cst438.Model.CourseDao;

public class MainActivity extends AppCompatActivity {
    CourseDao mCourseDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Log.d("here", "onCreate: hello");
        Intent intent = new Intent(this, ACT_Initial_Course_Display.class);
        startActivity(intent);

        mCourseDao = Room.databaseBuilder(this, AppDatabase.class,AppDatabase.COURSE_TABLE)
                .allowMainThreadQueries()
                .build()
                .getCourseDao();
        Course testA = new Course("Math","A","A",new Date(2020/2/11),new Date(2020/2/11),798);
        Course testB = new Course("English","A","A",new Date(2020/2/11),new Date(2020/2/11),998);
        Course testC = new Course("Science","A","A",new Date(2020/2/11),new Date(2020/2/11),456);
        mCourseDao.insert(testA);
        mCourseDao.insert(testB);
        mCourseDao.insert(testC);
    }
}
