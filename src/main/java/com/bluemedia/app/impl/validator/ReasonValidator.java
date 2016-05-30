package com.bluemedia.app.impl.validator;

import javax.validation.ConstraintValidatorContext;

import com.bluemedia.app.impl.entity.RequestEntity;
import com.bluemedia.app.impl.enums.RequestStatus;

public class ReasonValidator extends BaseValidator<ReasonValidity, RequestEntity> {

	@Override
	public void initialize(ReasonValidity arg0) {
	}

	@Override
	public boolean isValidImpl(RequestEntity request, ConstraintValidatorContext context) {
		if (request.getStatus() == RequestStatus.DELETED || request.getStatus() == RequestStatus.REJECTED) {
			if (request.getReason() == null || request.getReason().isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String getMessage(RequestEntity value) {
		return "Fill reason!";
	}

}
