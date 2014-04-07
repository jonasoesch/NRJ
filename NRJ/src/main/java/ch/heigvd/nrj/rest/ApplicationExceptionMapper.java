package ch.heigvd.nrj.rest;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * This class is used to send appropriate HTTP responses (with HTTP status codes)
 * when specific exceptions are thrown in the back-end. It is cleaner that always
 * throwing a 500.
 * 
 * @author Olivier Liechti
 */
@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<EntityNotFoundException> {

	@Override
	public Response toResponse(EntityNotFoundException exception) {
		return Response.status(Response.Status.NOT_FOUND).build();
	}

}
