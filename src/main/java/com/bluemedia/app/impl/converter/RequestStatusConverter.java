package com.bluemedia.app.impl.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.bluemedia.app.impl.enums.RequestStatus;

@Converter
public class RequestStatusConverter implements AttributeConverter<RequestStatus, String> {

	@Override
	public String convertToDatabaseColumn(RequestStatus requestStatus) {
		return requestStatus != null ? requestStatus.getDatabaseValue() : null;
	}

	@Override
	public RequestStatus convertToEntityAttribute(String databaseValue) {
		return RequestStatus.getEnumFromDatabaseValue(databaseValue);
	}

}
