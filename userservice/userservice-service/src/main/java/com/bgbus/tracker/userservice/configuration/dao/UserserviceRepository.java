package com.bgbus.tracker.userservice.configuration.dao;

import com.bgbus.tracker.userservice.model.Userservice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  public interface UserserviceRepository extends JpaRepository<Userservice,String> {}
