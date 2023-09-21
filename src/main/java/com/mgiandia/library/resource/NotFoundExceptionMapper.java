package com.mgiandia.library.resource;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

   

	@Override
	public Response toResponse(NotFoundException exception) {
		return Response.status(404).entity("Not Found").build();
	} 
}
