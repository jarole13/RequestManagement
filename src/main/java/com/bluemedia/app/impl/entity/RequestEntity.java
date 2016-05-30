package com.bluemedia.app.impl.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;


import com.bluemedia.app.impl.converter.RequestStatusConverter;
import com.bluemedia.app.impl.enums.RequestStatus;
import com.bluemedia.app.impl.validator.StatusValidity;
import com.bluemedia.app.impl.validator.ContentChangeAllowedValiditiy;
import com.google.common.collect.Lists;
import com.sun.istack.NotNull;

@Entity
@Table(name = "REQUEST")
@ReasonValidity
@StatusValidity
@ContentChangeAllowedValiditiy
public class RequestEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;

	@NotNull
	@Column(name = "NAME", nullable=false)
	private String name;

	@NotNull
	@Column(name = "CONTENT", nullable=false)
	private String content;

	@NotNull
	@Column(name = "STATUS", nullable=false)
	@Convert(converter = RequestStatusConverter.class)
	private RequestStatus status;

	@Transient
	private RequestStatus savedStatus;

	@Column(name = "REASON")
	private String reason;

	@Column(name = "REQUEST_NO", unique = true)
	private Long number;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "request")
	private List<RequestHistoryEntity> requestHistoryList;

	@PostLoad
	@PostUpdate
	@PostPersist
	public void onPostUpdate() {
		this.savedStatus = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	protected void setSavedStatus(RequestStatus savedStatus) {
		this.savedStatus = savedStatus;
	}

	public RequestStatus getSavedStatus() {
		return savedStatus;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setRequestHistoryList(List<RequestHistoryEntity> requestHistoryList) {
		this.requestHistoryList = requestHistoryList;
	}

	public List<RequestHistoryEntity> getRequestHistoryList() {
		if (requestHistoryList == null) {
			requestHistoryList = Lists.newArrayList();
		}
		return requestHistoryList;
	}
	
	public void addRequestHistory(RequestHistoryEntity requestHistory) {
		getRequestHistoryList().add(requestHistory);
	}

	public void setId(Long id) {
		this.id=id;
	}

	public Long getId() {
		return id;
	}

}
