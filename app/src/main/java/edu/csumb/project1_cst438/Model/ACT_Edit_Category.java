package edu.csumb.project1_cst438.Model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import edu.csumb.project1_cst438.R;

public class ACT_Edit_Category extends AppCompatActivity {
    EditText mCategoryName, mCategoryPercentage;
    CategoryDao mCategoryDao;
    Category mCategory;
    private Integer mID;
    boolean taken;

    List<Category>mCategoryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__edit__category);
        mCategoryName = findViewById(R.id.editCategory_name);
        mCategoryPercentage = findViewById(R.id.editCategory_percentage);
        Bundle b = getIntent().getExtras();
        mID = b.getInt("mID");
        taken = false;
        mCategoryDao = Room.databaseBuilder(this,AppRoom.class,AppRoom.CATEGORY_TABLE)
                .allowMainThreadQueries()
                .build()
                .getCategoryDao();
        mCategoryList = mCategoryDao.getAllCategorys();

    }

    public void onEdit(View view){
        //logic
        //Test if issues occur, including blanks
        if (mCategoryName.getText().toString().equals("")||mCategoryPercentage.getText().toString().equals("")){
            //a blank was entered, dialog confirm
            new AlertDialog.Builder(this)
                    .setTitle("Empty Entry Detected")
                    .setMessage("One or More Entries Were Left Blank, Fix That")
                    .setPositiveButton(android.R.string.yes,null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }else {
            //temporarily remove the currently being edited Category
            Category holdingCell = mCategoryDao.getCategoryByID(mID);
            for (Category c : mCategoryList) {
                if (c.getCid() == (mID)) {
                    taken = true;
                }
            }
            //actual action of button
            if (taken) {
                mCategory.setCategoryName(mCategoryName.getText().toString());
                mCategory.setCategoryPercentage(Double.parseDouble(mCategoryPercentage.getText().toString()));
            } else {
                new AlertDialog.Builder(this)
                        .setTitle("CategoryIdError")
                        .setMessage("This Category ID is Taken, Please Change It")
                        .setPositiveButton(android.R.string.yes, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        }
    }
}
