package com.bgbus.tracker.busstop.model;

import java.io.Serial;

import jakarta.persistence.*;
import com.bgbus.tracker.model.*;

import org.chenile.jpautils.entity.AbstractJpaStateEntity;
@Entity
@Table(name = "busstop")
public class Busstop extends AbstractJpaStateEntity{
    @Serial
	private static final long serialVersionUID = 1L;
	@Column(name = "busstop_name", nullable = false, length = 100)
	private String busstopName;

	@Column(name = "busstop_code", nullable = false, unique = true, length = 20)
	private String busstopCode;

	@Column(name = "busstop_type", length = 50)
	private String busstopType;

	@Embedded
	private Location location;

}
