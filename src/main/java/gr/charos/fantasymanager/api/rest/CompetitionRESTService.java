package gr.charos.fantasymanager.api.rest;

import gr.charos.fantasymanager.domain.Team;
import gr.charos.fantasymanager.service.TeamService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;


@Path("/competitions")
public class CompetitionRESTService {

  @Inject
  TeamService teamService;

  @Path("/{competitionId}/teams")
  @GET
  public List<Team> getTeamsBy(@PathParam("competitionId") String competition) {
    return teamService.getTeamsByCompetition(competition);
  }

}
