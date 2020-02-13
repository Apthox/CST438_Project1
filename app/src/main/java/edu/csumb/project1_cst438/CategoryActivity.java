package edu.csumb.project1_cst438;

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
            }
        });
    }

    public void openAddCategoryActivity() {
        // Main activity for now until we create add category activity.
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openRemoveCategoryActivity() {
        // Main activity for now until we create remove category activity.
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
