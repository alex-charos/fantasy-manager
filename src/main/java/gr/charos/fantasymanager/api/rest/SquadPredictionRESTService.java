package gr.charos.fantasymanager.api.rest;


import gr.charos.fantasymanager.api.rest.dto.SquadPredictionDTO;
import gr.charos.fantasymanager.domain.Predictor;
import gr.charos.fantasymanager.entity.SquadPredictionEntity;
import gr.charos.fantasymanager.gateway.MatchGateway;
import gr.charos.fantasymanager.gateway.TeamGateway;
import gr.charos.fantasymanager.gateway.dto.FixtureListRootDTO;
import gr.charos.fantasymanager.gateway.dto.TeamListRootDTO;
import io.quarkus.oidc.UserInfo;
import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.List;

@Path("/predictions")
public class SquadPredictionRESTService {

  @RestClient
  MatchGateway matchGateway;

  @Inject
  UserInfo userInfo;

  private static Logger LOGGER = LoggerFactory.getLogger(SquadPredictionRESTService.class);

  @POST
  public void createPrediction(SquadPredictionDTO dto ) {

    SquadPredictionEntity spe =  new SquadPredictionEntity();
    spe.team  = dto.team;
    spe.predictor = new Predictor(String.valueOf(userInfo.get("email")), String.valueOf(userInfo.get("name"))); // get name from oidc
    spe.fixtureId = dto.fixtureId;
    spe.players = dto.players;
    spe.persist();
  }



  @GET
  public List<SquadPredictionEntity> getPredictions() {
    return SquadPredictionEntity.listAll();
  }




}
