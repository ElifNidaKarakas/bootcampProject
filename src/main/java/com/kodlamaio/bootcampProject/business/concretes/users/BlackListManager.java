package com.kodlamaio.bootcampProject.business.concretes.users;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.bootcampProject.business.abstracts.users.ApplicantService;
import com.kodlamaio.bootcampProject.business.abstracts.users.BlackListService;
import com.kodlamaio.bootcampProject.business.constants.BusinessMessages;
import com.kodlamaio.bootcampProject.business.constants.Messages;
import com.kodlamaio.bootcampProject.business.requests.blackList.CreateBlackListRequest;
import com.kodlamaio.bootcampProject.business.requests.blackList.UpdateBlackListRequest;
import com.kodlamaio.bootcampProject.business.responses.blackList.CreateBlackListResponse;
import com.kodlamaio.bootcampProject.business.responses.blackList.GetAllBlackListResponse;
import com.kodlamaio.bootcampProject.business.responses.blackList.GetBlackListResponse;
import com.kodlamaio.bootcampProject.business.responses.blackList.UpdateBlackListResponse;
import com.kodlamaio.bootcampProject.core.utilities.exceptions.BusinessException;
import com.kodlamaio.bootcampProject.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.bootcampProject.core.utilities.results.DataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.ErrorDataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.Result;
import com.kodlamaio.bootcampProject.core.utilities.results.SuccessDataResult;
import com.kodlamaio.bootcampProject.core.utilities.results.SuccessResult;
import com.kodlamaio.bootcampProject.dataAccess.abstracts.BlackListRepository;
import com.kodlamaio.bootcampProject.entities.BlackList;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BlackListManager implements BlackListService {
	
	private BlackListRepository blackListRepository;
	private ModelMapperService modelMapperService;
	private ApplicantService applicantService;
	

	@Override
	public Result delete(int id) {
		this.checkIfBlackListExistById(id);
		this.blackListRepository.deleteById(id);
		return new SuccessResult(Messages.BlackListDeleted);
	}

	@Override
	public DataResult<List<GetAllBlackListResponse>> getAll() {
		List<BlackList> blackLists=this.blackListRepository.findAll();
		List<GetAllBlackListResponse> blackListResponsesItem=blackLists.stream().map(blackList->this.modelMapperService.forResponse().map(blackList, GetAllBlackListResponse.class)).collect(Collectors.toList());
		
		
		return new SuccessDataResult<List<GetAllBlackListResponse>>(blackListResponsesItem, Messages.BlackListListed);
	}

	@Override
	public DataResult<CreateBlackListResponse> add(CreateBlackListRequest createBlackListRequest) {
	checkIfApplicantExists(createBlackListRequest.getApplicantId());
		BlackList blackList=this.modelMapperService.forRequest().map(createBlackListRequest, BlackList.class);
		blackListRepository.save(blackList);
		
		CreateBlackListResponse blackListResponse=this.modelMapperService.forResponse().map(blackList, CreateBlackListResponse.class);
		return new SuccessDataResult<CreateBlackListResponse>(blackListResponse,Messages.BlackListCreate);
	}

	@Override
	public DataResult<UpdateBlackListResponse> update(UpdateBlackListRequest updateBlackListRequest) {
		checkIfApplicantExists(updateBlackListRequest.getApplicantId());
		checkIfBlackListExistById(updateBlackListRequest.getId());

	    BlackList blackList=this.modelMapperService.forRequest().map(updateBlackListRequest, BlackList.class);
	    blackListRepository.save(blackList);
	    
	    UpdateBlackListResponse blackListResponse=this.modelMapperService.forResponse().map(blackList, UpdateBlackListResponse.class);
		return new SuccessDataResult<UpdateBlackListResponse>(blackListResponse, Messages.BlackListUpdate);
	}

	@Override
	public DataResult<GetBlackListResponse> getById(int id) {
		this.checkIfBlackListExistById(id);
		BlackList blackList=this.blackListRepository.findById(id).get();
		 GetBlackListResponse blackListResponse=this.modelMapperService.forResponse().map(blackList, GetBlackListResponse.class);
		return new  SuccessDataResult<GetBlackListResponse>(blackListResponse);
	}

	@Override
	public DataResult<GetBlackListResponse> getByApplicantId(int Applicantid) {
		BlackList blackList = blackListRepository.findByApplicantId(Applicantid);
		if(blackList!=null) {
			GetBlackListResponse blackListResponse = modelMapperService.forResponse().map(blackList, GetBlackListResponse.class);
			return new ErrorDataResult<GetBlackListResponse>(blackListResponse);
		}
			
		return null;
		
	}
	
	public void checkIfBlackListExistById(int id) {
		BlackList blackList=this.blackListRepository.findById(id).orElse(null);
		if(blackList==null) {
			throw new BusinessException(BusinessMessages.BlackListNoExists);
		}
	}
	
	void checkIfApplicantExists(int applicantId) {
		var result=applicantService.getById(applicantId);
		if(result==null) {
			throw new BusinessException(BusinessMessages.ApplicantNoExists);
			
		}
		
	}
	
}

