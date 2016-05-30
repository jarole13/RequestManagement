package com.bluemedia.app.impl.dao;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.testfun.jee.EjbWithMockitoRunner;

import com.bluemedia.app.impl.entity.RequestEntity;
import com.bluemedia.app.impl.entity.RequestEntityBuilder;
import com.bluemedia.app.impl.enums.RequestStatus;
import com.google.common.collect.Lists;

import static org.fest.assertions.api.Assertions.*;

import java.util.List;

@RunWith(EjbWithMockitoRunner.class)
public class RequestDaoTest extends BaseDaoTest {

	@EJB
	private RequestDao requestDao;

	@PersistenceContext(name = "test")
	private EntityManager em;

	@Test
	public void testStoreRequest() {
		RequestEntity request = new RequestEntity();
		request.setName("TEST NAME");
		request.setContent("TEST Content");
		request.setStatus(RequestStatus.CREATED);
		RequestEntity storedEntity = requestDao.store(request);
		assertThat(storedEntity.getId()).isNotNull();
		assertThat(storedEntity.getStatus()).isEqualTo(storedEntity.getSavedStatus());
	}

	@Test
	public void testFindById() {
		Long id = prepareRequest().getId();
		RequestEntity foundEntity = requestDao.findById(id);
		assertThat(foundEntity).isNotNull();
		assertThat(foundEntity.getStatus()).isEqualTo(foundEntity.getSavedStatus());
	}

	@Test
	public void checkIfContentChangedTest() {
		RequestEntity request = prepareRequest();
		assertThat(requestDao.checkIfContentChanged(request)).isFalse();
		request.setContent("NEW CONTENT");
		assertThat(requestDao.checkIfContentChanged(request)).isTrue();
	}

	@Test
	public void testPagination() {
		prepareRequests();
		assertThat(requestDao.findRequestByNameOrStatus("TEST1", null, 0, 10)).hasSize(10);
		assertThat(requestDao.findRequestByNameOrStatus("TEST1", null, 1, 10)).hasSize(3);
		assertThat(requestDao.findRequestByNameOrStatus(null, RequestStatus.ACCEPTED, 0, 10)).hasSize(10);
		assertThat(requestDao.findRequestByNameOrStatus(null, RequestStatus.ACCEPTED, 1, 10)).hasSize(10);
		assertThat(requestDao.findRequestByNameOrStatus(null, RequestStatus.ACCEPTED, 2, 10)).hasSize(8);
		assertThat(requestDao.findRequestByNameOrStatus("TEST2", RequestStatus.VERIFIED, 0, 10)).hasSize(8);
	}

	private void prepareRequests() {
		generateRequest(5, RequestStatus.CREATED, null, "TEST1");
		generateRequest(8, RequestStatus.VERIFIED, RequestStatus.CREATED, "TEST2");
		generateRequest(8, RequestStatus.ACCEPTED, RequestStatus.VERIFIED, "TEST1");
		generateRequest(20, RequestStatus.ACCEPTED, RequestStatus.VERIFIED, "TEST3");
	}

	private void generateRequest(int size, RequestStatus requestStatus, RequestStatus savedStatus, String name) {
		for (int i = 0; i < size; i++) {
			em.persist(new RequestEntityBuilder().withName(name).withStatus(requestStatus).withSavedStatus(savedStatus)
					.withContent("TEST").build());
			em.flush();
		}
	}

	private RequestEntity prepareRequest() {
		RequestEntity request = new RequestEntity();
		request.setName("TEST NAME");
		request.setContent("TEST Content");
		request.setStatus(RequestStatus.CREATED);
		em.persist(request);

		return request;
	}

	@Override
	public BaseDao<?> getDaoToTest() {
		return requestDao;
	}

}
