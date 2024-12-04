package com.bgbus.tracker.route.configuration.dao;

import com.bgbus.tracker.route.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  public interface RouteRepository extends JpaRepository<Route,String> {}
