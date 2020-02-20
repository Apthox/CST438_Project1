package edu.csumb.project1_cst438;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import edu.csumb.project1_cst438.Model.AppRoom;
import edu.csumb.project1_cst438.Model.UserDao;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        final SignUpActivity instance = this;

        final Button create_account_button = (Button) findViewById(R.id.createAccountButton);
        create_account_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText username = (EditText) findViewById(R.id.usernameInput);
                final EditText password = (EditText) findViewById(R.id.passwordInput);
                final EditText first = (EditText) findViewById(R.id.firstNameInput);
                final EditText last = (EditText) findViewById(R.id.lastNameInput);

                if (
                    username.getText().toString().equals("") ||
                    password.getText().toString().equals("") ||
                    first.getText().toString().equals("") ||
                    last.getText().toString().equals("")
                ) {
                    AlertDialog alert = MainActivity.createAlertDialog(instance, "Alert", "Input all boxes!");
                    alert.show();
                    return;
                }
            }
        });

    }
}
