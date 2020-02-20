package edu.csumb.project1_cst438.Model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert
    void insert(Category...categories);
    @Update
    void update(Category...categories);
    @Delete
    void delete(Category...categories);

    @Query("SELECT * FROM " + AppDatabase.CATEGORY_TABLE)
    List<Category>getAllCourses();
    @Query("SELECT * FROM " + AppDatabase.CATEGORY_TABLE + " WHERE category_name = :categoryName")
    Category getCategoryByName(String categoryName);
    @Query("SELECT * FROM " + AppDatabase.CATEGORY_TABLE + " WHERE percentage = :categoryPercentage")
    Category getCategoryPercentage(double categoryPercentage);

}
