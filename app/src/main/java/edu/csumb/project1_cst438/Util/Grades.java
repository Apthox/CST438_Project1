package edu.csumb.project1_cst438.Util;

import java.util.List;

public class Grades {

    //This function will return the average grades
    public double averageGrade(List<Double> grades){
        double average = 0.00;
        double count = 0;

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
}
