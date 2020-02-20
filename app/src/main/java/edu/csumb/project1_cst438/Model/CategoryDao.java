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
    @Delete
    void delete(Category...categories);

    @Query("SELECT * FROM " + AppRoom.CATEGORY_TABLE)
    List<Category> getAllCategories();

    @Query("SELECT * FROM " + AppRoom.CATEGORY_TABLE + " WHERE userID == :userID and courseID == :courseID")
    List<Category>getCategories(int userID, int courseID);


    @Query("SELECT * FROM " + AppRoom.CATEGORY_TABLE + " WHERE category_name = :categoryName")
    Category getCategoryByName(String categoryName);
    @Query("SELECT * FROM " + AppRoom.CATEGORY_TABLE + " WHERE category_percentage = :categoryPercentage")
    Category getCategoryPercentage(double categoryPercentage);

    @Query("SELECT * FROM " + AppRoom.CATEGORY_TABLE + " WHERE cid = :cid ")
    Category getCategoryByID(int cid);


}
