package com.bgbus.tracker.college.configuration.dao;

import com.bgbus.tracker.college.model.College;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  public interface CollegeRepository extends JpaRepository<College,String> {}
