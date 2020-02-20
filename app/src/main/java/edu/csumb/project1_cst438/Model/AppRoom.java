package edu.csumb.project1_cst438.Model;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;

@Database(entities = {User.class,  Assignment.class, Course.class}, version = 4, exportSchema = false)
public abstract class AppRoom extends RoomDatabase {
    private static AppRoom instance;

    public static final String dbName = "db-studentGradeTracker";

    public abstract UserDao userDao();
    public abstract AssignmentDao assignmentDao();
    public abstract CourseDao getCourseDao();

    public static final String ASSIGNMENTS_TABLE = "Assignment_Table";
    public static final String COURSE_TABLE="course";

    public static AppRoom getAppRoom(final Context context){

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppRoom.class,
                    "GradingDB") // database name
                    .allowMainThreadQueries()  // temporary for now
                    .fallbackToDestructiveMigration()
                    .fallbackToDestructiveMigrationOnDowngrade()
                    .build();
        }
        return instance;
    }

    public void loadData(Context context){

        // if user table is empty, then load data for users and flights
        List<User> user_list = AppRoom.getAppRoom(context).userDao().getAllUsers();
        if (user_list.size() == 0) {
            Log.d("FlightRoom", "loading data ");
            loadUsers(context);
        } else {
            Log.d("FlightRoom", "data retained!");
        }
    }

    private void loadUsers(Context context) {
        UserDao dao = getAppRoom(context).userDao();

        User alice = new User("A@lice5", "@cSit100", "Alice", "Smith");
        User brian = new User("$BriAn7","123aBc##", "Brain", "Bob");
        User chris = new User("!chriS12!", "CHrIS12!!", "Chris", "Sanchez");
        User kevin = new User("kevin1", "guzman1", "Kevin", "Guzman");
        dao.insert(alice);
        dao.insert(brian);
        dao.insert(chris);
        dao.insert(kevin);
        Log.d("FlightRoom", "4 users added to database");
    }
}