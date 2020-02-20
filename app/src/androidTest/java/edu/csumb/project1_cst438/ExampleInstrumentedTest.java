package edu.csumb.project1_cst438;

import android.content.Context;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

import edu.csumb.project1_cst438.Model.AppRoom;
import edu.csumb.project1_cst438.Model.Course;
import edu.csumb.project1_cst438.Model.CourseDao;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("edu.csumb.project1_cst438", appContext.getPackageName());
    }
    private AppRoom db;
    private CourseDao courseDao;
    @Before
    public void createDb(){
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(),
                AppRoom.class)
                .allowMainThreadQueries()
                .build();
        courseDao =  db.getCourseDao();
    }
    @Before
    public void setUp() throws Exception{

    }
    @After
    public void tearDown() throws Exception{

    }
    @Test
    public void insert(){
        Course testInsert = new Course("Psych","Big Mike","learn bout brains","2020/7/8","2020/5/6",907);
        courseDao.insert(testInsert);
        List<Course>DBTestList = courseDao.getAllCourses();
        assertEquals(1,DBTestList.size());
        assertEquals(testInsert.getCourseID(),DBTestList.get(0).getCourseID());
        //assertEquals(testInsert,DBTestList.get(0));
    }
    @Test
    public void delete(){
        Course testInsert = new Course("Psych","Big Mike","learn bout brains","2020/7/8","2020/5/6",907);
        courseDao.insert(testInsert);
        List<Course>DBTestList = courseDao.getAllCourses();
        assertEquals(1,DBTestList.size());
        courseDao.delete(courseDao.getCourseByID(907));
        DBTestList = courseDao.getAllCourses();
        assertEquals(0,DBTestList.size());
    }

}
