package edu.csumb.project1_cst438;
import org.junit.Test;

import java.util.Date;

import edu.csumb.project1_cst438.Model.Course;

import static org.junit.Assert.*;

public class CourseTest {
    /**
     * constructor order, title,instructor,description,start date, end date,courseID
     */
    @Test
    public void testCourseConstructor(){
        Course course = new Course("Calculus","Ms. Thomas","Is math","2020/2/2","2020/2/2",103);
        assertEquals("CourseInfo: " + "Calculus" + "Is math" + "Ms. Thomas" + 103 + "2020/2/2" + "2020/2/2",course.toString());
    }
    @Test
    public void testGetCourseId(){
        Course course = new Course("Calculus","Ms. Thomas","Is math","2020/2/2","2020/2/2",103);
        //assertEquals(103,course.getCourseID());
        assertTrue(103==course.getCourseID());
    }
    @Test
    public void testGetCourseTitle(){
        Course course = new Course("Calculus","Ms. Thomas","Is math","2020/2/2","2020/2/2",103);
        assertEquals("Calculus",course.getCourseTitle());
    }
    @Test
    public void testGetCourseInstructor(){
        Course course = new Course("Calculus","Ms. Thomas","Is math","2020/2/2","2020/2/2",103);
        assertEquals("Ms. Thomas",course.getInstructor());
    }
    @Test
    public void testGetCourseStartDate(){
        Course course = new Course("Calculus","Ms. Thomas","Is math","2020/2/2","2020/2/2",103);
        assertEquals("2020/2/2",course.getStartDate());
    }
    @Test
    public void testGetCourseEndDate(){
        Course course = new Course("Calculus","Ms. Thomas","Is math","2020/2/2","2020/2/2",103);
        assertEquals("2020/2/2",course.getEndDate());
    }
    @Test
    public void testGetCourseDescription(){
        Course course = new Course("Calculus","Ms. Thomas","Is math","2020/2/2","2020/2/2",103);
        assertEquals("Is math",course.getCourseDescription());
    }
    @Test
    public void testSetCourseID(){
        Course course = new Course("Calculus","Ms. Thomas","Is math","2020/2/2","2020/2/2",103);
        course.setCourseID(609);
        //assertEquals(609,course.getCourseID());
        assertTrue(609==course.getCourseID());
    }
    @Test
    public void testSetCourseTitle(){
        Course course = new Course("Calculus","Ms. Thomas","Is math","2020/2/2","2020/2/2",103);        course.setCourseTitle("English");
        assertEquals("English",course.getCourseTitle());
    }
    @Test
    public void testSetCourseDescription(){
        Course course = new Course("Calculus","Ms. Thomas","Is math","2020/2/2","2020/2/2",103);
        course.setCourseDescription("Is not math");
        assertEquals("Is not math",course.getCourseDescription());
    }
    @Test
    public void testSetCourseInstructor(){
        Course course = new Course("Calculus","Ms. Thomas","Is math","2020/2/2","2020/2/2",103);
        course.setInstructor("Mr. Notmath");
        assertEquals("Mr. Notmath",course.getInstructor());
    }
    @Test
    public void testSetCourseStartDate(){
        Course course = new Course("Calculus","Ms. Thomas","Is math","2020/2/2","2020/2/2",103);
        course.setCourseStartDate("2020/4/6");
        assertEquals("2020/4/6",course.getStartDate());
    }
    @Test
    public void testSetCourseEndDate(){
        Course course = new Course("Calculus","Ms. Thomas","Is math","2020/2/2","2020/2/2",103);
        course.setCourseEndDate("2020/8/9");
        assertEquals("2020/8/9",course.getEndDate());
    }
}
