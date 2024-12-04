package com.bgbus.tracker.busstop.configuration.dao;

import com.bgbus.tracker.busstop.model.Busstop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  public interface BusstopRepository extends JpaRepository<Busstop,String> {}
