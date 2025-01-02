package com.bgbus.tracker.college.service.store;

import java.util.HashMap;
import java.util.Map;

import org.chenile.utils.entity.service.EntityStore;
import com.bgbus.tracker.college.model.College;
import org.springframework.beans.factory.annotation.Autowired;
import org.chenile.base.exception.NotFoundException;
import com.bgbus.tracker.college.configuration.dao.CollegeRepository;
import java.util.Optional;

public class CollegeEntityStore implements EntityStore<College>{
    @Autowired private CollegeRepository collegeRepository;

	@Override
	public void store(College entity) {
        collegeRepository.save(entity);
	}

	@Override
	public College retrieve(String id) {
        Optional<College> entity = collegeRepository.findById(id);
        if (entity.isPresent()) return entity.get();
        throw new NotFoundException(1500,"Unable to find College with ID " + id);
	}

}
