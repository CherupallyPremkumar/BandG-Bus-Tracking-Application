package com.bgbus.tracker.route.model;

import com.bgbus.tracker.college.model.College;
import jakarta.persistence.*;
import org.chenile.jpautils.entity.AbstractJpaStateEntity;

import java.io.Serial;
@Entity
@Table(name = "route")
public class Route extends AbstractJpaStateEntity{
    @Serial
	private static final long serialVersionUID = 1L;

	@Column(name = "route_name", nullable = false, length = 100)
	private String routeName;

	@Column(name = "route_code", nullable = false, unique = true, length = 20)
	private String routeCode;

	@Column(name = "route_desc", length = 255)
	private String routeDesc;

	@Column(name = "route_type", length = 50)
	private String routeType;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private College college;
}
