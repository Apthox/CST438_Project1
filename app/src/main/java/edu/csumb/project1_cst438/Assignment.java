package edu.csumb.project1_cst438;/*
* edu.csumb.project1_cst438.Assignment class is intended to store the information pertaining to an assignment.
* That is to say holds Title, Date assigned, Due date and time, Description, Possible score
* and Category.
*
* @Author   Juan Eduardo Garcia
*/

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

// TBD @Entity
public class Assignment {

    @PrimaryKey (autoGenerate = true)
    private int assignmentId;

    private String title;
    private Date dateAssigned;
    private Date dueDate;
    private Time dueTime;
    private String description;
    private float possibleScore;
    private String category;

    public Assignment(String title, Date dateAssigned, Date dueDate, Time dueTime,
            String description, float possibleScore, String category) {

        this.title = title;
        this.dateAssigned = dateAssigned;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        this.description = description;
        this.possibleScore = possibleScore;
        this.category = category;
    }

    // Title setter and getter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Date Assigned setter and getter
    public Date getDateAssigned() {
        return dateAssigned;
    }

    public void setDateAssigned(Date dateAssigned) {
        this.dateAssigned = dateAssigned;
    }

    // Title setter and getter
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    // Due time setter and getter
    public Time getDueTime() {
        return dueTime;
    }

    public void setDueTime(Time dueTime) {
        this.dueTime = dueTime;
    }

    // Description setter and getter
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Possible score setter and getter
    public float getPossibleScore() {
        return possibleScore;
    }

    public void setPossibleScore(float possibleScore) {
        this.possibleScore = possibleScore;
    }

    // Category setter and getter
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Function to set the assignment date to today
    public void assignedToday() {
        dateAssigned = new Date();
    }
}
