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

    public void displayName(String categoryName){
        System.out.print(categoryName);
    }

    public void displayGrades(double gradePoints){
        if(gradePoints <= 100 && gradePoints > 89) {
            System.out.print('A');
        }
        else if(gradePoints <= 89 && gradePoints > 79){
            System.out.print('B');
        }
        else if(gradePoints <= 79 && gradePoints > 69){
            System.out.print('C');
        }
        else if(gradePoints <= 69 && gradePoints > 59){
            System.out.print('D');
        }
        else if (gradePoints < 59){
            System.out.print('F');
        }
        else {
            System.out.print("NULL");
        }
    }
}
