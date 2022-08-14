package gr.charos.fantasymanager.api.rest;


import gr.charos.fantasymanager.api.rest.dto.LineupDTO;
import gr.charos.fantasymanager.domain.Fixture;
import gr.charos.fantasymanager.domain.Team;
import gr.charos.fantasymanager.gateway.MatchGateway;
import gr.charos.fantasymanager.gateway.dto.MatchDTO;
import gr.charos.fantasymanager.service.LineupService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/lineup")
public class LineupRESTService {


  @RestClient
  MatchGateway matchGateway;

  @Inject
  LineupService lineupService;

  @POST
  public void setLineup(LineupDTO lineupDTO) {

    MatchDTO match = matchGateway.getById(lineupDTO.fixtureId);
    Fixture f = new Fixture(match.id, match.utcDate.toLocalDateTime(), match.homeTeam, match.awayTeam);
    Team t;
    if (lineupDTO.teamId.equalsIgnoreCase(match.homeTeam.id())) {
      t = match.homeTeam;
    } else if (lineupDTO.teamId.equalsIgnoreCase(match.awayTeam.id())) {
      t= match.awayTeam;
    } else {
      throw new IllegalArgumentException("Invalid team id provided: " + lineupDTO.teamId);
    }
    lineupService.lineupReceived(f, t, lineupDTO.lineup );

  }
}
