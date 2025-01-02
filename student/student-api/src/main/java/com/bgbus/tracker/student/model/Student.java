package com.bgbus.tracker.student.model;

import java.io.Serial;
import java.time.LocalDate;

import com.bgbus.tracker.college.model.College;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import org.chenile.jpautils.entity.AbstractJpaStateEntity;
@Entity
@Table(name = "student")
public class Student extends AbstractJpaStateEntity{
    @Serial
	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(max = 100)
	@Column(name = "student_name", nullable = false)
	private String studentName;

	@NotNull
	@Email
	@Column(name = "student_email", unique = true, nullable = false)
	private String studentEmail;

	@NotNull
	@Size(min = 8)
	@Column(name = "student_password", nullable = false)
	private String studentPassword;

	@NotNull
	@Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "Invalid phone number format")
	@Column(name = "student_phone", unique = true, nullable = false)
	private String studentPhone;

	@Size(max = 255)
	@Column(name = "student_address")
	private String studentAddress;

	@NotNull
	@Pattern(regexp = "Male|Female|Other", message = "Gender must be 'Male', 'Female', or 'Other'")
	@Column(name = "student_gender", nullable = false)
	private String studentGender;

	@PastOrPresent
	@Column(name = "student_birthday")
	private LocalDate studentBirthday;

	@Size(max = 150)
	@Column(name = "student_school")
	private String studentSchool;

	@OneToOne
	private College college;

}
