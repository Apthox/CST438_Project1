package edu.csumb.project1_cst438.Model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Assignment.class}, version = 1)
public abstract class AppRoom extends RoomDatabase {
    public static final String dbName = "db-studentGradeTracker";
    public static final String ASSIGNMENTS_TABLE = "Assignment_Table";

    public abstract UserDao userDao();
    public abstract AssignmentDao assignmentDao();
}
