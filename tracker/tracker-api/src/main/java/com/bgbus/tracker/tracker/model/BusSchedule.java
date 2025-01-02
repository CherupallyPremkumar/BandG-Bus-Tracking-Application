package com.bgbus.tracker.tracker.model;


import com.bgbus.tracker.bus.model.Bus;
import com.bgbus.tracker.busstop.model.Busstop;
import com.bgbus.tracker.route.model.Route;
import jakarta.persistence.*;
import org.chenile.jpautils.entity.BaseJpaEntity;
import org.hibernate.mapping.ToOne;

import java.util.List;


@Entity
public class BusSchedule extends BaseJpaEntity {

    @OneToOne
    @JoinColumn(name = "ROUTE_ID")
    private Route route;

    @OneToMany(mappedBy = "busSchedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BusstopOrder> busstopOrders; // Changed to a List
    @OneToOne
    @JoinColumn(name = "BUS_ID")
    private Bus bus;


    // Getters and setters
    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }



    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }


}
