package edu.csumb.project1_cst438;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final LoginActivity instance = this;

        final Button button = (Button) findViewById(R.id.SignInButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent activityChangeIntent = new Intent(LoginActivity.this, MainActivity.class);

                // currentContext.startActivity(activityChangeIntent);

                EditText username = (EditText) findViewById(R.id.UsernameInput);
                EditText password = (EditText) findViewById(R.id.PasswordInput);

                Log.d("Login Activity", "Username > " + username.getText());
                Log.d("Login Activity", "Password > " + password.getText());

                LoginActivity.this.startActivity(activityChangeIntent);
                instance.finish();
            }
        });

    }
}
