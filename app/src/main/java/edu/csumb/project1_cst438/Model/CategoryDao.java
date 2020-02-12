package edu.csumb.project1_cst438.Model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CategoryDao {

    @Query("SELECT * FROM category")
    List<Category> getAllCategory();

    @Query("SELECT * FROM category WHERE student_id IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Insert
    void insertAll(Category... categories);

    @Delete
    void delete(Category categories);

}
