package com.training.gradesubmission.repository;

import com.training.gradesubmission.entity.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {
}
