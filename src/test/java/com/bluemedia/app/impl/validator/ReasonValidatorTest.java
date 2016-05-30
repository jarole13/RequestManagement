package com.bluemedia.app.impl.validator;

import org.junit.Test;

import com.bluemedia.app.impl.entity.RequestEntity;
import com.bluemedia.app.impl.entity.RequestEntityBuilder;
import com.bluemedia.app.impl.enums.RequestStatus;

public class ReasonValidatorTest extends BaseValidatorTest{

	@Test
	public void testValidCaseForCreated(){
		ReasonValidator validator = new ReasonValidator();
		RequestEntity request = new RequestEntityBuilder().withStatus(RequestStatus.CREATED).build();
		assertValid(validator, request);
	}
	
	@Test
	public void testValidCaseForVerified(){
		ReasonValidator validator = new ReasonValidator();
		RequestEntity request = new RequestEntityBuilder().withStatus(RequestStatus.VERIFIED).build();
		assertValid(validator, request);
	}
	
	@Test
	public void testValidCaseForAccepted(){
		ReasonValidator validator = new ReasonValidator();
		RequestEntity request = new RequestEntityBuilder().withStatus(RequestStatus.ACCEPTED).build();
		assertValid(validator, request);
	}
	
	@Test
	public void testValidCaseForPublished(){
		ReasonValidator validator = new ReasonValidator();
		RequestEntity request = new RequestEntityBuilder().withStatus(RequestStatus.PUBLISHED).build();
		assertValid(validator, request);
	}
	
	@Test
	public void testValidCaseForDeleted(){
		ReasonValidator validator = new ReasonValidator();
		RequestEntity request = new RequestEntityBuilder().withStatus(RequestStatus.DELETED).withReason("TEST REASON").build();
		assertValid(validator, request);
	}
	
	@Test
	public void testValidCaseForRejected(){
		ReasonValidator validator = new ReasonValidator();
		RequestEntity request = new RequestEntityBuilder().withStatus(RequestStatus.REJECTED).withReason("TEST REASON").build();
		assertValid(validator, request);
	}
	
	@Test
	public void testInvalidCaseForRejected(){
		ReasonValidator validator = new ReasonValidator();
		RequestEntity request = new RequestEntityBuilder().withStatus(RequestStatus.REJECTED).build();
		assertInvalid(validator, request);
	}
	
	@Test
	public void testInvalidCaseForDeleted(){
		ReasonValidator validator = new ReasonValidator();
		RequestEntity request = new RequestEntityBuilder().withStatus(RequestStatus.DELETED).build();
		assertInvalid(validator, request);
	}
}
