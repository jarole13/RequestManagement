package com.bluemedia.app.impl.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.joda.time.LocalDateTime;

import com.bluemedia.app.impl.enums.RequestStatus;
import com.sun.istack.NotNull;

@Entity
@Table(name = "REQUEST_HISTORY")
public class RequestHistoryEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@NotNull
	@Column(name = "FROM_STATUS")
	private RequestStatus fromStatus;
	
	@NotNull
	@Column(name = "TO_STATUS")
	private RequestStatus toStatus;
	
	@NotNull
	@Column(name = "MODIFIED_DATE")
	private LocalDateTime localDateTime;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="REQUEST_ID")
	private RequestEntity request;
	
	public RequestStatus getFromStatus() {
		return fromStatus;
	}
	
	public RequestStatus getToStatus() {
		return toStatus;
	}
	
	public void setFromStatus(RequestStatus fromStatus) {
		this.fromStatus = fromStatus;
	}
	
	public void setToStatus(RequestStatus toStatus) {
		this.toStatus = toStatus;
	}
	
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
	
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	
	public void setRequest(RequestEntity request) {
		this.request = request;
	}
	
	public RequestEntity getRequest() {
		return request;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}

}
