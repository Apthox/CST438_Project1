package edu.csumb.project1_cst438.Model;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity
public class Category {
        @PrimaryKey
        public int cid; //category ID

        @ColumnInfo(name = "category_name")
        public String category_name;

        @ColumnInfo(name = "percentage")
        public Double precentage;

        public String categoryName;
        public double categoryPercentage;

        public Category(){ };

        public Category(String categoryName, double categoryPercentage) {
            this.categoryName = categoryName;
            this.categoryPercentage = categoryPercentage;
        }

        public String categoryName() {
            return categoryName;
        }

        public void setCategoryNameame(String categoryName) {
            this.categoryName = categoryName;
        }

        public double getCaCategorytegoryPercentage() {
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

