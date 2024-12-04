package com.bgbus.tracker.tracker.service.store;

import java.util.HashMap;
import java.util.Map;

import org.chenile.utils.entity.service.EntityStore;
import com.bgbus.tracker.tracker.model.Tracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.chenile.base.exception.NotFoundException;
import com.bgbus.tracker.tracker.configuration.dao.TrackerRepository;
import java.util.Optional;

public class TrackerEntityStore implements EntityStore<Tracker>{
    @Autowired private TrackerRepository trackerRepository;

	@Override
	public void store(Tracker entity) {
        trackerRepository.save(entity);
	}

	@Override
	public Tracker retrieve(String id) {
        Optional<Tracker> entity = trackerRepository.findById(id);
        if (entity.isPresent()) return entity.get();
        throw new NotFoundException(1500,"Unable to find Tracker with ID " + id);
	}

}
