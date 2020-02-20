package edu.csumb.project1_cst438.Model;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity (tableName = "category")
public class Category {
    @PrimaryKey(autoGenerate = true)
    private int uCID; //category ID

    @ColumnInfo(name = "category_name")
    public String category_name;

    @ColumnInfo(name = "category_percentage")
    public double category_percentage;

    private int cid;
    private String categoryName;
    private double categoryPercentage;
    private int userID;
    private int courseID;

    public Category(int cid, String categoryName, double categoryPercentage) {
        this.cid = cid;
        this.categoryName = categoryName;
        this.categoryPercentage = categoryPercentage;
    }

    public int getuserID() {
        return userID;
    }

    public int getcourseID() {
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

    public void setCategoryPerecentage(double categoryPercentage) {
        this.categoryPercentage = categoryPercentage;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryName='" + categoryName + '\'' +
                '}';
    }
}

