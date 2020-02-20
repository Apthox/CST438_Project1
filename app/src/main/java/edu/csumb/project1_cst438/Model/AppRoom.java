package edu.csumb.project1_cst438.Model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Category.class}, version = 1, exportSchema = false)
public abstract class AppRoom extends RoomDatabase {
    public abstract UserDao userDao();

    public static final String CATEGORY_TABLE="category";
    public abstract CategoryDao getCategoryDao();
}
