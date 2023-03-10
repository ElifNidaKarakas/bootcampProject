package com.kodlamaio.bootcampProject.entities.applications;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.kodlamaio.bootcampProject.entities.Bootcamp;
import com.kodlamaio.bootcampProject.entities.users.Applicant;
import com.kodlamaio.bootcampProject.entities.users.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="applications")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Application{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@ManyToOne //bire çok ilişkisi olduğu için
	@JoinColumn(name="applicant_id")
	private Applicant applicant;
	
	@ManyToOne
	@JoinColumn(name="bootcamp_id")
	private Bootcamp bootcamp;
	
	@Column(name="state")
	private int state;
	
}
