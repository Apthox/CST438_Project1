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

    @ColumnInfo(name = "percentage")
    public double percentage;

    public String categoryName;
    public double categoryPercentage;

    public Category(int cid, String categoryName, double categoryPercentage) {
        this.cid = cid;
        this.categoryName = categoryName;
        this.categoryPercentage = categoryPercentage;
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

