package edu.csumb.project1_cst438;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.csumb.project1_cst438.Model.Assignment;

import static org.junit.Assert.*;

public class AssignmentTest {
    // Test rules (so far...)
    // Test should be independent from each other
    // Test only apply to a small part of the whole
    // A good starting guide would be constructor->setter&getter->methods
    // Depending on the structure of the setters and getters you may test
    //      the methods setters&getters or constructors depend on first

    // tag constant
    private final String TAG = "AssignmentTestFile";
    // Date formats
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    // TODO: This is the list of test left to make
    // test constructor using dates

    // this test checks that the Assignment object created has the right information in it and also
    // helps to test some of the getters
    @Test
    public void testConstructorWithStringDates() {
        Assignment testA = new Assignment("TitleTest", "01/01/2020", "02/01/2020",
                "12:00", "very important information", 10);
        assertEquals("TitleTest", testA.getTitle());
        assertEquals("01/01/2020", testA.getStringDateAssigned());
        assertEquals("02/01/2020", testA.getStringDueDate());
        assertEquals("12:00", testA.getStringDueTime());
        assertEquals("very important information", testA.getDescription());
        assertTrue(10 == testA.getPossibleScore());
    }

    // test constructor using strings instead of dates
    @Test
    public void testConstructorWithLongDates() {
        Long jan_1_2020 = convertStringDateToLong("01/01/2020");
        Long jan_2_2020 = convertStringDateToLong("02/01/2020");
        Long dayTime12pm = convertStringTimeToLong("12:00");

        Assignment testA = new Assignment("TitleTest", jan_1_2020, jan_2_2020,
                dayTime12pm, "very important information", 10);
        assertEquals("TitleTest", testA.getTitle());
        assertEquals(jan_1_2020, testA.getDateAssigned());
        assertEquals(jan_2_2020, testA.getDueDate());
        assertEquals(dayTime12pm, testA.getDueTime());
        assertEquals("very important information", testA.getDescription());
        assertTrue(10 == testA.getPossibleScore());
    }

    // Change the title of an assignment
    @Test
    public void testTitleSetter() {
        Assignment testA = new Assignment("TitleTest", "01/01/2020", "02/01/2020",
                "12:00", "very important information", 10);

        testA.setTitle("TheNewPerfectTitle");
        assertEquals("TheNewPerfectTitle", testA.getTitle());
    }

    // Change assigned date
    @Test
    public void testAssignedLongDateSetter() {
        Assignment testA = new Assignment("TitleTest", "01/01/2020", "02/01/2020",
                "12:00", "very important information", 10);

        testA.setDateAssigned(convertStringDateToLong("02/02/2020"));
        assertTrue(1580630400 == (testA.getDateAssigned() / 1000));
        assertEquals("02/02/2020", testA.getStringDateAssigned());
    }

    @Test
    public void testAssignedStringDateSetter() {
        Assignment testA = new Assignment("TitleTest", "01/01/2020", "02/01/2020",
                "12:00", "very important information", 10);

        testA.setDateAssignedFromString("02/02/2020");
        assertEquals("02/02/2020", testA.getStringDateAssigned());
        assertTrue(1580630400 == (testA.getDateAssigned() / 1000));
    }

    // Change due date
    @Test
    public void testDueLongDate() {
        Assignment testA = new Assignment("TitleTest", "01/01/2020", "02/01/2020",
                "12:00", "very important information", 10);

        testA.setDueDate(convertStringDateToLong("02/02/2020"));
        assertTrue(1580630400 == (testA.getDueDate() / 1000));
        assertEquals("02/02/2020", testA.getStringDueDate());
    }

    @Test
    public void testDueStringDate() {
        Assignment testA = new Assignment("TitleTest", "01/01/2020", "02/01/2020",
                "12:00", "very important information", 10);

        testA.setDueDateFromString("02/02/2020");
        assertEquals("02/02/2020", testA.getStringDueDate());
        assertTrue(1580630400 == (testA.getDueDate() / 1000));
    }

    // Change due time
    @Test
    public void testDueLongTime() {
        Assignment testA = new Assignment("TitleTest", "01/01/2020", "02/01/2020",
                "12:00", "very important information", 10);

        testA.setDueTime(convertStringTimeToLong("13:00"));
        assertTrue(75600 == (testA.getDueTime() / 1000));
        assertEquals("13:00", testA.getStringDueTime());
    }

    @Test
    public void testDueStringTime() {
        Assignment testA = new Assignment("TitleTest", "01/01/2020", "02/01/2020",
                "12:00", "very important information", 10);

        testA.setDueTimeFromString("13:00");
        assertEquals("13:00", testA.getStringDueTime());
        assertTrue(75600 == (testA.getDueTime() / 1000));
    }


    // Can you display Assignment class as a string?
    @Test
    public void testAssignmentToString() {
        Assignment testA = new Assignment("TitleTest", "01/01/2020", "02/01/2020",
                "12:00", "very important information", 10);

        String expectedAssignmentString = "Title: TitleTest" + "\n" + "Date assigned: " + "01/01/2020" + "\n" +
                "Due date: 02/01/2020" + "\n" + "Due time: 12:00" + "\n" + "Description: very important information" + "\n" +
                "Possible score: 10.0";
        assertEquals(expectedAssignmentString, testA.toString());
    }

    // TODO: Remember test are always growing and changing. If you can improve something do it!

    // functions for tests
    private String convertLongTimeToString(Long date) {
        return timeFormat.format(new Date(date));
    }

    private Long convertStringTimeToLong(String time) {
        Date temp = parseMomentInTimeFromFormatAndStringToDate(timeFormat, time);
        return temp.getTime();
    }

    private Long convertStringDateToLong(String date) {
        Date temp = parseMomentInTimeFromFormatAndStringToDate(dateFormat, date);
        return temp.getTime();
    }

    private Date parseMomentInTimeFromFormatAndStringToDate(SimpleDateFormat format, String date) {
        Date found = null;
        try{
            found = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return found;
    }
}