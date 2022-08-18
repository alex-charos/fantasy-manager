package gr.charos.fantasymanager.api.rest.mappers;

import gr.charos.fantasymanager.exceptions.LeagueNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class LeagueNotFoundExceptionMapper implements ExceptionMapper<LeagueNotFoundException> {
  @Override
  public Response toResponse(LeagueNotFoundException exception) {
    return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).build();
  }
}
