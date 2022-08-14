package gr.charos.fantasymanager.gateway;


import gr.charos.fantasymanager.gateway.dto.FixtureListRootDTO;
import gr.charos.fantasymanager.gateway.dto.MatchDTO;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/v4")
@RegisterRestClient(configKey="matches-api")
public interface MatchGateway {

  @Path("/competitions/{competitionId}/matches")
  @GET
  @ClientHeaderParam(name = "X-Auth-Token", value = "${matches-api.auth.value}")
  FixtureListRootDTO getByCompetition(@PathParam("competitionId") String competitionId);

  @Path("/matches/{matchId}")
  @GET
  @ClientHeaderParam(name = "X-Auth-Token", value = "${matches-api.auth.value}")
  MatchDTO getById(@PathParam("matchId") String matchId);
}
