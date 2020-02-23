package edu.csumb.project1_cst438.Model;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.csumb.project1_cst438.LoginActivity;
import edu.csumb.project1_cst438.MainActivity;
import edu.csumb.project1_cst438.MultiAssignmentDisplay;
import edu.csumb.project1_cst438.R;

public class ACT_Initial_Category_Display extends AppCompatActivity implements MyAdapter.ItemClickListener {
    Button mAdd;
    RecyclerView mDisplay;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    CategoryDao mCategoryDao;
    AssignmentDao mAssignmentDao;
    List<Category> mCategoryList;
    int selectedCourse = -1;
    Assignment mCurrentAssignment;

    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__initial__category__display);

        mCategoryDao = Room.databaseBuilder(this,AppRoom.class,AppRoom.CATEGORY_TABLE)
                .allowMainThreadQueries()
                .build()
                .getCategoryDao();
        mAdd = findViewById(R.id.button_add);
        mDisplay = findViewById(R.id.Category_display);
        layoutManager = new LinearLayoutManager(this);
        //mDisplay.setLayoutManager(layoutManager);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            selectedCourse = bundle.getInt("course_id");
        }

        mCategoryList = mCategoryDao.getCategories(MainActivity.uid, selectedCourse);

        ArrayList<String> titles = new ArrayList<>();
        ArrayList<Integer> IDs = new ArrayList<>();

        for (Category category : mCategoryList) {
            titles.add(category.getCategoryName());
            IDs.add(category.getCid());
            Log.d("Category Activity", "ID > " + category.getCid());
        }

        mDisplay.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyAdapter(this, IDs, titles);
        adapter.setClickListener(this);
        mDisplay.setAdapter(adapter);

        final ACT_Initial_Category_Display instance = this;

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



        Button add_cat_button = findViewById(R.id.button_add);
        add_cat_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(instance, ACT_Add_Category.class);
                intent.putExtra("course_id", selectedCourse);
                startActivity(intent);
            }
        });

        //Assginment Calculation
//        mCurrentAssignment = getIncomingAssignment();
//        TextView avg_grade = findViewById(R.id.grades_display);
//        String update = getString();
//        avg_grade.setText(update);

    }

    private Assignment getIncomingAssignment() {
        Assignment assignmentFound = null;
        if(getIntent().hasExtra("Assignment")) {
            Assignment assignment = (Assignment) getIntent().getSerializableExtra("Assignment");
            assignmentFound = assignment;
        }
        // here a null may be returned
        // this will be addressed by removing the item from multiAssignmentDisplay
        // therefore returning null should never happen
        return assignmentFound;
    }

//    @Override
//    public String getString() {
//        super.onResume();
//        String data = "";
//        int count = 0;
//        float average_grade = 0;
//        int currentAssignmentId = mCurrentAssignment.getAssignmentId();
//        mCurrentAssignment= mAssignmentDao.getAssignmentFromId(currentAssignmentId);
//        for (Assignment: mCurrentAssignment){
//            average_grade += (mCurrentAssignment.getPossibleScore()/mCurrentAssignment.getPossibleScore() * 100);
//            count += 1;
//        }
//        average_grade = average_grade/count;
//
//        return data;
//    }

    @Override
    protected void onResume() {
        super.onResume();

        mCategoryList = mCategoryDao.getCategories(MainActivity.uid, selectedCourse);

        Log.d("Category Display", "Results > " + mCategoryList.size());

        ArrayList<String> titles = new ArrayList<>();
        ArrayList<Integer> IDs = new ArrayList<>();

        for (Category category : mCategoryList) {
            titles.add(category.getCategoryName());
            IDs.add(category.getCid());
            Log.d("Category Activity", "ID > " + category.getCid());
        }

        mDisplay.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyAdapter(this, IDs, titles);
        adapter.setClickListener(this);
        mDisplay.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position){

        Log.d("Category Activity", "Item clicked!");

        int cat_id = adapter.getID(position);

        Intent intent = new Intent(this, MultiAssignmentDisplay.class);

        intent.putExtra("course_id", selectedCourse);
        intent.putExtra("cat_id", cat_id);

        startActivity(intent);

        //Intent detail = new Intent(this,ACT_Detailed_Category_Editable.class);
        //detail.putExtra("selectedCategory",clickedID);
        //startActivity(detail);

    }
    public void add(View view){
        Intent intent = new Intent(this,ACT_Add_Category.class);
        intent.putExtra("course_id", selectedCourse);
        startActivity(intent);
    }
}
