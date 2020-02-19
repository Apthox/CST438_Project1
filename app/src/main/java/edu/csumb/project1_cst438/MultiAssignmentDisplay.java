package edu.csumb.project1_cst438;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import edu.csumb.project1_cst438.Model.AppDatabase;
import edu.csumb.project1_cst438.Model.Assignment;
import edu.csumb.project1_cst438.Model.AssignmentDao;

public class MultiAssignmentDisplay extends AppCompatActivity {

    List<Assignment> assignments;
    AssignmentDao mAssignmentDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_assignment_display);

        Button goToCreateAssigBtn = (Button) findViewById(R.id.add_assignment_btn);
        goToCreateAssigBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MultiAssignmentDisplay.this, CreateAssignmentActivity.class));
            }
        });

        RecyclerView rvAssignments = (RecyclerView) findViewById(R.id.assignments_rv);

        // add lines between items to improve UX
        DividerItemDecoration itemDecor = new DividerItemDecoration(rvAssignments.getContext(), DividerItemDecoration.VERTICAL);
        rvAssignments.addItemDecoration(itemDecor);

        // get AssignmentDao to access the db
        mAssignmentDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.dbName)
                .allowMainThreadQueries()
                .build()
                .assignmentDao();

        int courseId = getIncomingCourse();

        assignments = populateAssignmentHolder(courseId);

        AssignmentsAdapter adapter = new AssignmentsAdapter(this, assignments);
        rvAssignments.setAdapter(adapter);
        rvAssignments.setLayoutManager(new LinearLayoutManager(this));
    }

    public List<Assignment> populateAssignmentHolder() {
        List<Assignment> items = new ArrayList<>();

        Assignment testA = new Assignment("TitleTest", "01/01/2020", "02/01/2020",
                "12:00", "very important information", 10);
        Assignment testB = new Assignment("TitleTest", "01/01/2020", "02/01/2020",
                "12:00", "very important information", 10);
        Assignment testC = new Assignment("TitleTest", "01/01/2020", "02/01/2020",
                "12:00", "very important information", 10);
        Assignment testD = new Assignment("TitleTest", "01/01/2020", "02/01/2020",
                "12:00", "very important information", 10);
        Assignment testE = new Assignment("TitleTest", "01/01/2020", "02/01/2020",
                "12:00", "very important information", 10);
        Assignment testF = new Assignment("TitleTest", "01/01/2020", "02/01/2020",
                "12:00", "very important information", 10);
        Assignment testG = new Assignment("TitleTest", "01/01/2020", "02/01/2020",
                "12:00", "very important information", 10);
        Assignment testH = new Assignment("TitleTest", "01/01/2020", "02/01/2020",
                "12:00", "very important information", 10);
        Assignment testI = new Assignment("TitleTest", "01/01/2020", "02/01/2020",
                "12:00", "very important information", 10);
        Assignment testJ = new Assignment("TitleTest", "01/01/2020", "02/01/2020",
                "12:00", "very important information", 10);
        Assignment testK = new Assignment("TitleTest", "01/01/2020", "02/01/2020",
                "12:00", "very important information", 10);
        Assignment testL = new Assignment("TitleTest", "01/01/2020", "02/01/2020",
                "12:00", "very important information", 10);
        Assignment testM = new Assignment("TitleTest", "01/01/2020", "02/01/2020",
                "12:00", "very important information", 10);
        Assignment testN = new Assignment("TitleTest", "01/01/2020", "02/01/2020",
                "12:00", "very important information", 10);
        Assignment testO = new Assignment("TitleTest", "01/01/2020", "02/01/2020",
                "12:00", "very important information", 10);
        Assignment testP = new Assignment("TitleTest", "01/01/2020", "02/01/2020",
                "12:00", "very important information", 10);

        items.add(testA);
        items.add(testB);
        items.add(testC);
        items.add(testD);
        items.add(testE);
        items.add(testF);
        items.add(testG);
        items.add(testH);
        items.add(testI);
        items.add(testJ);
        items.add(testK);
        items.add(testL);
        items.add(testM);
        items.add(testN);
        items.add(testO);
        items.add(testP);

        return items;
    }

    public List<Assignment> populateAssignmentHolder(int course) {
        List<Assignment> results;
        results = mAssignmentDao.getAssignmentsInCourse(course);

        return results;
    }

    private int getIncomingCourse() { // this may end up being a different type later
        return getIntent().getIntExtra("courseId", 0);
    }
}
