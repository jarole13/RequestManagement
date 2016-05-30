package com.bluemedia.app.impl.validator;

import org.junit.Test;

import com.bluemedia.app.impl.entity.RequestEntityBuilder;
import com.bluemedia.app.impl.enums.RequestStatus;

public class StatusValidatorTest extends BaseValidatorTest {
	
	@Test
	public void testValidFromCreatedToVerified(){
		StatusValidator statusValidator = new StatusValidator();
		assertValid(statusValidator, new RequestEntityBuilder().withSavedStatus(RequestStatus.CREATED).withStatus(RequestStatus.VERIFIED).build());
	}
	
	@Test
	public void testValidFromCreatedToDeleted(){
		StatusValidator statusValidator = new StatusValidator();
		assertValid(statusValidator, new RequestEntityBuilder().withSavedStatus(RequestStatus.CREATED).withStatus(RequestStatus.DELETED).build());
	}
	
	@Test
	public void testValidFromVerifiedToRejected(){
		StatusValidator statusValidator = new StatusValidator();
		assertValid(statusValidator, new RequestEntityBuilder().withSavedStatus(RequestStatus.VERIFIED).withStatus(RequestStatus.REJECTED).build());
	}
	
	@Test
	public void testValidFromVerifiedToAccepted(){
		StatusValidator statusValidator = new StatusValidator();
		assertValid(statusValidator, new RequestEntityBuilder().withSavedStatus(RequestStatus.VERIFIED).withStatus(RequestStatus.ACCEPTED).build());
	}
	
	@Test
	public void testValidFromAcceptedToPublished(){
		StatusValidator statusValidator = new StatusValidator();
		assertValid(statusValidator, new RequestEntityBuilder().withSavedStatus(RequestStatus.ACCEPTED).withStatus(RequestStatus.PUBLISHED).build());
	}
	
	@Test
	public void testValidFromAcceptedToRejected(){
		StatusValidator statusValidator = new StatusValidator();
		assertValid(statusValidator, new RequestEntityBuilder().withSavedStatus(RequestStatus.ACCEPTED).withStatus(RequestStatus.REJECTED).build());
	}
	
	@Test
	public void testInvalidFromAcceptedToVerified(){
		StatusValidator statusValidator = new StatusValidator();
		assertInvalid(statusValidator, new RequestEntityBuilder().withSavedStatus(RequestStatus.ACCEPTED).withStatus(RequestStatus.VERIFIED).build());
	}
	
	@Test
	public void testInvalidFromAcceptedToCreated(){
		StatusValidator statusValidator = new StatusValidator();
		assertInvalid(statusValidator, new RequestEntityBuilder().withSavedStatus(RequestStatus.ACCEPTED).withStatus(RequestStatus.CREATED).build());
	}
	
	@Test
	public void testInvalidFromAcceptedToDeleted(){
		StatusValidator statusValidator = new StatusValidator();
		assertInvalid(statusValidator, new RequestEntityBuilder().withSavedStatus(RequestStatus.ACCEPTED).withStatus(RequestStatus.DELETED).build());
	}
	
	@Test
	public void testInvalidFromVerifiedToPublished(){
		StatusValidator statusValidator = new StatusValidator();
		assertInvalid(statusValidator, new RequestEntityBuilder().withSavedStatus(RequestStatus.VERIFIED).withStatus(RequestStatus.PUBLISHED).build());
	}
	
	@Test
	public void testInvalidFromVerifiedToCreated(){
		StatusValidator statusValidator = new StatusValidator();
		assertInvalid(statusValidator, new RequestEntityBuilder().withSavedStatus(RequestStatus.VERIFIED).withStatus(RequestStatus.CREATED).build());
	}
	
	@Test
	public void testInvalidFromVerifiedToDeleted(){
		StatusValidator statusValidator = new StatusValidator();
		assertInvalid(statusValidator, new RequestEntityBuilder().withSavedStatus(RequestStatus.VERIFIED).withStatus(RequestStatus.DELETED).build());
	}
	
	@Test
	public void testInvalidFromCreatedToAccepted(){
		StatusValidator statusValidator = new StatusValidator();
		assertInvalid(statusValidator, new RequestEntityBuilder().withSavedStatus(RequestStatus.CREATED).withStatus(RequestStatus.ACCEPTED).build());
	}
	
	@Test
	public void testInvalidFromCreatedToPublished(){
		StatusValidator statusValidator = new StatusValidator();
		assertInvalid(statusValidator, new RequestEntityBuilder().withSavedStatus(RequestStatus.CREATED).withStatus(RequestStatus.PUBLISHED).build());
	}
	
	@Test
	public void testInvalidFromCreatedToRejected(){
		StatusValidator statusValidator = new StatusValidator();
		assertInvalid(statusValidator, new RequestEntityBuilder().withSavedStatus(RequestStatus.CREATED).withStatus(RequestStatus.REJECTED).build());
	}
	
	@Test
	public void testInvalidFromDeletedToRejected(){
		StatusValidator statusValidator = new StatusValidator();
		assertInvalid(statusValidator, new RequestEntityBuilder().withSavedStatus(RequestStatus.DELETED).withStatus(RequestStatus.REJECTED).build());
	}
	
	@Test
	public void testInvalidFromDeletedToCreated(){
		StatusValidator statusValidator = new StatusValidator();
		assertInvalid(statusValidator, new RequestEntityBuilder().withSavedStatus(RequestStatus.DELETED).withStatus(RequestStatus.CREATED).build());
	}
	
	@Test
	public void testInvalidFromDeletedToVerified(){
		StatusValidator statusValidator = new StatusValidator();
		assertInvalid(statusValidator, new RequestEntityBuilder().withSavedStatus(RequestStatus.DELETED).withStatus(RequestStatus.VERIFIED).build());
	}
	
	@Test
	public void testInvalidFromDeletedToAccepted(){
		StatusValidator statusValidator = new StatusValidator();
		assertInvalid(statusValidator, new RequestEntityBuilder().withSavedStatus(RequestStatus.DELETED).withStatus(RequestStatus.ACCEPTED).build());
	}
	
	@Test
	public void testInvalidFromDeletedToPublished(){
		StatusValidator statusValidator = new StatusValidator();
		assertInvalid(statusValidator, new RequestEntityBuilder().withSavedStatus(RequestStatus.DELETED).withStatus(RequestStatus.PUBLISHED).build());
	}
	
	@Test
	public void testInvalidFromRejectedToDeleted(){
		StatusValidator statusValidator = new StatusValidator();
		assertInvalid(statusValidator, new RequestEntityBuilder().withSavedStatus(RequestStatus.REJECTED).withStatus(RequestStatus.DELETED).build());
	}
	
	@Test
	public void testInvalidFromRejectedToCreated(){
		StatusValidator statusValidator = new StatusValidator();
		assertInvalid(statusValidator, new RequestEntityBuilder().withSavedStatus(RequestStatus.REJECTED).withStatus(RequestStatus.CREATED).build());
	}
	
	@Test
	public void testInvalidFromRejectedToVerified(){
		StatusValidator statusValidator = new StatusValidator();
		assertInvalid(statusValidator, new RequestEntityBuilder().withSavedStatus(RequestStatus.REJECTED).withStatus(RequestStatus.VERIFIED).build());
	}
	
	@Test
	public void testInvalidFromRejectedToAccepted(){
		StatusValidator statusValidator = new StatusValidator();
		assertInvalid(statusValidator, new RequestEntityBuilder().withSavedStatus(RequestStatus.REJECTED).withStatus(RequestStatus.ACCEPTED).build());
	}
	
	@Test
	public void testInvalidFromRejectedToPublished(){
		StatusValidator statusValidator = new StatusValidator();
		assertInvalid(statusValidator, new RequestEntityBuilder().withSavedStatus(RequestStatus.REJECTED).withStatus(RequestStatus.PUBLISHED).build());
	}
}
