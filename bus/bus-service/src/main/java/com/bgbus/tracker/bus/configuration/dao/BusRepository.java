package com.bgbus.tracker.bus.configuration.dao;

import com.bgbus.tracker.bus.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  public interface BusRepository extends JpaRepository<Bus,String> {}
