package edu.csumb.project1_cst438.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

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

    private int score;
    private int assignment_id;
    private int student_id;
    private int course_id;

    public int getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(int grade_id) {
        this.grade_id = grade_id;
    }

    public int getScore() {
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

//    This function will return the average grades
//    public double averageGrade(List<double> grades){
//        int average = 0;
//        int count = 0;
//
//        for(int i = 0; i < grades.length(); i++){
//            average += grades[i];
//            count += 1;
//        }
//
//        return average/count;
//    }

//    This Function Displays the Letter Grade
//    public char displayLetterGrade(int gradePoints){
//      if(gradePoints < 100 && gradePoints > 89) {
//          return 'A';
//      }
//      else if(gradePoints < 89 && gradePoints > 79){
//          return 'B';
//      }
//      else if(gradePoints < 79 && gradePoints > 69){
//          return 'C';
//      }
//      else if(gradePoints <69 && gradePoints > 59){
//          return 'D';
//      }
//      else{
//          return 'F';
//      }
//    }

}
