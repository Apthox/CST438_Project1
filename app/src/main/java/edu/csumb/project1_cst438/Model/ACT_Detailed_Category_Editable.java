package edu.csumb.project1_cst438.Model;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import edu.csumb.project1_cst438.R;

public class ACT_Detailed_Category_Editable extends AppCompatActivity {
    TextView mDisplay;
    CategoryDao mCategoryDao;
    Button mEditButton, mDeleteButton;
    private int selectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__detailed__category__editable);

        Bundle bundle = getIntent().getExtras();

        mDeleteButton = findViewById(R.id.button_delete_course);
        mEditButton = findViewById(R.id.button_edit_course);
        mDisplay = findViewById(R.id.selectedDisplay);
        mCategoryDao = Room.databaseBuilder(this, AppRoom.class, AppRoom.CATEGORY_TABLE)
                .allowMainThreadQueries()
                .build()
                .getCategoryDao();

        //find the course in db
        if (bundle!=null){
            selectedCategory = bundle.getInt("selectedCategory");
            Category temp = mCategoryDao.getCategoryByID(selectedCategory);
            mDisplay.setText(temp.toString());
        }

    }


    public void onDelete(View view){
        new AlertDialog.Builder(this)
                .setTitle("Delete Entry")
                .setMessage("Are you certain you'd like to delete this course?")

                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //where to do when deletion occurs
                        mCategoryDao.delete(mCategoryDao.getCategoryByID(selectedCategory));
                        Intent intent = new Intent(ACT_Detailed_Category_Editable.this,ACT_Initial_Category_Display.class);
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
        intent.putExtra("refID",selectedCategory);
        startActivity(intent);

    }

}
