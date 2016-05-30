package com.bluemedia.app.impl.validator;

import javax.validation.ConstraintValidatorContext;

import com.bluemedia.app.impl.entity.RequestEntity;
import com.bluemedia.app.impl.enums.RequestStatus;

public class StatusValidator extends BaseValidator<StatusValidity, RequestEntity> {
	
	public static final String message = "New status is not valid. Valid statuses: ";

	@Override
	public void initialize(StatusValidity arg0) {
	}

	@Override
	public boolean isValidImpl(RequestEntity requestEntity, ConstraintValidatorContext context) {
		RequestStatus currentStatus = requestEntity.getStatus();
		RequestStatus savedStatus = requestEntity.getSavedStatus();
		if (savedStatus!=null && currentStatus != savedStatus) {
			return savedStatus.getAllowedNextStatusList().contains(currentStatus);
		}
		return true;
	}

	@Override
	public String getMessage(RequestEntity requestEntity) {
		return message+requestEntity.getSavedStatus().getAllowedNextStatusList();
	}

}
