package edu.csumb.project1_cst438.Model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1, exportSchema = false) // remove exportSchema
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
