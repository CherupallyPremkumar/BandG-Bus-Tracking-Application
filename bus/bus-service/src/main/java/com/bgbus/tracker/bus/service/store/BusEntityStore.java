package com.bgbus.tracker.bus.service.store;

import java.util.HashMap;
import java.util.Map;

import org.chenile.utils.entity.service.EntityStore;
import com.bgbus.tracker.bus.model.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.chenile.base.exception.NotFoundException;
import com.bgbus.tracker.bus.configuration.dao.BusRepository;
import java.util.Optional;

public class BusEntityStore implements EntityStore<Bus>{
    @Autowired private BusRepository busRepository;

	@Override
	public void store(Bus entity) {
        busRepository.save(entity);
	}

	@Override
	public Bus retrieve(String id) {
        Optional<Bus> entity = busRepository.findById(id);
        if (entity.isPresent()) return entity.get();
        throw new NotFoundException(1500,"Unable to find Bus with ID " + id);
	}

}
