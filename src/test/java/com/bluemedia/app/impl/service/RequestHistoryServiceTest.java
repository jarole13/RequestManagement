package com.bluemedia.app.impl.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.fest.assertions.api.Assertions.*;
import com.bluemedia.app.impl.dao.RequestHistoryDao;
import com.bluemedia.app.impl.entity.RequestEntity;
import com.bluemedia.app.impl.entity.RequestEntityBuilder;
import com.bluemedia.app.impl.entity.RequestHistoryEntity;
import com.bluemedia.app.impl.enums.RequestStatus;

public class RequestHistoryServiceTest {

	@Mock
	private RequestHistoryDao requestHistoryDao;

	@InjectMocks
	private RequestHistoryService requestHistoryService;
	
	ArgumentCaptor<RequestHistoryEntity> requestCaptor = ArgumentCaptor.forClass(RequestHistoryEntity.class);
	
	@Before
	public void setUp(){
		requestHistoryService = new RequestHistoryService();
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void createRequestHistoryTest(){
		RequestEntity request = new RequestEntityBuilder().withId(1L).withStatus(RequestStatus.VERIFIED).withSavedStatus(RequestStatus.CREATED).build();
		requestHistoryService.createRequestHistoryEntity(request);
		Mockito.verify(requestHistoryDao, Mockito.only()).store(requestCaptor.capture());
		
		RequestHistoryEntity requestHistory = requestCaptor.getValue();
		assertThat(requestHistory.getFromStatus()).isEqualTo(request.getSavedStatus());
		assertThat(requestHistory.getToStatus()).isEqualTo(request.getStatus());
		assertThat(requestHistory.getLocalDateTime()).isNotNull();
		assertThat(requestHistory.getRequest()).isEqualTo(request);
	}
}
