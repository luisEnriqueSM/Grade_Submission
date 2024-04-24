package com.training.gradesubmission.service;

import com.training.gradesubmission.entity.Course;

import java.util.List;

public interface CourseService {
    Course getCourse(Long id);
    Course saveCourse(Course course);
    void deleteCourse(Long id);
    List<Course> getCourses();
}
