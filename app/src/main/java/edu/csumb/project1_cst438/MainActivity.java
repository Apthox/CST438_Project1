package edu.csumb.project1_cst438;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // make button that takes you to Create edu.csumb.project1_cst438.Model.Assignment Activity
        // This section should be deleted later

        Button goToCreateAssigBtn = (Button) findViewById(R.id.go_to_create_assignment_activity_btn);
        goToCreateAssigBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreateAssignmentActivity.class));
            }
        });

        Button goToMultiAssignmentDisplayBtn = (Button) findViewById(R.id.got_to_multi_assignment_display_btn);
        goToMultiAssignmentDisplayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "This runs on button press");
                startActivity(new Intent(MainActivity.this, MultiAssignmentDisplay.class));
            }
        });
    }
}