package gr.charos.fantasymanager.gateway;


import gr.charos.fantasymanager.gateway.dto.FixtureListRootDTO;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/v4/competitions")
@RegisterRestClient(configKey="matches-api")
public interface MatchGateway {

  @Path("/{competitionId}/matches")
  @GET
  @ClientHeaderParam(name = "X-Auth-Token", value = "${matches-api.auth.value}")
  FixtureListRootDTO getByCompetition(@PathParam("competitionId") String competitionId);

}
