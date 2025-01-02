package com.bgbus.tracker.bus.model;

import com.bgbus.tracker.student.model.Student;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import org.chenile.jpautils.entity.AbstractJpaStateEntity;
import org.hibernate.mapping.ToOne;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "attendance")
public class Attendance extends AbstractJpaStateEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @OneToOne
    private Bus bus;

    private int currentAttendance;

}
