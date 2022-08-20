package gr.charos.fantasymanager.api.rest;

import gr.charos.fantasymanager.api.rest.dto.LeagueCreationDTO;
import gr.charos.fantasymanager.api.rest.dto.LeagueWithStandingDTO;
import gr.charos.fantasymanager.domain.League;
import gr.charos.fantasymanager.domain.Predictor;
import gr.charos.fantasymanager.exceptions.LeagueNotFoundException;
import gr.charos.fantasymanager.service.LeagueService;
import io.quarkus.oidc.UserInfo;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import java.security.Permission;
import java.util.List;
import java.util.stream.Collectors;

@Path("leagues")
public class LeagueRESTService {

  @Inject
  LeagueService leagueService;

  @Inject
  UserInfo userInfo;

  @GET
  @Path("/{leagueCode}")
  public LeagueWithStandingDTO getLeagueByCode(@PathParam("leagueCode") String leagueCode) throws LeagueNotFoundException {
    return LeagueWithStandingDTO.of(leagueService.getLeagueByCode(leagueCode));
  }

  @DELETE
  @Path("/{leagueCode}")
  public void leaveLeague(@PathParam("leagueCode") String leagueCode) throws LeagueNotFoundException {
    leagueService.leaveLeague(leagueCode, new Predictor(userInfo.getString("email"), userInfo.getString("name")));
  }

  @GET
  public List<LeagueWithStandingDTO> getUsersLeagues(@Context SecurityContext sec) throws LeagueNotFoundException {
    return  leagueService.getLeaguesByPredictor(new Predictor(userInfo.getString("email"), userInfo.getString("name")))
      .stream()
      .map(LeagueWithStandingDTO::of)
      .collect(Collectors.toList());
  }

  @POST
  public LeagueWithStandingDTO createLeague(LeagueCreationDTO leagueCreationDTO) {
    Predictor p = new Predictor(userInfo.getString("email"), userInfo.getString("name"));
    return LeagueWithStandingDTO.of(leagueService.createLeague(leagueCreationDTO.toLeague(p)));
  }

  @PUT
  @Path("/{leagueCode}")
  public LeagueWithStandingDTO joinLeague(@PathParam("leagueCode") String leagueCode) throws LeagueNotFoundException {
    Predictor p = new Predictor(userInfo.getString("email"), userInfo.getString("name"));
    return  LeagueWithStandingDTO.of( leagueService.joinLeague(leagueCode,p));
  }



}
