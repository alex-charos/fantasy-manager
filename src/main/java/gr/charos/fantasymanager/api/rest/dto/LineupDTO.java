package gr.charos.fantasymanager.api.rest.dto;

import gr.charos.fantasymanager.domain.Player;
import gr.charos.fantasymanager.domain.Team;

import java.util.List;

public class LineupDTO {
  public String fixtureId;
  public String teamId;
  public List<Player> lineup;

}
