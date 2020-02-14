package edu.csumb.project1_cst438;

import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        final Button add_button = (Button) findViewById(R.id.add_button);
        final Button remove_button = (Button) findViewById(R.id.remove_button);
        TextView gradeView = (TextView) findViewById(R.id.grade_view);
        TextView password = (TextView) findViewById(R.id.grade_percent_view);

        // on click listener for add_button
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddCategoryActivity();
            }
        });

        // on click listener for remove_button
        remove_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                openRemoveCategoryActivity();

                //***Alert dialog below will be moved to different place***

//                AlertDialog.Builder builder = new AlertDialog.Builder(CategoryActivity.this);
//                builder.setTitle("Are you sure you want to delete this category?");
//
//                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //delete category
//                    }
//                });
//                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        //cancel alert
//                    }
//                });
            }
        }); // end of onclick listener for remove button
    }

    public void openAddCategoryActivity() {
        // Main activity for now until we create add category activity.
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openRemoveCategoryActivity() {
        // Main activity for now until we create remove category activity.

        // Instead of a new activity, team suggested to have options on the clickable categories to delete/edit and one button on the bottom to add category.
        // For the delete option we can add an alert asking user to confirm that they want to delete that category.

        //Example: https://www.simplifiedcoding.net/create-options-menu-recyclerview-item-tutorial/
        // Not sure how helpful the link above is yet
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
