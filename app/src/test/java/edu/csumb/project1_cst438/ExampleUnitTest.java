//require constructor to make test.
//

package edu.csumb.project1_cst438;

import android.content.Context;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import edu.csumb.project1_cst438.Model.Grades;

import static org.junit.Assert.*;




/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    /*
        I believe we need to set up a constructor because I am getting an error due to having the
        functions running as static.
     */

//    @Test
//    public void testaverageGrade()
//    {
//        List<Double> testGrades = Arrays.asList(95.00, 95.00, 95.00);
//        assertEquals(95.00, Grades.averageGrade(testGrades));
//    }
//
//    @Test
//    public void testLetterGrade()
//    {
//        double testAverageScore = 95.00;
//        assertEquals('A', Grades.letterGrade(testAverageScore));
//    }
}