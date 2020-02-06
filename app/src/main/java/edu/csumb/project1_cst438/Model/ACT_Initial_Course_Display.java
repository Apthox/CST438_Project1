package edu.csumb.project1_cst438.Model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.csumb.project1_cst438.R;

public class ACT_Initial_Course_Display extends AppCompatActivity {
    Button mAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__initial__course__display);

        mAdd = findViewById(R.id.button_add);
    }
    public void add(View view){
        Intent intent = new Intent(this,ACT_Add_Course.class);
        startActivity(intent);
    }
}
