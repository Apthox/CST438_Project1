package edu.csumb.project1_cst438;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

import edu.csumb.project1_cst438.Model.ACT_Initial_Course_Display;
import edu.csumb.project1_cst438.Model.Course;
import edu.csumb.project1_cst438.Model.CourseDao;

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

    public static AlertDialog createAlertDialog(Context context, String title, String text) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // set title
        alertDialogBuilder.setTitle(title);

        // set dialog message
        alertDialogBuilder
                .setMessage(text)
                .setCancelable(true)
                .setNegativeButton("Close",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });

        return alertDialogBuilder.create();
    }

    public static void logout() {
        MainActivity.username = "";
        MainActivity.signedIn = false;
    }
}
