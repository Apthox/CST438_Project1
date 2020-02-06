package edu.csumb.project1_cst438.Model;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {User.class,Course.class}, version = 1)
@TypeConverters(DateTypeConverter.class)

public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    public static final String COURSE_TABLE="course";
    public abstract CourseDao getCourseDao();
}
