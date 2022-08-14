package gr.charos.fantasymanager.api.rest;


import gr.charos.fantasymanager.api.rest.dto.SquadPredictionDTO;
import gr.charos.fantasymanager.domain.Predictor;
import gr.charos.fantasymanager.entity.SquadPredictionEntity;
import gr.charos.fantasymanager.gateway.MatchGateway;
import gr.charos.fantasymanager.gateway.TeamGateway;
import gr.charos.fantasymanager.gateway.dto.FixtureListRootDTO;
import gr.charos.fantasymanager.gateway.dto.TeamListRootDTO;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.ws.rs.*;
import java.util.List;

@Path("/predictions")
public class SquadPredictionRESTService {

  @RestClient
  MatchGateway matchGateway;


  @POST
  public void createPrediction(SquadPredictionDTO dto) {
    SquadPredictionEntity spe =  new SquadPredictionEntity();
    spe.team  = dto.team;
    spe.predictor = new Predictor("1","Alex"); // get name from oidc
    spe.fixtureId = dto.fixtureId;
    spe.players = dto.players;
    spe.persist();
  }



  @GET
  public List<SquadPredictionEntity> getPredictions() {
    return SquadPredictionEntity.listAll();
  }




}
