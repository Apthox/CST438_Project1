package edu.csumb.project1_cst438.Model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CourseDao {
    @Insert
    void insert(Course...courses);
    @Update
    void update(Course...courses);
    @Delete
    void delete(Course...courses);

    @Query("SELECT * FROM " + AppDatabase.COURSE_TABLE)
    List<Course>getAllCourses();
    @Query("SELECT * FROM " + AppDatabase.COURSE_TABLE + " WHERE mCourseID = :courseId")
    Course getCourseByID(int courseId);
    @Query("SELECT * FROM " + AppDatabase.COURSE_TABLE + " WHERE mCourseTitle = :courseTitle")
    Course getCourseByTitle(String courseTitle);


}
