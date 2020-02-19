package edu.csumb.project1_cst438;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import edu.csumb.project1_cst438.Model.AppRoom;

public class MainActivity extends AppCompatActivity {

    static public String username;
    static public boolean signedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity.username = "";
        MainActivity.signedIn = false;

        // loads data and creates singleton instance
        AppRoom.getAppRoom(MainActivity.this).loadData(this);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();



    }
}
