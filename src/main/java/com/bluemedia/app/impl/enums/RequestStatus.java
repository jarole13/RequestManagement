package com.bluemedia.app.impl.enums;

import java.util.Arrays;
import java.util.List;

public enum RequestStatus {

	DELETED("D"), REJECTED("R"), PUBLISHED("P"), ACCEPTED("A", PUBLISHED, REJECTED), VERIFIED("V", REJECTED, ACCEPTED), CREATED("C", VERIFIED, DELETED);

	private final String databaseValue;
	private List<RequestStatus> allowedNextStatusList;

	private RequestStatus(final String databaseValue, RequestStatus... statuses) {
		this.databaseValue = databaseValue;
		allowedNextStatusList = Arrays.asList(statuses);
	}

	public String getDatabaseValue() {
		return databaseValue;
	}
	
	public List<RequestStatus> getAllowedNextStatusList() {
		return allowedNextStatusList;
	}

	public static RequestStatus getEnumFromDatabaseValue(final String databaseValue) {
		if (databaseValue == null) {
			return null;
		}
		String databaseValueTrimmed = databaseValue.trim();
		for (RequestStatus status : values()) {
			if (status.databaseValue.equals(databaseValueTrimmed)) {
				return status;
			}
		}
		return null;
	}
}
