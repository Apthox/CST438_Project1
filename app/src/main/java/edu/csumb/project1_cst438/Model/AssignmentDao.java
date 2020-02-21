package edu.csumb.project1_cst438.Model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AssignmentDao {

    @Insert
    void insert(Assignment... assignment);

    @Delete
    void delete(Assignment assignment);

    @Update
    void update(Assignment... assignment);

    @Query("SELECT * FROM " + AppRoom.ASSIGNMENTS_TABLE + " WHERE courseId = :course")
    List<Assignment> getAssignmentsInCourse(int course);

    @Query("SELECT * FROM " + AppRoom.ASSIGNMENTS_TABLE + " WHERE categoryId = :category")
    List<Assignment> getAssignments(int category);

    @Query("SELECT * FROM " + AppRoom.ASSIGNMENTS_TABLE + " WHERE assignmentId = :aId")
    Assignment getAssignmentFromId(int aId);
}
