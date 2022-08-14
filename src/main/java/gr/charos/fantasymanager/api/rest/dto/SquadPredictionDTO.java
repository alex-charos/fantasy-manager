package gr.charos.fantasymanager.api.rest.dto;

import gr.charos.fantasymanager.domain.HomeAway;
import gr.charos.fantasymanager.domain.Player;
import gr.charos.fantasymanager.domain.Predictor;
import gr.charos.fantasymanager.domain.Team;

import java.util.List;

public class SquadPredictionDTO {

  public String fixtureId;
  public Team team;
  public List<Player> players;
}
