package gr.charos.fantasymanager.api.rest.dto;

import gr.charos.fantasymanager.domain.HomeAway;
import gr.charos.fantasymanager.domain.Player;
import gr.charos.fantasymanager.domain.Predictor;
import gr.charos.fantasymanager.domain.Team;
import gr.charos.fantasymanager.entity.SquadPredictionEntity;

import java.util.List;

public class SquadPredictionDTO {

  public SquadPredictionDTO(String fixtureId, Team t, List<Player> players) {
    this.fixtureId = fixtureId;
    this.team = t;
    this.players = players;

  }


  public String fixtureId;
  public Team team;
  public List<Player> players;

  public static SquadPredictionDTO from(SquadPredictionEntity spe) {
    return new SquadPredictionDTO(spe.getFixtureId(),spe.getTeam(), spe.getPlayers() );
  }
}
