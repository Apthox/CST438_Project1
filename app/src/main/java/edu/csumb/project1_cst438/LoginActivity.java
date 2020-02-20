package edu.csumb.project1_cst438;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.SigningInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import edu.csumb.project1_cst438.Model.ACT_Initial_Course_Display;
import edu.csumb.project1_cst438.Model.AppRoom;
import edu.csumb.project1_cst438.Model.User;
import edu.csumb.project1_cst438.Model.UserDao;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final LoginActivity instance = this;

        final Button sign_in_button = (Button) findViewById(R.id.SignInButton);
        sign_in_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UserDao dao = AppRoom.getAppRoom(instance).userDao();

                EditText username = (EditText) findViewById(R.id.UsernameInput);
                EditText password = (EditText) findViewById(R.id.PasswordInput);

                if (username.getText().toString().equals("") || password.getText().toString().equals("")) {
                    AlertDialog alertDialog = MainActivity.createAlertDialog(instance, "Alert", "Input username and password!");
                    alertDialog.show();
                    return;
                }

                User user = dao.getUser(username.getText().toString());

                if (user == null) {
                    AlertDialog alertDialog = MainActivity.createAlertDialog(instance, "Alert", "User does not exist in the database!");
                    alertDialog.show();
                    return;
                }

                if (user.getPassword().equals(password.getText().toString())) {
                    AlertDialog alertDialog = MainActivity.createAlertDialog(instance, "Alert", "User authenticated!");
                    alertDialog.show();

                    MainActivity.signedIn = true;
                    MainActivity.username = user.getUsername();
                    MainActivity.uid = user.uid;

                    // TODO: Place intent here for course display page
                    Intent intent = new Intent(instance, ACT_Initial_Course_Display.class);
                    startActivity(intent);
                    finish();

                    return;
                } else {
                    AlertDialog alertDialog = MainActivity.createAlertDialog(instance, "Alert", "Password was incorrect!");
                    alertDialog.show();
                    return;
                }
            }
        });

        final Button create_account_button = (Button) findViewById(R.id.CreateAccountButton);
        create_account_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                instance.startActivityForResult(intent, -1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("Login Activity", "Checking Activity Result");

        if (requestCode == -1) {
            if (resultCode == RESULT_OK) {
                this.finish();
                Log.d("Login Activity", "Exiting!");
            }
        }
    }

}
