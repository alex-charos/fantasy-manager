package gr.charos.fantasymanager.gateway;

import gr.charos.fantasymanager.domain.Team;
import gr.charos.fantasymanager.gateway.dto.FixtureListRootDTO;
import gr.charos.fantasymanager.gateway.dto.TeamListRootDTO;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;


@Path("/v4")
@RegisterRestClient(configKey="matches-api")
public interface TeamGateway {


  @Path("/competitions/{competitionId}/teams")
  @GET
  @ClientHeaderParam(name = "X-Auth-Token", value = "${matches-api.auth.value}")
  TeamListRootDTO getTeamsByCompetition(@PathParam("competitionId") String competitionId);

  @Path("/teams/{teamId}")
  @GET
  @ClientHeaderParam(name = "X-Auth-Token", value = "${matches-api.auth.value}")
  Team getTeamByTeamId(@PathParam("teamId") String teamId);

  @Path("teams/{teamId}/matches")
  @GET
  @ClientHeaderParam(name = "X-Auth-Token", value = "${matches-api.auth.value}")
  FixtureListRootDTO getMatchesByTeamId(@PathParam("teamId") String teamId);



}
