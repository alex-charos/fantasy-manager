package gr.charos.fantasymanager.api.rest;


import gr.charos.fantasymanager.api.rest.dto.SquadPredictionDTO;
import gr.charos.fantasymanager.api.rest.dto.SquadPredictionRequestDTO;
import gr.charos.fantasymanager.domain.FixtureLineup;
import gr.charos.fantasymanager.domain.Predictor;
import gr.charos.fantasymanager.entity.SquadPredictionEntity;
import gr.charos.fantasymanager.gateway.FixtureGateway;
import gr.charos.fantasymanager.service.LineupService;
import io.quarkus.oidc.UserInfo;
import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Path("/predictions")
@Authenticated
public class SquadPredictionRESTService {

  @RestClient
  FixtureGateway fixtureGateway;

  @Inject
  LineupService lineupService;

  @Inject
  UserInfo userInfo;

  Supplier<LocalDateTime> dateTimeProvider = LocalDateTime::now;

  private static Logger LOGGER = LoggerFactory.getLogger(SquadPredictionRESTService.class);

  @POST
  public void createPrediction(SquadPredictionRequestDTO dto ) {

    SquadPredictionEntity spe =  new SquadPredictionEntity();
    spe.team  = dto.team();
    spe.predictor = new Predictor(userInfo.getString("email"), userInfo.getString("name"));
    spe.fixtureId = dto.fixtureId();
    spe.players = dto.players();
    spe.predictionDate = dateTimeProvider.get();

    SquadPredictionEntity.deleteByPredictorAndFixtureIdAndTeam(spe.predictor.id(), spe.fixtureId, spe.getTeam().id());
    spe.persist();
  }

  @GET
  @Path("/fixture/{fixtureId}/team/{teamId}")
  public SquadPredictionDTO getAllPredictions(@PathParam("fixtureId") String fixtureId, @PathParam("teamId") String teamId) {
    SquadPredictionEntity spe = SquadPredictionEntity.findByPredictorAndFixtureIdAndTeam(userInfo.getString("email"), fixtureId, teamId);
    if (spe != null) {
      Optional<FixtureLineup> actual = lineupService.getLineup(fixtureId, teamId);
      return SquadPredictionDTO.from(spe, actual);
    } else {
      throw new NotFoundException();
    }

  }



}
