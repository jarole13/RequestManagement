package com.bluemedia.app.impl.validator;

import java.lang.annotation.Annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public abstract class BaseValidator<A extends Annotation, T> implements ConstraintValidator<A, T> {
	
	private void addCustomConstraintViolation(final ConstraintValidatorContext context, final String message){
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
	}
	
	@Override
	public final boolean isValid(T value, ConstraintValidatorContext context) {
		if(!isValidImpl(value, context)){
			addCustomConstraintViolation(context, getMessage(value));
			return false;
		}
		return true;
	}
	
	public abstract boolean isValidImpl(T value, ConstraintValidatorContext context);
	
	public abstract String getMessage(T value);
}
