package com.bluemedia.app.impl.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.bluemedia.app.impl.entity.RequestEntity;
import com.bluemedia.app.impl.enums.RequestStatus;

@LocalBean
@Stateless
public class RequestDao extends BaseDao<RequestEntity> {

	public List<RequestEntity> findRequestByNameOrStatus(String name, RequestStatus status, Integer pageIndex,
			Integer numOfRecordsOnSite) {
		final TypedQuery<RequestEntity> query = getEntityManager().createQuery(
				"SELECT r FROM RequestEntity r WHERE r.name=:name OR r.status=:status", RequestEntity.class);
		query.setParameter("name", name);
		query.setParameter("status", status);
		query.setMaxResults(numOfRecordsOnSite);
		query.setFirstResult(pageIndex * numOfRecordsOnSite);
		return query.getResultList();
	}
	
	public boolean checkIfContentChanged(RequestEntity request){
		boolean result = true;
		getEntityManager().detach(request);
		RequestEntity foundRequest = findById(request.getId());
		if(foundRequest.getContent().equals(request.getContent())){
			result = false;
		}
		getEntityManager().merge(request);
		return result;
	}

}
