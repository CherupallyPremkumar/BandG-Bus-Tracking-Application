package com.bgbus.tracker.student.service.store;

import java.util.HashMap;
import java.util.Map;

import org.chenile.utils.entity.service.EntityStore;
import com.bgbus.tracker.student.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.chenile.base.exception.NotFoundException;
import com.bgbus.tracker.student.configuration.dao.StudentRepository;
import java.util.Optional;

public class StudentEntityStore implements EntityStore<Student>{
    @Autowired private StudentRepository studentRepository;

	@Override
	public void store(Student entity) {
        studentRepository.save(entity);
	}

	@Override
	public Student retrieve(String id) {
        Optional<Student> entity = studentRepository.findById(id);
        if (entity.isPresent()) return entity.get();
        throw new NotFoundException(1500,"Unable to find Student with ID " + id);
	}

}
