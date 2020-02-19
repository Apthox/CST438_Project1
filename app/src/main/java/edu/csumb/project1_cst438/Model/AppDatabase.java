package edu.csumb.project1_cst438.Model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract CategoryDao categoryDao();

    public static final String CATEGORY_TABLE="category";
    public abstract CategoryDao getCategoryDao();
}
