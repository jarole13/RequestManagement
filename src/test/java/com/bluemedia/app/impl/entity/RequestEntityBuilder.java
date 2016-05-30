package com.bluemedia.app.impl.entity;

import com.bluemedia.app.impl.entity.RequestEntity;
import com.bluemedia.app.impl.enums.RequestStatus;

public class RequestEntityBuilder {

	private RequestEntity requestEntity;

	public RequestEntityBuilder() {
		requestEntity = new RequestEntity();
	}

	public RequestEntityBuilder withId(Long id) {
		requestEntity.setId(id);
		return this;
	}

	public RequestEntityBuilder withName(String name) {
		requestEntity.setName(name);
		return this;
	}

	public RequestEntityBuilder withContent(String content) {
		requestEntity.setContent(content);
		return this;
	}

	public RequestEntityBuilder withStatus(RequestStatus status) {
		requestEntity.setStatus(status);
		return this;
	}

	public RequestEntityBuilder withSavedStatus(RequestStatus savedStatus) {
		requestEntity.setSavedStatus(savedStatus);
		return this;
	}

	public RequestEntityBuilder withNumber(Long number) {
		requestEntity.setNumber(number);
		return this;
	}

	public RequestEntityBuilder withReason(String reason) {
		requestEntity.setReason(reason);
		return this;
	}

	public RequestEntity build() {
		return requestEntity;
	}
}
