package com.training.gradesubmission.repository;

import com.training.gradesubmission.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long>{

}