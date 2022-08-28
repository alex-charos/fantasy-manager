package gr.charos.fantasymanager.api.rest;


import gr.charos.fantasymanager.api.rest.dto.LineupDTO;
import gr.charos.fantasymanager.domain.Fixture;
import gr.charos.fantasymanager.domain.FixtureLineup;
import gr.charos.fantasymanager.domain.Team;
import gr.charos.fantasymanager.gateway.FixtureGateway;
import gr.charos.fantasymanager.service.LineupService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/lineup")
public class LineupRESTService {


  @RestClient
  FixtureGateway fixtureGateway;

  @Inject
  LineupService lineupService;

  @POST
  public void setLineup(LineupDTO lineupDTO) {

    Fixture fixture = fixtureGateway.getById(lineupDTO.fixtureId);
    Team t;
    if (lineupDTO.teamId.equalsIgnoreCase(fixture.homeTeam().id())) {
      t = fixture.homeTeam();
    } else if (lineupDTO.teamId.equalsIgnoreCase(fixture.awayTeam().id())) {
      t= fixture.awayTeam();
    } else {
      throw new IllegalArgumentException("Invalid team id provided: " + lineupDTO.teamId);
    }
    lineupService.lineupReceived( new FixtureLineup(fixture.id(), t.id(), lineupDTO.lineup ));

  }


}
