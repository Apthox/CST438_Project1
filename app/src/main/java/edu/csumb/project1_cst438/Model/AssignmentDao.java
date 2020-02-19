package edu.csumb.project1_cst438.Model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AssignmentDao {

    @Insert
    void insert(Assignment... assignment);

    @Delete
    void delete(Assignment assignment);

    @Query("SELECT * FROM " + AppDatabase.ASSIGNMENTS_TABLE + " WHERE courseId = :course")
    List<Assignment> getAssignmentsInCourse(int course);
}
