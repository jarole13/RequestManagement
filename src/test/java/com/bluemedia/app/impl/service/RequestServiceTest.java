package com.bluemedia.app.impl.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.bluemedia.app.impl.dao.RequestDao;
import com.bluemedia.app.impl.entity.RequestEntity;
import com.bluemedia.app.impl.entity.RequestEntityBuilder;
import com.bluemedia.app.impl.enums.RequestStatus;

import static org.fest.assertions.api.Assertions.*;

public class RequestServiceTest {

	@Mock
	private RequestDao requestDao;

	@Mock
	private RequestHistoryService requestHistoryService;

	@InjectMocks
	private RequestService requestService;

	ArgumentCaptor<RequestEntity> requestCaptor = ArgumentCaptor.forClass(RequestEntity.class);

	@Before
	public void setUp() {
		requestService = new RequestService();
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void createTest() {
		requestService.create("TEST NAME", "TEST CONTENT");
		Mockito.verify(requestDao, Mockito.only()).store(requestCaptor.capture());

		RequestEntity request = requestCaptor.getValue();
		assertThat(request.getContent()).isEqualTo("TEST CONTENT");
		assertThat(request.getName()).isEqualTo("TEST NAME");
		assertThat(request.getStatus()).isEqualTo(RequestStatus.CREATED);
		assertThat(request.getSavedStatus()).isNull();
		assertThat(request.getReason()).isNull();
		assertThat(request.getNumber()).isNull();
	}

	@Test
	public void verifyTest() {
		RequestEntity newRequest = new RequestEntityBuilder().withName("TEST NAME").withContent("TEST CONTENT")
				.withId(1L).withStatus(RequestStatus.CREATED).withSavedStatus(RequestStatus.CREATED).build();
		Mockito.when(requestDao.findById(Mockito.eq(1L))).thenReturn(newRequest);
		requestService.verify(1L);
		Mockito.verify(requestDao, Mockito.times(1)).store(requestCaptor.capture());
		Mockito.verify(requestHistoryService, Mockito.times(1))
				.createRequestHistoryEntity(Mockito.any(RequestEntity.class));

		RequestEntity request = requestCaptor.getValue();
		assertThat(request.getContent()).isEqualTo("TEST CONTENT");
		assertThat(request.getName()).isEqualTo("TEST NAME");
		assertThat(request.getStatus()).isEqualTo(RequestStatus.VERIFIED);
		assertThat(request.getSavedStatus()).isEqualTo(RequestStatus.CREATED);
		assertThat(request.getReason()).isNull();
		assertThat(request.getNumber()).isNull();
	}

	@Test
	public void acceptTest() {
		RequestEntity newRequest = new RequestEntityBuilder().withName("TEST NAME").withContent("TEST CONTENT")
				.withId(1L).withStatus(RequestStatus.VERIFIED).withSavedStatus(RequestStatus.VERIFIED).build();
		Mockito.when(requestDao.findById(Mockito.eq(1L))).thenReturn(newRequest);
		requestService.accept(1L);
		Mockito.verify(requestDao, Mockito.times(1)).store(requestCaptor.capture());
		Mockito.verify(requestHistoryService, Mockito.times(1))
				.createRequestHistoryEntity(Mockito.any(RequestEntity.class));

		RequestEntity request = requestCaptor.getValue();
		assertThat(request.getContent()).isEqualTo("TEST CONTENT");
		assertThat(request.getName()).isEqualTo("TEST NAME");
		assertThat(request.getStatus()).isEqualTo(RequestStatus.ACCEPTED);
		assertThat(request.getSavedStatus()).isEqualTo(RequestStatus.VERIFIED);
		assertThat(request.getReason()).isNull();
		assertThat(request.getNumber()).isNull();
	}

	@Test
	public void publishTest() {
		RequestEntity newRequest = new RequestEntityBuilder().withName("TEST NAME").withContent("TEST CONTENT")
				.withId(1L).withStatus(RequestStatus.ACCEPTED).withSavedStatus(RequestStatus.ACCEPTED).build();
		Mockito.when(requestDao.findById(Mockito.eq(1L))).thenReturn(newRequest);
		requestService.publish(1L);
		Mockito.verify(requestDao, Mockito.times(1)).store(requestCaptor.capture());
		Mockito.verify(requestHistoryService, Mockito.times(1))
				.createRequestHistoryEntity(Mockito.any(RequestEntity.class));

		RequestEntity request = requestCaptor.getValue();
		assertThat(request.getContent()).isEqualTo("TEST CONTENT");
		assertThat(request.getName()).isEqualTo("TEST NAME");
		assertThat(request.getStatus()).isEqualTo(RequestStatus.PUBLISHED);
		assertThat(request.getSavedStatus()).isEqualTo(RequestStatus.ACCEPTED);
		assertThat(request.getReason()).isNull();
		assertThat(request.getNumber()).isNotNull();
	}

	@Test
	public void rejectTest() {
		RequestEntity newRequest = new RequestEntityBuilder().withName("TEST NAME").withContent("TEST CONTENT")
				.withId(1L).withStatus(RequestStatus.VERIFIED).withSavedStatus(RequestStatus.VERIFIED).build();
		Mockito.when(requestDao.findById(Mockito.eq(1L))).thenReturn(newRequest);
		requestService.reject(1L, "TEST REASON");
		Mockito.verify(requestDao, Mockito.times(1)).store(requestCaptor.capture());
		Mockito.verify(requestHistoryService, Mockito.times(1))
				.createRequestHistoryEntity(Mockito.any(RequestEntity.class));

		RequestEntity request = requestCaptor.getValue();
		assertThat(request.getContent()).isEqualTo("TEST CONTENT");
		assertThat(request.getName()).isEqualTo("TEST NAME");
		assertThat(request.getStatus()).isEqualTo(RequestStatus.REJECTED);
		assertThat(request.getSavedStatus()).isEqualTo(RequestStatus.VERIFIED);
		assertThat(request.getReason()).isEqualTo("TEST REASON");
		assertThat(request.getNumber()).isNull();
	}
	
	@Test
	public void deleteTest() {
		RequestEntity newRequest = new RequestEntityBuilder().withName("TEST NAME").withContent("TEST CONTENT")
				.withId(1L).withStatus(RequestStatus.CREATED).withSavedStatus(RequestStatus.CREATED).build();
		Mockito.when(requestDao.findById(Mockito.eq(1L))).thenReturn(newRequest);
		requestService.delete(1L, "TEST REASON");
		Mockito.verify(requestDao, Mockito.times(1)).store(requestCaptor.capture());
		Mockito.verify(requestHistoryService, Mockito.times(1))
				.createRequestHistoryEntity(Mockito.any(RequestEntity.class));

		RequestEntity request = requestCaptor.getValue();
		assertThat(request.getContent()).isEqualTo("TEST CONTENT");
		assertThat(request.getName()).isEqualTo("TEST NAME");
		assertThat(request.getStatus()).isEqualTo(RequestStatus.DELETED);
		assertThat(request.getSavedStatus()).isEqualTo(RequestStatus.CREATED);
		assertThat(request.getReason()).isEqualTo("TEST REASON");
		assertThat(request.getNumber()).isNull();
	}
}
