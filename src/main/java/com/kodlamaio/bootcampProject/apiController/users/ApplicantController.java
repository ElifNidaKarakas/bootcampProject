package com.kodlamaio.bootcampProject.apiController.users;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.bootcampProject.business.abstracts.users.ApplicantService;
import com.kodlamaio.bootcampProject.business.requests.applicant.CreateApplicantRequest;
import com.kodlamaio.bootcampProject.business.requests.applicant.UpdateApplicantRequest;
import com.kodlamaio.bootcampProject.business.responses.applicant.CreateApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.applicant.GetAllApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.applicant.GetApplicantResponse;
import com.kodlamaio.bootcampProject.business.responses.applicant.UpdateApplicantResponse;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;

import lombok.AllArgsConstructor;

@RequestMapping("/api/applicants")
@AllArgsConstructor
@RestController
public class ApplicantController {
	private ApplicantService applicantService;

	@GetMapping("/{id}")
	public DataResult<GetApplicantResponse> getById(@PathVariable int id) {
		return applicantService.getById(id);
	}

	@GetMapping()
	public DataResult<List<GetAllApplicantResponse>> getAll() {
		return applicantService.getAll();
	}

	@PostMapping
	public DataResult<CreateApplicantResponse> add(@Valid
			@RequestBody CreateApplicantRequest createApplicantRequest) {
		return this.applicantService.add(createApplicantRequest);
	}

	@DeleteMapping("/{id}")
	public Result delete(@PathVariable int id) {
		return this.applicantService.delete(id);
	}

	@PutMapping
	public DataResult<UpdateApplicantResponse> update(@Valid @RequestBody UpdateApplicantRequest updateApplicantRequest) {
		return this.applicantService.update(updateApplicantRequest);
	}
}
