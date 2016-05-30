package com.bluemedia.app.impl.service;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.joda.time.LocalDateTime;

import com.bluemedia.app.impl.dao.RequestHistoryDao;
import com.bluemedia.app.impl.entity.RequestEntity;
import com.bluemedia.app.impl.entity.RequestHistoryEntity;

@LocalBean
@Stateless
public class RequestHistoryService {
	
	@EJB
	private RequestHistoryDao requestHistoryDao;
	
	public RequestHistoryEntity createRequestHistoryEntity(final RequestEntity requestEntity){
		
		RequestHistoryEntity requestHistoryEntity = new RequestHistoryEntity();
		requestHistoryEntity.setFromStatus(requestEntity.getSavedStatus());
		requestHistoryEntity.setToStatus(requestEntity.getStatus());
		requestHistoryEntity.setRequest(requestEntity);
		return save(requestHistoryEntity);
	}
	
	private RequestHistoryEntity save(RequestHistoryEntity requestHistory){
		requestHistory.setLocalDateTime(new LocalDateTime());
		return requestHistoryDao.store(requestHistory);
	}
}
