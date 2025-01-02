package com.bgbus.tracker.college.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.chenile.jpautils.entity.AbstractJpaStateEntity;

import java.io.Serial;
@Entity
@Table(name = "college")
public class College extends AbstractJpaStateEntity{
	@Serial

	private static final long serialVersionUID = 1L;
	private String collegeName;
	private String collegeAddress;
	private String collegePhone;
	private String collegeEmail;



	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getCollegeAddress() {
		return collegeAddress;
	}

	public void setCollegeAddress(String collegeAddress) {
		this.collegeAddress = collegeAddress;
	}

	public String getCollegePhone() {
		return collegePhone;
	}

	public void setCollegePhone(String collegePhone) {
		this.collegePhone = collegePhone;
	}

	public String getCollegeEmail() {
		return collegeEmail;
	}

	public void setCollegeEmail(String collegeEmail) {
		this.collegeEmail = collegeEmail;
	}
}
