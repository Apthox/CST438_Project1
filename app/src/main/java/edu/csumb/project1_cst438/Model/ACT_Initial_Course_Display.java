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

import edu.csumb.project1_cst438.LoginActivity;
import edu.csumb.project1_cst438.MainActivity;
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

        mCourseDao = Room.databaseBuilder(this,AppRoom.class,AppRoom.COURSE_TABLE)
                .allowMainThreadQueries()
                .build()
                .getCourseDao();
        mAdd = findViewById(R.id.button_add);
        mDisplay = findViewById(R.id.course_display);
        layoutManager = new LinearLayoutManager(this);
        //mDisplay.setLayoutManager(layoutManager);
        mCourseList = mCourseDao.getAllCourses();

        /**
         * here is where we would pull any data from the database
         *
         */
        ArrayList<String> useData = new ArrayList<>();

        for (Course course : mCourseList) {
            //useData.add("CourseTitle: "+course.getCourseTitle());
            useData.add("ID#:" +course.getCourseID());
        }
        mDisplay.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyAdapter(this,useData);
        adapter.setClickListener(this);
        mDisplay.setAdapter(adapter);

        final ACT_Initial_Course_Display instance = this;

        Button logout_button = findViewById(R.id.logout_button);
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.logout();
                Intent intent = new Intent(instance, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onItemClick(View view, int position){
        /**
         * here is what will happen to any given value clicked
         */
        String clickedIDFull = adapter.getItem(position);
        String clickedIDSub = clickedIDFull.substring(4,7);
        Integer clickedID = Integer.parseInt(clickedIDSub);
        String selectedCourse = "selectedCourse";
        Intent detail = new Intent(this,ACT_Detailed_Course_Editable.class);
        detail.putExtra("selectedCourse",clickedID);
        startActivity(detail);

    }
    public void add(View view){
        Intent intent = new Intent(this,ACT_Add_Course.class);
        startActivity(intent);
    }
}
