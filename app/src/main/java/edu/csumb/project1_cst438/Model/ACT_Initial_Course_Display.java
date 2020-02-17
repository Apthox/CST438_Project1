package edu.csumb.project1_cst438.Model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.csumb.project1_cst438.R;

public class ACT_Initial_Course_Display extends AppCompatActivity implements MyAdapter.ItemClickListener {
    Button mAdd;
    RecyclerView mDisplay;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    CourseDao mCourseDao;
    List<Course> mCourseList;
    private String[] mDataset;
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__initial__course__display);

        mCourseDao = Room.databaseBuilder(this,AppDatabase.class,AppDatabase.COURSE_TABLE)
                .allowMainThreadQueries()
                .build()
                .getCourseDao();
        mAdd = findViewById(R.id.button_add);
        mDisplay = findViewById(R.id.course_display);
        layoutManager = new LinearLayoutManager(this);
        //mDisplay.setLayoutManager(layoutManager);
        mCourseList = mCourseDao.getAllCourses();
        //ArrayList help = new ArrayList();
        //int i = 0;
//        for (Course course:mCourseList) {
//            mDataset[i]=course.getCourseTitle();
//        }


        //mAdapter = new RecyclerView.Adapter(mCourseList);
        //mDisplay.setAdapter(mAdapter);
        /**
         * here is where we would pull any data from the database
         *
         */
        ArrayList<String> useData = new ArrayList<>();

        //useData.add("haha");
        for (Course course:mCourseList) {
            useData.add(course.getCourseTitle());
        }
        mDisplay.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyAdapter(this,useData);
        adapter.setClickListener(this);
        mDisplay.setAdapter(adapter);

    }
    @Override
    public void onItemClick(View view, int position){
        /**
         * here is what will happen to any given value clicked
         */
        Toast.makeText(this,adapter.getItem(position),Toast.LENGTH_SHORT).show();
        String clickedID = adapter.getItem(position);
        String selectedCourse = "selectedCourse";
        //Course cBundle = mCourseDao.getCourseByTitle(clickedID);
        Intent detail = new Intent(this,ACT_Detailed_Course_Editable.class);
        //detail.putExtra("selectedCourse", (Parcelable) cBundle);
        detail.putExtra("selectedCourse",clickedID);
        startActivity(detail);

    }
    public void add(View view){
        Intent intent = new Intent(this,ACT_Add_Course.class);
        startActivity(intent);
    }
}
