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
                // Perform action on click
                Intent activityChangeIntent = new Intent(LoginActivity.this, MainActivity.class);

                UserDao dao = AppRoom.getAppRoom(instance).userDao();

                EditText username = (EditText) findViewById(R.id.UsernameInput);
                EditText password = (EditText) findViewById(R.id.PasswordInput);

                if (username.getText().toString().equals("") || password.getText().toString().equals("")) {
                    AlertDialog alertDialog = createAlertDialog("Alert", "Input username and password!");
                    alertDialog.show();
                    return;
                }

                User user = dao.getUser(username.getText().toString());

                if (user == null) {
                    AlertDialog alertDialog = createAlertDialog("Alert", "User does not exist in the database!");
                    alertDialog.show();
                    return;
                }

                if (user.getPassword().equals(password.getText().toString())) {
                    AlertDialog alertDialog = createAlertDialog("Alert", "User authenticated!");
                    alertDialog.show();

                    MainActivity.signedIn = true;
                    MainActivity.username = user.getUsername();

                    // TODO: Place intent here for course display page

                    return;
                } else {
                    AlertDialog alertDialog = createAlertDialog("Alert", "Password was incorrect!");
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
                startActivity(intent);
            }
        });
    }

    private AlertDialog createAlertDialog(String title, String text) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

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
}
