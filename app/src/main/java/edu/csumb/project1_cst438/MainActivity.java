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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent = new Intent(this, ACT_Initial_Course_Display.class);
//        startActivity(intent);


    }
}
