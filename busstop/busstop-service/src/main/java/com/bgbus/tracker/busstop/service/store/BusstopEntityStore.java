package com.bgbus.tracker.busstop.service.store;

import java.util.HashMap;
import java.util.Map;

import org.chenile.utils.entity.service.EntityStore;
import com.bgbus.tracker.busstop.model.Busstop;
import org.springframework.beans.factory.annotation.Autowired;
import org.chenile.base.exception.NotFoundException;
import com.bgbus.tracker.busstop.configuration.dao.BusstopRepository;
import java.util.Optional;

public class BusstopEntityStore implements EntityStore<Busstop>{
    @Autowired private BusstopRepository busstopRepository;

	@Override
	public void store(Busstop entity) {
        busstopRepository.save(entity);
	}

	@Override
	public Busstop retrieve(String id) {
        Optional<Busstop> entity = busstopRepository.findById(id);
        if (entity.isPresent()) return entity.get();
        throw new NotFoundException(1500,"Unable to find Busstop with ID " + id);
	}

}
