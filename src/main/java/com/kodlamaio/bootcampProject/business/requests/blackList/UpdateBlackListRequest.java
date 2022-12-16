package com.kodlamaio.bootcampProject.business.requests.blackList;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBlackListRequest {
	private int id;

	@NotNull
	private int applicantId;
	
	@NotNull
	//@JsonFormat(shape=JsonFormat.Shape.STRING,pattern ="dd/MM/yyyy")
	private LocalDate dateAdded;
	
	@NotNull
	@Length(min=5,max=50)
	private String reason;
	

}
