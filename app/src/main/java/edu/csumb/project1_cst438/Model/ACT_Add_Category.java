package edu.csumb.project1_cst438.Model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import edu.csumb.project1_cst438.MainActivity;
import edu.csumb.project1_cst438.R;

public class ACT_Add_Category extends AppCompatActivity {
    EditText mCategoryName, mCategoryPercentage, mID;
    Button mDone, mAdd;
    CategoryDao mCategoryDao;
    boolean taken;
    int course_id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_category_screen);

        mCategoryName = findViewById(R.id.category_name);
        mCategoryPercentage = findViewById(R.id.category_percentage);
        mID = findViewById(R.id.categoryID);
        mAdd = findViewById(R.id.button2);
        mDone = findViewById(R.id.button_submit);
        taken=false;

        mCategoryDao = Room.databaseBuilder(this,AppRoom.class,AppRoom.CATEGORY_TABLE)
                .allowMainThreadQueries()
                .build()
                .getCategoryDao();

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            course_id = bundle.getInt("course_id");
        }

        Log.d("Add Category", "Course ID > " + course_id);

    }

    /*
    This is what will add the category to the db. All logic for each fields entry conditions
    are also here.
     */
    public void onAdd(View view){

        //Logic portion

        //Test if issues occur, including blanks
        if (mCategoryName.getText().toString().equals("") || mCategoryPercentage.getText().toString().equals("")){
            //a blank was entered, dialog confirm
            new AlertDialog.Builder(this)
                    .setTitle("Empty Entry Detected")
                    .setMessage("One or More Entries Were Left Blank, Fix That")
                    .setPositiveButton(android.R.string.yes,null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else {
            //Retrieve all values
            String mTmpName = mCategoryName.getText().toString();
            double mTmpPercentage = Double.parseDouble(mCategoryPercentage.getText().toString());
//            Integer mTmpCategoryID = Integer.parseInt(mID.getText().toString());
//            List<Category> mCategoryList = mCategoryDao.getAllCategories();
//
//            //Checking ID matches
//            for (Category c : mCategoryList) {
//                if (c.getCid() == mTmpCategoryID) {
//                    taken = true;
//                }
//            }
//            if (taken) {
//                new AlertDialog.Builder(this)
//                        .setTitle("CategoryIdError")
//                        .setMessage("This Category ID is Taken, Please Change It")
//                        .setPositiveButton(android.R.string.yes, null)
//                        .setIcon(android.R.drawable.ic_dialog_alert)
//                        .show();
//            } else if (!taken) {
                //Insert Portion
                //name,instructor,description,start date, end date,courseID

            Log.d("Add Category", "Inserting Data");
            Category category = new Category(mTmpName, mTmpPercentage, MainActivity.uid, course_id);
            mCategoryDao.insert(category);
                //clear screen???
//            CategoryName.getText().clear();
            mCategoryPercentage.getText().clear();
            mID.getText().clear();
//            }
        }


    }
    /*
    This is the return button or like "im done entering stuff button"
     */
    public void onDone(View view){

        Intent back = new Intent(this,ACT_Initial_Category_Display.class);
        startActivity(back);

    }


}
