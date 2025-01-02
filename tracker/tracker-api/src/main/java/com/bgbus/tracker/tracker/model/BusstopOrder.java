package com.bgbus.tracker.tracker.model;

import com.bgbus.tracker.busstop.model.Busstop;
import com.bgbus.tracker.route.model.Route;
import jakarta.persistence.*;
import org.chenile.jpautils.entity.BaseJpaEntity;
import org.chenile.utils.entity.model.BaseEntity;

import java.util.List;
@Entity
public class BusstopOrder extends BaseJpaEntity {

    @ManyToOne
    @JoinColumn(name = "BUS_SCHEDULE_ID", nullable = false)
    private BusSchedule busSchedule;

    @ManyToOne
    @JoinColumn(name = "BUSSTOP_ID", nullable = false)
    private Busstop busstop;

    @Column(name = "BUSSTOP_ORDER")
    private int busStopOrder;
}
