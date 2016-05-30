package com.bluemedia.app.impl.validator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.bluemedia.app.impl.dao.RequestDao;
import com.bluemedia.app.impl.entity.RequestEntity;
import com.bluemedia.app.impl.entity.RequestEntityBuilder;
import com.bluemedia.app.impl.enums.RequestStatus;

public class ContentChangeAllowedValidatorTest extends BaseValidatorTest {

	@Mock
	private RequestDao requestDao;

	@InjectMocks
	private ContentChangeAllowedValidator validator;

	@Before
	public void setUp() {
		validator = new ContentChangeAllowedValidator();
		MockitoAnnotations.initMocks(this);
		initBaseTest();
	}

	@Test
	public void testValidCaseForCreatedStatus() {
		assertValid(validator,
				new RequestEntityBuilder().withStatus(RequestStatus.CREATED).withContent("TEST CONTENT").build());

		RequestEntity requestEntity = new RequestEntityBuilder().withStatus(RequestStatus.CREATED)
				.withContent("TEST CONTENT").withId(1L).build();
		Mockito.when(requestDao.findById(Mockito.eq(1L))).thenReturn(requestEntity);
		assertValid(validator,
				new RequestEntityBuilder().withStatus(RequestStatus.CREATED).withContent("TEST").withId(1L).build());
	}

	@Test
	public void testValidCaseForVerifiedStatus() {
		RequestEntity requestEntity = new RequestEntityBuilder().withStatus(RequestStatus.VERIFIED)
				.withContent("TEST CONTENT").withId(1L).build();
		Mockito.when(requestDao.findById(Mockito.eq(1L))).thenReturn(requestEntity);
		assertValid(validator,
				new RequestEntityBuilder().withStatus(RequestStatus.VERIFIED).withContent("TEST").withId(1L).build());
	}

	@Test
	public void testValidCaseForAcceptedStatus() {
		RequestEntity requestEntity = new RequestEntityBuilder().withStatus(RequestStatus.ACCEPTED)
				.withContent("TEST CONTENT").withId(1L).build();
		Mockito.when(requestDao.findById(Mockito.eq(1L))).thenReturn(requestEntity);
		assertValid(validator, requestEntity);
	}

	@Test
	public void testValidCaseForPublishedStatus() {
		RequestEntity requestEntity = new RequestEntityBuilder().withStatus(RequestStatus.PUBLISHED)
				.withContent("TEST CONTENT").withId(1L).build();
		Mockito.when(requestDao.findById(Mockito.eq(1L))).thenReturn(requestEntity);
		assertValid(validator, requestEntity);
	}

	@Test
	public void testValidCaseForRejectedStatus() {
		RequestEntity requestEntity = new RequestEntityBuilder().withStatus(RequestStatus.REJECTED)
				.withContent("TEST CONTENT").withId(1L).build();
		Mockito.when(requestDao.findById(Mockito.eq(1L))).thenReturn(requestEntity);
		assertValid(validator, requestEntity);
	}

	@Test
	public void testValidCaseForDeletedStatus() {
		RequestEntity requestEntity = new RequestEntityBuilder().withStatus(RequestStatus.DELETED)
				.withContent("TEST CONTENT").withId(1L).build();
		Mockito.when(requestDao.findById(Mockito.eq(1L))).thenReturn(requestEntity);
		assertValid(validator, requestEntity);
	}

	@Test
	public void testInvalidCaseForAcceptedStatus() {
		RequestEntity requestEntity = new RequestEntityBuilder().withStatus(RequestStatus.ACCEPTED)
				.withContent("TEST CONTENT").withId(1L).build();
		Mockito.when(requestDao.checkIfContentChanged(Mockito.eq(requestEntity))).thenReturn(true);
		assertInvalid(validator, requestEntity);
	}

	@Test
	public void testInvalidCaseForPublishedStatus() {
		RequestEntity requestEntity = new RequestEntityBuilder().withStatus(RequestStatus.PUBLISHED)
				.withContent("TEST CONTENT").withId(1L).build();
		Mockito.when(requestDao.checkIfContentChanged(Mockito.eq(requestEntity))).thenReturn(true);
		assertInvalid(validator, requestEntity);}

	@Test
	public void testInvalidCaseForRejectedStatus() {
		RequestEntity requestEntity = new RequestEntityBuilder().withStatus(RequestStatus.REJECTED)
				.withContent("TEST CONTENT").withId(1L).build();
		Mockito.when(requestDao.checkIfContentChanged(Mockito.eq(requestEntity))).thenReturn(true);
		assertInvalid(validator, requestEntity);
	}

	@Test
	public void testInvalidCaseForDeletedStatus() {
		RequestEntity requestEntity = new RequestEntityBuilder().withStatus(RequestStatus.DELETED)
				.withContent("TEST CONTENT").withId(1L).build();
		Mockito.when(requestDao.checkIfContentChanged(Mockito.eq(requestEntity))).thenReturn(true);
		assertInvalid(validator, requestEntity);
		}
}
