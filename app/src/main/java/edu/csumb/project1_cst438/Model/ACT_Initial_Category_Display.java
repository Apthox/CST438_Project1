package edu.csumb.project1_cst438.Model;

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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.csumb.project1_cst438.LoginActivity;
import edu.csumb.project1_cst438.MainActivity;
import edu.csumb.project1_cst438.R;

public class ACT_Initial_Category_Display extends AppCompatActivity implements MyAdapter.ItemClickListener {
    Button mAdd;
    RecyclerView mDisplay;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    CategoryDao mCategoryDao;
    List<Category> mCategoryList;
    int selectedCourse = -1;

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
            selectedCourse = bundle.getInt("courseID");
        }

        Category cat = new Category("Podcast Homeworks", 20, MainActivity.uid, selectedCourse);
        mCategoryDao.insert(cat);
        Log.d("Category Activity", "Created new category 1> " + cat.getCid());

        Category cat2 = new Category("Podcast Exams", 50, MainActivity.uid, selectedCourse);
        mCategoryDao.insert(cat2);
        Log.d("Category Activity", "Created new category 2> " + cat2.getCid());

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
    }
    @Override
    public void onItemClick(View view, int position){
        /**
         * here is what will happen to any given value clicked
         */

        int cat_id = adapter.getID(position);

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
