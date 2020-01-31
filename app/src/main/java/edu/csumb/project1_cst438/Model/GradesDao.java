package edu.csumb.project1_cst438.Model;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface GradesDao {

    @Query("SELECT * FROM grades")
    List<Grades> getAllGrades();

    @Query("SELECT * FROM grades WHERE student_id IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Insert
    void insertAll(Grades... grade);

    @Delete
    void delete(Grades grade);

}
