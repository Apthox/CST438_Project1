package edu.csumb.project1_cst438.Model;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity (tableName = "category")
public class Category {
    @PrimaryKey(autoGenerate = true)
    private int cid; //category ID

    @ColumnInfo(name = "category_name")
    public String category_name;

    @ColumnInfo(name = "category_percentage")
    public double category_percentage;

    private String categoryName;
    private double categoryPercentage;
    private int userID;

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    private int courseID;

    public Category(int cid, String categoryName, double categoryPercentage) {
        this.cid = cid;
        this.categoryName = categoryName;
        this.categoryPercentage = categoryPercentage;
    }

    public int getUserID() {
        return userID;
    }

    public int getCourseID() {
        return courseID;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getCategoryPercentage() {
        return categoryPercentage;
    }

    public void setCategoryPercentage(double categoryPercentage) {
        this.categoryPercentage = categoryPercentage;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryName='" + categoryName + '\'' +
                '}';
    }

}

