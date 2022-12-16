package com.kodlamaio.bootcampProject.business.requests.application;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateApplicationRequest {
	
	@NotNull
	@Min(value = 1)
	private int bootcampId;

	@NotNull
	@Min(value = 1)
	private int applicantId;

	@NotNull
	@Min(value = 1)
	@Max(value = 4)
	private int state;
}
