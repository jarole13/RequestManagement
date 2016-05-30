package com.bluemedia.app.impl.validator;

import javax.ejb.EJB;
import javax.validation.ConstraintValidatorContext;

import com.bluemedia.app.impl.dao.RequestDao;
import com.bluemedia.app.impl.entity.RequestEntity;
import com.bluemedia.app.impl.enums.RequestStatus;

public class ContentChangeAllowedValidator extends BaseValidator<ContentChangeAllowedValiditiy, RequestEntity> {

	@EJB
	private RequestDao requestDao;

	@Override
	public void initialize(ContentChangeAllowedValiditiy constraintAnnotation) {
	}

	@Override
	public boolean isValidImpl(RequestEntity request, ConstraintValidatorContext context) {
		if (request.getStatus() != RequestStatus.CREATED && request.getStatus() != RequestStatus.VERIFIED) {
//			return !requestDao.checkIfContentChanged(request);
		}
		return true;
	}

	@Override
	public String getMessage(RequestEntity value) {
		return "Change can't be applied in status CREATED or VERIFIED.";
	}

}
