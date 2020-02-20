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

import java.util.List;

import edu.csumb.project1_cst438.Model.AppRoom;
import edu.csumb.project1_cst438.Model.Assignment;
import edu.csumb.project1_cst438.Model.AssignmentDao;

public class MultiAssignmentDisplay extends AppCompatActivity {

    private List<Assignment> assignments;
    private int courseId;
    private AssignmentDao mAssignmentDao;
    private RecyclerView rvAssignments;
    private DividerItemDecoration itemDecor;

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

        // get AssignmentDao to access the db
        mAssignmentDao = Room.databaseBuilder(this, AppRoom.class, AppRoom.dbName)
                .allowMainThreadQueries()
                .build()
                .assignmentDao();


        rvAssignments = (RecyclerView) findViewById(R.id.assignments_rv);

        // add lines between items to improve UX
        itemDecor = new DividerItemDecoration(rvAssignments.getContext(), DividerItemDecoration.VERTICAL);
        rvAssignments.addItemDecoration(itemDecor);

        int courseId = getIncomingCourse();

        populateRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        populateRecyclerView();
    }

    public List<Assignment> populateAssignmentHolder(int course) {
        List<Assignment> results;
        results = mAssignmentDao.getAssignmentsInCourse(course);

        return results;
    }

    private int getIncomingCourse() { // this may end up being a different type later
        return getIntent().getIntExtra("courseId", 0);
    }

    private void populateRecyclerView() {
        assignments = populateAssignmentHolder(courseId);

        AssignmentsAdapter adapter = new AssignmentsAdapter(this, assignments);
        rvAssignments.setAdapter(adapter);
        rvAssignments.setLayoutManager(new LinearLayoutManager(this));
    }
}
