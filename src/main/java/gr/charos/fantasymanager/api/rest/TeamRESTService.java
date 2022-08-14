package gr.charos.fantasymanager.api.rest;

import gr.charos.fantasymanager.domain.Team;
import gr.charos.fantasymanager.gateway.TeamGateway;
import gr.charos.fantasymanager.gateway.dto.FixtureListRootDTO;
import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/teams")
public class TeamRESTService {


  @RestClient
  TeamGateway teamGateway;

  @Path("/{teamId}")
  @GET
  public Team getTeam(@PathParam("teamId") String teamId) {
    return teamGateway.getTeamByTeamId(teamId);
  }

  @Path("/{teamId}/fixtures")
  @GET
  public FixtureListRootDTO getMatchesByTeam(@PathParam("teamId") String teamId) {
    return teamGateway.getMatchesByTeamId(teamId);
  }


}
