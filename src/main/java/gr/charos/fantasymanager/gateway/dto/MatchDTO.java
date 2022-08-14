package gr.charos.fantasymanager.gateway.dto;

import gr.charos.fantasymanager.domain.Team;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class MatchDTO {

  public String id;

  public ZonedDateTime utcDate;
  public Team homeTeam;
  public Team awayTeam;

}
