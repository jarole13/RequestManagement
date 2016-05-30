package com.bluemedia.app.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.bluemedia.app.impl.entity.RequestEntity;
import com.bluemedia.app.impl.enums.RequestStatus;
import com.bluemedia.app.impl.service.RequestService;

@Path("request/management")
public class RequestManagementWebService {

	public static final String RECORDS_ON_SITE = "10";

	@EJB
	private RequestService requestService;

	@GET
	@Produces("application/json")
	@Path("create/{name}/{content}")
	public Response create(@PathParam("name") String name, @PathParam("content") String content) {
		RequestEntity request = requestService.create(name, content);
		return Response.status(Status.OK).entity(request).build();
	}

	@POST
	@Produces("application/json")
	@Path("verify")
	public Response verify(@FormParam("requestId") Long id) {
		RequestEntity request = requestService.verify(id);
		return Response.status(Status.OK).entity(request).build();
	}

	@POST
	@Produces("application/json")
	@Path("accept")
	public Response accept(@FormParam("requestId") Long id) {
		RequestEntity request = requestService.accept(id);
		return Response.status(Status.OK).entity(request).build();
	}

	@POST
	@Produces("application/json")
	@Path("publish")
	public Response publish(@FormParam("requestId") Long id) {
		RequestEntity request = requestService.publish(id);
		return Response.status(Status.OK).entity(request).build();
	}

	@POST
	@Produces("application/json")
	@Path("reject")
	public Response reject(@FormParam("requestId") Long id, @FormParam("reason") String reason) {
		RequestEntity request = requestService.reject(id, reason);
		return Response.status(Status.OK).entity(request).build();
	}

	@POST
	@Produces("application/json")
	@Path("delete")
	public Response delete(@FormParam("requestId") Long id, @FormParam("reason") String reason) {
		RequestEntity request = requestService.reject(id, reason);
		return Response.status(Status.OK).entity(request).build();
	}

	@POST
	@Produces("application/json")
	@Path("search")
	public Response search(@FormParam("name") String name, @FormParam("status") RequestStatus status,
			@DefaultValue(RECORDS_ON_SITE) @FormParam("pageIndex") Integer pageIndex) {
		List<RequestEntity> requestList = requestService.findByNameOrStatus(name, status, pageIndex, pageIndex);
		return Response.status(Status.OK).entity(requestList).build();
	}

	@POST
	@Produces("application/json")
	@Path("find")
	public Response findById(@FormParam("requestId") Long id) {
		RequestEntity request = requestService.findRequest(id);
		return Response.status(Status.OK).entity("found entity: " + request).build();
	}
	
	@POST
	@Produces("application/json")
	@Path("modify")
	public Response modify(@FormParam("requestId") Long id, @FormParam("content") String content) {
		RequestEntity request = requestService.modifyRequestContent(id, content);
		return Response.status(Status.OK).entity("found entity: " + request).build();
	}
}