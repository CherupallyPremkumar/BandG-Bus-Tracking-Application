package com.bgbus.tracker.tracker.model;

import java.io.Serial;

import com.bgbus.tracker.bus.model.Bus;
import com.bgbus.tracker.model.Location;
import jakarta.persistence.*;
import org.chenile.jpautils.entity.AbstractJpaStateEntity;
import org.hibernate.mapping.ToOne;

@Entity
@Table(name = "bus_loction")
public class Tracker extends AbstractJpaStateEntity{
    @Serial
	private static final long serialVersionUID = 1L;
//	@OneToOne
//	@JoinColumn(name = "bus_id")
//	private Bus busId;
	@Embedded
	private Location location;

}
