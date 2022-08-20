package gr.charos.fantasymanager.api.rest.dto;

import gr.charos.fantasymanager.domain.League;
import gr.charos.fantasymanager.domain.LeagueParticipant;
import gr.charos.fantasymanager.domain.LeagueType;

import java.util.List;
import java.util.stream.Collectors;

public record LeagueWithStandingDTO(String code, String name, LeagueType type, List<LeagueParticipant> participants) {

  public static LeagueWithStandingDTO of(League l) {

    List<LeagueParticipant> lps = l.participants().stream().sorted().collect(Collectors.toList());
    return new LeagueWithStandingDTO(l.code(),l.name(),l.type(), lps);
  }
}

