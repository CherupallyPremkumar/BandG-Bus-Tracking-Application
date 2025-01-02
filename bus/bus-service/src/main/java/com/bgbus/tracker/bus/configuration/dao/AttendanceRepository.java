package com.bgbus.tracker.bus.configuration.dao;

import com.bgbus.tracker.bus.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, String> {
}
