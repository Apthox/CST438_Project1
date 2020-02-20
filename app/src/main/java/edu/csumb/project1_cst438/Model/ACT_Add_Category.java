package edu.csumb.project1_cst438.Model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import edu.csumb.project1_cst438.R;

public class ACT_Add_Category extends AppCompatActivity {
    EditText mCategoryName, mCategoryPercentage, mID;
    Button mDone, mAdd;
    CategoryDao mCategoryDao;
    boolean taken;

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

    }

    /*
    This is what will add the category to the db. All logic for each fields entry conditions
    are also here.
     */
    public void onAdd(View view){

        //Logic portion

        //Test if issues occur, including blanks
        if (mID.getText().toString().equals("")||mCategoryName.getText().toString().equals("")||mCategoryPercentage.getText().toString().equals("")){
            //a blank was entered, dialog confirm
            new AlertDialog.Builder(this)
                    .setTitle("Empty Entry Detected")
                    .setMessage("One or More Entries Were Left Blank, Fix That")
                    .setPositiveButton(android.R.string.yes,null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }else {
            //Retrieve all values
            String mTmpName = mCategoryName.getText().toString();
            Double mTmpPercentage = Double.parseDouble(mCategoryPercentage.getText().toString());
            Integer mTmpCategoryID = Integer.parseInt(mID.getText().toString());
            List<Category> mCategoryList = mCategoryDao.getAllCategorys();

            //Checking ID matches
            for (Category c : mCategoryList) {
                if (c.getCid() == mTmpCategoryID) {
                    taken = true;
                }
            }
            if (taken) {
                new AlertDialog.Builder(this)
                        .setTitle("CategoryIdError")
                        .setMessage("This Category ID is Taken, Please Change It")
                        .setPositiveButton(android.R.string.yes, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            } else if (!taken) {
                //Insert Portion
                //name,instructor,description,start date, end date,courseID
                Category category = new Category(mTmpCategoryID, mTmpName, mTmpPercentage);
                mCategoryDao.insert(category);
                //clear screen???
                mCategoryName.getText().clear();
                mCategoryPercentage.getText().clear();
                mID.getText().clear();
            }
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
