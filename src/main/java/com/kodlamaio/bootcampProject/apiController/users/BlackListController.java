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

import com.kodlamaio.bootcampProject.business.abstracts.users.BlackListService;
import com.kodlamaio.bootcampProject.business.requests.blackList.CreateBlackListRequest;
import com.kodlamaio.bootcampProject.business.requests.blackList.UpdateBlackListRequest;
import com.kodlamaio.bootcampProject.business.responses.blackList.CreateBlackListResponse;
import com.kodlamaio.bootcampProject.business.responses.blackList.GetAllBlackListResponse;
import com.kodlamaio.bootcampProject.business.responses.blackList.GetBlackListResponse;
import com.kodlamaio.bootcampProject.business.responses.blackList.UpdateBlackListResponse;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@RequestMapping("/api/blackLists")
@RestController
public class BlackListController {
private BlackListService blackListService;

@GetMapping("/{id}")
public DataResult<GetBlackListResponse> getById(@PathVariable int id) {
	return blackListService.getById(id);
}

@GetMapping()
public DataResult<List<GetAllBlackListResponse>> getAll() {
	return blackListService.getAll();
}


@PostMapping()
public DataResult<CreateBlackListResponse> add(@Valid @RequestBody CreateBlackListRequest createBlackListRequest){
	return this.blackListService.add(createBlackListRequest);
}

@PutMapping()
public DataResult<UpdateBlackListResponse> update (@Valid@RequestBody UpdateBlackListRequest updateBlackListRequest){
	return this.blackListService.update(updateBlackListRequest);
}

@DeleteMapping("/{id}")
public Result delete(@PathVariable int id) {
	return this.blackListService.delete(id);
}
}

