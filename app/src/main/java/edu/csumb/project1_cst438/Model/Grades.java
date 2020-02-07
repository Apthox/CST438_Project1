package edu.csumb.project1_cst438.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Grades {

    /*
    TODO:
    Collaboarte with Ed for the Assignment Display
    Fix Primary Key/Foreign Key
    Create Layout
    Create Functions to return Average Grade of Assignment Category (i.e. Quizes, Exams, Projects)


    */

    @PrimaryKey
    private int grade_id;

    private double score;
    private int assignment_id;
    private int student_id;
    private int course_id;


    public int getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(int grade_id) {
        this.grade_id = grade_id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAssignment_id() {
        return assignment_id;
    }

    public void setAssignment_id(int assignment_id) {
        this.assignment_id = assignment_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    List<Double> gradesSample = Arrays.asList(99.00, 10.34, 32.23, 23.43, 100.00);


    //This function will return the average grades
    public double averageGrade(List<Double> grades){
        int average = 0;
        int count = 0;

        for(int i = 0; i < grades.size(); i++){
            average += grades.get(i);
            count += 1;
        }

        return average/count;
    }


//    This Function Displays the Letter Grade
    public char letterGrade(double gradePoints){
      if(gradePoints < 100 && gradePoints > 89) {
          return 'A';
      }
      else if(gradePoints < 89 && gradePoints > 79){
          return 'B';
      }
      else if(gradePoints < 79 && gradePoints > 69){
          return 'C';
      }
      else if(gradePoints <69 && gradePoints > 59){
          return 'D';
      }
      else{
          return 'F';
      }
    }

    double average = averageGrade(gradesSample);
    char letterGradeSample = letterGrade(average);

}
