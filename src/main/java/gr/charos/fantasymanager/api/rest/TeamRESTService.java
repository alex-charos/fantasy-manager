package gr.charos.fantasymanager.api.rest;

import gr.charos.fantasymanager.domain.Fixture;
import gr.charos.fantasymanager.domain.Team;
import gr.charos.fantasymanager.gateway.TeamGateway;
import gr.charos.fantasymanager.gateway.dto.FixtureListRootDTO;
import gr.charos.fantasymanager.service.FixtureService;
import gr.charos.fantasymanager.service.TeamService;
import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("/teams")
public class TeamRESTService {


  @Inject
  TeamService teamService;

  @Inject
  FixtureService fixtureService;

  @Path("/{teamId}")
  @GET
  public Team getTeam(@PathParam("teamId") String teamId) {
    return teamService.getTeamById(teamId);
  }

  @Path("/{teamId}/fixtures")
  @GET
  public List<Fixture> getMatchesByTeam(@PathParam("teamId") String teamId) {
    return fixtureService.getFixturesByTeamId(teamId);
  }


}
