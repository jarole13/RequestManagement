package com.bluemedia.app.impl.validator;

import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

import org.junit.Assert;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public abstract class BaseValidatorTest {

	@Mock
	private ConstraintValidatorContext context;

	@Mock
	private ConstraintViolationBuilder violationBuilder;

	@Before
	public void initBaseTest() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(context.buildConstraintViolationWithTemplate(Mockito.anyString())).thenAnswer(new Answer<ConstraintViolationBuilder>() {

			@Override
			public ConstraintViolationBuilder answer(InvocationOnMock invocation) throws Throwable {
				return violationBuilder;
			}
		});
	}

	public <T> void assertInvalid(BaseValidator<?, T> validator, T valueToValidate) {
		assertInvalid(validator, valueToValidate, validator.getMessage(valueToValidate));
	}

	public <T> void assertInvalid(BaseValidator<?, T> validator, T valueToValidate, String expectedMessage) {
		Assert.assertFalse(validator.isValid(valueToValidate, context));
		Mockito.verify(context).buildConstraintViolationWithTemplate(expectedMessage);
	}

	public <T> void assertValid(BaseValidator<?, T> validator, T valueToValidate) {
		Assert.assertTrue("Expected valid bean, but got message: " + validator.getMessage(valueToValidate),
				validator.isValid(valueToValidate, context));
	}
}
