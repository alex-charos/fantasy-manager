package gr.charos.fantasymanager.api.rest;

import gr.charos.fantasymanager.gateway.TeamGateway;
import gr.charos.fantasymanager.gateway.dto.TeamListRootDTO;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;


@Path("/competitions")
public class CompetitionRESTService {


  @RestClient
  TeamGateway teamGateway;


  @Path("/{competitionId}/teams")
  @GET
  public TeamListRootDTO getTeamsBy(@PathParam("competitionId") String competition) {
    return teamGateway.getTeamsByCompetition(competition);
  }

}
