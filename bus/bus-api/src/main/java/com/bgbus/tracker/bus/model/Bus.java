package com.bgbus.tracker.bus.model;

import java.io.Serial;

import jakarta.persistence.*;
import org.chenile.jpautils.entity.AbstractJpaStateEntity;
import org.hibernate.mapping.ToOne;

@Entity
@Table(name = "bus")
public class Bus extends AbstractJpaStateEntity{
    @Serial
	private static final long serialVersionUID = 1L;

	private int capacity;


	private String licensePlate;

	@OneToOne(fetch = FetchType.EAGER)
	private Attendance attendance;

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public Attendance getAttendance() {
		return attendance;
	}

	public void setAttendance(Attendance attendance) {
		this.attendance = attendance;
	}
}
