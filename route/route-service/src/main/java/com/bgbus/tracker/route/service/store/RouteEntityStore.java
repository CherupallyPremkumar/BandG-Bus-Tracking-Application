package com.bgbus.tracker.route.service.store;

import java.util.HashMap;
import java.util.Map;

import org.chenile.utils.entity.service.EntityStore;
import com.bgbus.tracker.route.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.chenile.base.exception.NotFoundException;
import com.bgbus.tracker.route.configuration.dao.RouteRepository;
import java.util.Optional;

public class RouteEntityStore implements EntityStore<Route>{
    @Autowired private RouteRepository routeRepository;

	@Override
	public void store(Route entity) {
        routeRepository.save(entity);
	}

	@Override
	public Route retrieve(String id) {
        Optional<Route> entity = routeRepository.findById(id);
        if (entity.isPresent()) return entity.get();
        throw new NotFoundException(1500,"Unable to find Route with ID " + id);
	}

}
