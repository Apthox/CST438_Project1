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
        Course course = new Course("Calculus","Ms. Thomas","Is math",new Date(2020,01,01),new Date(2020,02,02),103);
        assertEquals("CourseInfo: " + "Calculus" + "Is math" + "Ms. Thomas" + 103 + new Date(2020,01,01) + new Date(2020,02,02),course.toString());
    }
    @Test
    public void testGetCourseId(){
        Course course = new Course("Calculus","Ms. Thomas","Is math",new Date(2020,01,01),new Date(2020,02,02),103);
        //assertEquals(103,course.getCourseID());
        assertTrue(103==course.getCourseID());
    }
    @Test
    public void testGetCourseTitle(){
        Course course = new Course("Calculus","Ms. Thomas","Is math",new Date(2020,01,01),new Date(2020,02,02),103);
        assertEquals("Calculus",course.getCourseTitle());
    }
    @Test
    public void testGetCourseInstructor(){
        Course course = new Course("Calculus","Ms. Thomas","Is math",new Date(2020,01,01),new Date(2020,02,02),103);
        assertEquals("Ms. Thomas",course.getInstructor());
    }
    @Test
    public void testGetCourseStartDate(){
        Course course = new Course("Calculus","Ms. Thomas","Is math",new Date(2020,01,01),new Date(2020,02,02),103);
        assertEquals(new Date(2020,01,01),course.getStartDate());
    }
    @Test
    public void testGetCourseEndDate(){
        Course course = new Course("Calculus","Ms. Thomas","Is math",new Date(2020,01,01),new Date(2020,02,02),103);
        assertEquals(new Date(2020,02,02),course.getEndDate());
    }
    @Test
    public void testGetCourseDescription(){
        Course course = new Course("Calculus","Ms. Thomas","Is math",new Date(2020,01,01),new Date(2020,02,02),103);
        assertEquals("Is math",course.getCourseDescription());
    }
    @Test
    public void testSetCourseID(){
        Course course = new Course("Calculus","Ms. Thomas","Is math",new Date(2020,01,01),new Date(2020,02,02),103);
        course.setCourseID(609);
        //assertEquals(609,course.getCourseID());
        assertTrue(609==course.getCourseID());
    }
    @Test
    public void testSetCourseTitle(){
        Course course = new Course("Calculus","Ms. Thomas","Is math",new Date(2020,01,01),new Date(2020,02,02),103);
        course.setCourseTitle("English");
        assertEquals("English",course.getCourseTitle());
    }
    @Test
    public void testSetCourseDescription(){
        Course course = new Course("Calculus","Ms. Thomas","Is math",new Date(2020,01,01),new Date(2020,02,02),103);
        course.setCourseDescription("Is not math");
        assertEquals("Is not math",course.getCourseDescription());
    }
    @Test
    public void testSetCourseInstructor(){
        Course course = new Course("Calculus","Ms. Thomas","Is math",new Date(2020,01,01),new Date(2020,02,02),103);
        course.setInstructor("Mr. Notmath");
        assertEquals("Mr. Notmath",course.getInstructor());
    }
    @Test
    public void testSetCourseStartDate(){
        Course course = new Course("Calculus","Ms. Thomas","Is math",new Date(2020,01,01),new Date(2020,02,02),103);
        course.setCourseStartDate(new Date(2020,8,9));
        assertEquals(new Date(2020,8,9),course.getStartDate());
    }
    @Test
    public void testSetCourseEndDate(){
        Course course = new Course("Calculus","Ms. Thomas","Is math",new Date(2020,01,01),new Date(2020,02,02),103);
        course.setCourseEndDate(new Date(2020,7,15));
        assertEquals(new Date(2020,7,15),course.getEndDate());
    }
}
