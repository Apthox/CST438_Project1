package edu.csumb.project1_cst438.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Category {
    @PrimaryKey
    public int student_id;

    public String categoryName;
    public String categoryType;
    public double categoryPercentage;

    public Category(String categoryName, String categoryType, double categoryPercentage) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
        this.categoryPercentage = categoryPercentage;
    }

    public String categoryName() {
        return categoryName;
    }

    public void setCategoryNameame(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public double getCategoryPercentage() {
        return categoryPercentage;
    }

    public void setCategoryPercentagePerecentage(double categoryPercentage) {
        this.categoryPercentage = categoryPercentage;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryName='" + categoryName + '\'' +
                '}';
    }
}
