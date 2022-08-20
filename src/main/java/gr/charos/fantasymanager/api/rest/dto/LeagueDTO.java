package gr.charos.fantasymanager.api.rest.dto;

import gr.charos.fantasymanager.domain.League;
import gr.charos.fantasymanager.domain.LeagueParticipant;
import gr.charos.fantasymanager.domain.LeagueType;

import java.util.List;
import java.util.Set;

public record LeagueDTO(String code, String name, LeagueType type) {

  public static LeagueDTO of(League l) {
    return new LeagueDTO(l.code(),l.name(),l.type());
  }
}
