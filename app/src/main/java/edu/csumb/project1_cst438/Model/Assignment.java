/*
* edu.csumb.project1_cst438.Model.Assignment class is intended to store the information pertaining to an assignment.
* That is to say holds Title, Date assigned, Due date and time, Description, Possible score
* and Category.
*
* @Author   Juan Eduardo Garcia
*/

package edu.csumb.project1_cst438.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(tableName = "Assignment_Table")
public class Assignment implements Serializable {

    @Ignore
    private static final String TAG = "Assignment";
    @Ignore
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    @Ignore
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    @PrimaryKey (autoGenerate = true)
    @NonNull
    private int assignmentId;
    
    @NonNull
    private int courseId;
    @NonNull
    private int categoryId; // grading category (hw, test, quiz, etc.)

    @NonNull
    private String title;
    private Long dateAssigned; // using long for ease of use
    private Long dueDate;
    private Long dueTime;
    private String description;

    private String category;
    private float possibleScore;
    private float scoreEarned;

    // Constructor for assignment using date objects for dateAssigned and dueDateAndTime
    public Assignment(String title, Long dateAssigned, Long dueDate, Long dueTime,
            String description, float possibleScore) {

        this.title = title;
        this.dateAssigned = dateAssigned;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        this.description = description;
        this.possibleScore = possibleScore;
        this.scoreEarned = -1;
    }

    // Constructor for assignment using strings for dateAssigned and dueDateAndTime
    public Assignment(String title, String dateAssigned, String dueDate, String dueTime,
            String description, float possibleScore) {
        this.title = title;
        this.dateAssigned = convertStringDateToLong(dateAssigned); // could be null due to parsing
        this.dueDate = convertStringDateToLong(dueDate); // could be null due to parsing
        this.dueTime = convertStringTimeToLong(dueTime); // could be null due to parsing
        this.description = description;
        this.possibleScore = possibleScore;
        this.scoreEarned = -1;
    }

    // getter and setter for title --------------------------------------
    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // getter and setter for dateAssigned -------------------------------
    public Long getDateAssigned() { // using long
        return dateAssigned;
    }

    public String getStringDateAssigned() { // using string
        return convertLongDateToString(dateAssigned);
    }

    public void setDateAssigned(Long date) { // using long
        this.dateAssigned = date;
    }

    public void setDateAssignedFromString(String date) { // using string
        this.dateAssigned = convertStringDateToLong(date);
    }

    // getter and setter for dueDate ----------------------------------
    public Long getDueDate() {
        return dueDate;
    }

    public String getStringDueDate() {
        return convertLongDateToString(dueDate);
    }

    public void setDueDate(Long dueDate) {
        this.dueDate = dueDate;
    }

    public void setDueDateFromString(String date) {
        this.dueDate = convertStringDateToLong(date);
    }

    // getter and setter for dueTime ----------------------------------
    public Long getDueTime() {
        return dueTime;
    }

    public String getStringDueTime() {
        return convertLongTimeToString(dueTime);
    }

    public void setDueTime(Long time) {
        this.dueTime = time;
    }

    public void setDueTimeFromString(String time) {
        this.dueTime = convertStringTimeToLong(time);
    }

    // converter functions --------------------------------------------
    private String convertLongDateToString(Long date) {
        return dateFormat.format(new Date(date));
    }

    private String convertLongTimeToString(Long date) {
        return timeFormat.format(new Date(date));
    }

    private Long convertStringDateToLong(String date) {
        Date temp = parseMomentInTimeFromFormatAndStringToDate(dateFormat, date);
        return temp.getTime();
    }

    private Long convertStringTimeToLong(String time) {
        Date temp = parseMomentInTimeFromFormatAndStringToDate(timeFormat, time);
        return temp.getTime();
    }

    /* parseMomentInTimeFromFormatAndStringToDate(simpleDateFormat, String)
    This helper function will take a SimpleDateFormat and a string containing a moment in time
    that follows the SimpleDateFormat given and will return a Date object containing the appropriate
    information. Should the string be on a different format or an invalid format an error will be
    raised.
    */
    private Date parseMomentInTimeFromFormatAndStringToDate(SimpleDateFormat format, String date) {
        Date found = null;
        try{
            found = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return found;
    }

    // getter and setter for description ------------------------------
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // getter and setter for category ---------------------------------
    @NonNull
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // getter and setter for courseId ---------------------------------
    @NonNull
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    // getter and setter for possibleScore ----------------------------
    public float getPossibleScore() {
        return possibleScore;
    }

    public void setPossibleScore(float possibleScore) {
        this.possibleScore = possibleScore;
    }

    // getter and setter for scoreEarned ------------------------------
    public float getScoreEarned() {
        return scoreEarned;
    }

    public void setScoreEarned(float scoreEarned) {
        this.scoreEarned = scoreEarned;
    }

    // getter and setter for assignmentId -----------------------------
    @NonNull
    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    // getter and setter for categoryId -------------------------------
    @NonNull
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    // To String
    @Override
    public String toString() {
        // this may still need more information to display
        return "Title: " + title + "\n" +
                "Date assigned: " + convertLongDateToString(dateAssigned) + "\n" + // may need to be changed
                "Due date: " + convertLongDateToString(dueDate) + "\n" + // may need to also be changed
                "Due time: " + convertLongTimeToString(dueTime) + "\n" + // same as above
                "Description: " + description + "\n" +
                "Possible score: " + possibleScore;
    }
}
