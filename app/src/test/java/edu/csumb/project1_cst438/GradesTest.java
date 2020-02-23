package edu.csumb.project1_cst438;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Arrays;

import edu.csumb.project1_cst438.Util.Grades;


import static org.junit.Assert.*;

public class GradesTest {
    @Test
    public void testAverageGrade()
    {
        Grades grade = new Grades();
        List<Double> testGrades = Arrays.asList(95.00, 95.00, 95.00);
        assertEquals(95.00, grade.avgAssignmentGrade(testGrades), 0.1);
    }

    @Test
    public void testLetterGrade()
    {
        Grades grade = new Grades();
        double testAverageScore = 95.00;
        assertEquals('A', grade.letterGrade(testAverageScore));
    }

    @Test
    public void testAvgTotalGrade()
    {
        Grades grade = new Grades();
        List<Double> testGrades = Arrays.asList(35.00, 50.00, 5.00);
        assertEquals(90.00, grade.avgTotalGrade(testGrades), 0.1);
    }
}
