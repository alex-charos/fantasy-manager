package gr.charos.fantasymanager.api.rest;

import gr.charos.fantasymanager.api.rest.dto.LeagueCreationDTO;
import gr.charos.fantasymanager.domain.League;
import gr.charos.fantasymanager.domain.Predictor;
import gr.charos.fantasymanager.exceptions.LeagueNotFoundException;
import gr.charos.fantasymanager.service.LeagueService;
import io.quarkus.oidc.UserInfo;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.security.Permission;

@Path("leagues")
public class LeagueRESTService {

  @Inject
  LeagueService leagueService;

  @Inject
  UserInfo userInfo;

  @GET
  @Path("/{leagueCode}")
  public League getLeagueByCode(@PathParam("/{leagueCode") String leagueCode) throws LeagueNotFoundException {
    return leagueService.getLeagueByCode(leagueCode);
  }

  @POST
  public League createLeague(LeagueCreationDTO leagueCreationDTO) {
    Predictor p = new Predictor(userInfo.getString("email"), userInfo.getString("name"));
    return leagueService.createLeague(leagueCreationDTO.toLeague(p));
  }

  @PUT
  @Path("/{leagueCode}")
  public League joinLeague(@PathParam("leagueCode") String leagueCode) throws LeagueNotFoundException {
    Predictor p = new Predictor(userInfo.getString("email"), userInfo.getString("name"));
    return leagueService.joinLeague(leagueCode,p);
  }

}
