package com.bgbus.tracker.tracker.configuration.dao;

import com.bgbus.tracker.tracker.model.Tracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  public interface TrackerRepository extends JpaRepository<Tracker,String> {}
