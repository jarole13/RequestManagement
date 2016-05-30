package com.bluemedia.app.impl.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.bluemedia.app.impl.dao.RequestDao;
import com.bluemedia.app.impl.entity.RequestEntity;
import com.bluemedia.app.impl.enums.RequestStatus;
import com.bluemedia.app.impl.util.UniqueNumberGenerator;

@LocalBean
@Stateless
public class RequestService {

	@EJB
	private RequestDao requestDao;
	
	@EJB
	private RequestHistoryService requestHistoryService;

	public RequestEntity create(String name, String content) {
		RequestEntity requestEntity = new RequestEntity();
		requestEntity.setName(name);
		requestEntity.setContent(content);
		return changeStatusAndSave(requestEntity, RequestStatus.CREATED);
	}

	public RequestEntity verify(Long id) {

		return changeStatusAndSave(findRequest(id), RequestStatus.VERIFIED);
	}

	public RequestEntity accept(Long id) {
		return changeStatusAndSave(findRequest(id), RequestStatus.ACCEPTED);
	}

	public RequestEntity publish(Long id) {
		RequestEntity request = findRequest(id);
		request.setNumber(UniqueNumberGenerator.generateUniqueNumber());
		return changeStatusAndSave(request, RequestStatus.PUBLISHED);
	}

	public RequestEntity reject(Long id, String reason) {
		RequestEntity request = requestDao.findById(id);
		request.setReason(reason);
		return changeStatusAndSave(request, RequestStatus.REJECTED);
	}

	public RequestEntity delete(Long id, String reason) {
		RequestEntity request = requestDao.findById(id);
		request.setReason(reason);
		return changeStatusAndSave(request, RequestStatus.DELETED);
	}

	public RequestEntity findRequest(Long id) {
		return requestDao.findById(id);
	}

	public List<RequestEntity> findByNameOrStatus(String name, RequestStatus status, Integer recordsOnSite,
			Integer pageIndex) {
		return requestDao.findRequestByNameOrStatus(name, status, pageIndex, recordsOnSite);
	}
	
	public RequestEntity modifyRequestContent(Long id, String newContent){
		RequestEntity request = findRequest(id);
		request.setName(newContent);
		return saveRequest(request);
	}

	private RequestEntity changeStatusAndSave(RequestEntity request, RequestStatus status) {
		request.setStatus(status);
		return saveRequest(request);
	}
	
	private RequestEntity saveRequest(RequestEntity request){
		if(request.getStatus()!=RequestStatus.CREATED && request.getStatus() != request.getSavedStatus()){
			requestHistoryService.createRequestHistoryEntity(request);
		}
		return requestDao.store(request);
	}

}
