package edu.csumb.project1_cst438;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.csumb.project1_cst438.Model.AppRoom;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // loads data and creates singleton instance
        AppRoom.getAppRoom(MainActivity.this).loadData(this);

    }
}
