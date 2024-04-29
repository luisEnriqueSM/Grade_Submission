package com.training.gradesubmission.expection;

public class StudentNotEnrolledException extends RuntimeException{

    public StudentNotEnrolledException(Long studentId, Long courseId) {
        super("The student id '" + studentId + "' is not enrolled in the course '" + courseId + "' ");
    }
}
