package com.bgbus.tracker.userservice.service.store;

import java.util.HashMap;
import java.util.Map;

import org.chenile.utils.entity.service.EntityStore;
import com.bgbus.tracker.userservice.model.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.chenile.base.exception.NotFoundException;
import com.bgbus.tracker.userservice.configuration.dao.UserserviceRepository;
import java.util.Optional;

public class UserserviceEntityStore implements EntityStore<Userservice>{
    @Autowired private UserserviceRepository userserviceRepository;

	@Override
	public void store(Userservice entity) {
        userserviceRepository.save(entity);
	}

	@Override
	public Userservice retrieve(String id) {
        Optional<Userservice> entity = userserviceRepository.findById(id);
        if (entity.isPresent()) return entity.get();
        throw new NotFoundException(1500,"Unable to find Userservice with ID " + id);
	}

}
