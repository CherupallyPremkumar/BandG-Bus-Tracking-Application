package com.bgbus.tracker.student.configuration.dao;

import com.bgbus.tracker.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  public interface StudentRepository extends JpaRepository<Student,String> {}
