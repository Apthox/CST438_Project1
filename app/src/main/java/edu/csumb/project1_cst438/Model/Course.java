package edu.csumb.project1_cst438.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
@Entity
public class Course {
    /**
     * fields:
     *
     * constructor order, title,instructor,description,start date, end date,courseID
     *
     */
    @PrimaryKey(autoGenerate = true)
    private Integer mUniqueID;

    //@ColumnInfo
    private Integer mCourseID;

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    private int UserID;

    private String mInstructor,mCourseTitle,mCourseDescription,mStartDate,mEndDate;



    public Course(String mCourseTitle, String mInstructor, String mCourseDescription, String mStartDate, String mEndDate, Integer mCourseID, int UserID) {
        this.mInstructor = mInstructor;
        this.mCourseTitle = mCourseTitle;
        this.mCourseDescription = mCourseDescription;
        this.mStartDate = mStartDate;
        this.mEndDate = mEndDate;
        this.mCourseID = mCourseID;
        this.UserID = UserID;
    }

    public String getInstructor() {
        return mInstructor;
    }

    public void setInstructor(String mInstructorName) {
        this.mInstructor = mInstructorName;
    }

    public String getCourseTitle() {
        return mCourseTitle;
    }

    public void setCourseTitle(String mCourseTitle) {
        this.mCourseTitle = mCourseTitle;
    }

    public String getCourseDescription() {
        return mCourseDescription;
    }

    public void setCourseDescription(String mCourseDescription) {
        this.mCourseDescription = mCourseDescription;
    }

    public String getStartDate() {
        return mStartDate;
    }

    public void setCourseStartDate(String mStartDate) {
        this.mStartDate = mStartDate;
    }

    public String getEndDate() {
        return mEndDate;
    }

    public void setCourseEndDate(String mEndDate) {
        this.mEndDate = mEndDate;
    }

    public Integer getCourseID() {
        return mCourseID;
    }

    public void setCourseID(Integer mCourseID) {
        this.mCourseID = mCourseID;
    }

    public Integer getUniqueID() {
        return mUniqueID;
    }

    public void setUniqueID(Integer mUniqueID) {
        this.mUniqueID = mUniqueID;
    }

    @Override
    public String toString() {
        return "CourseInfo: \n\n\t" + mCourseTitle + "\n\n\t" + mCourseDescription + "\n\n\t" +
                mInstructor + "\n\n\t" + mCourseID + "\n\n\t" + mStartDate + "\n\n\t" + mEndDate;
    }
}
