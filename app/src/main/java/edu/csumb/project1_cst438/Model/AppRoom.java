package edu.csumb.project1_cst438.Model;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;

@Database(entities = {User.class}, version = 1)
public abstract class AppRoom extends RoomDatabase {

    private static AppRoom instance;

    public abstract UserDao userDao();

    public static AppRoom getAppRoom(final Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppRoom.class,
                    "FlightDB") // database name
                    .allowMainThreadQueries()  // temporary for now
                    .build();
        }
        return instance;
    }

    public void loadData(Context context){

        // if user table is empty, then load data for users and flights

        List<User> user_list = AppRoom.getAppRoom(context).dao().getAllUsers();
        if (user_list.size() == 0) {
            Log.d("FlightRoom", "loading data ");
            loadUsers(context);
        }
    }

    private void loadUsers(Context context) {
        UserDao dao = getAppRoom(context).dao();

        User alice = new User("A@lice5", "@cSit100", "Alice", "Smith");
        User brian = new User("$BriAn7","123aBc##", "Brain", "Bob");
        User chris = new User("!chriS12!", "CHrIS12!!", "Chris", "Sanchez");
        dao.insert(alice);
        dao.insert(brian);
        dao.insert(chris);
        Log.d("FlightRoom", "3 users added to database");
    }


}
