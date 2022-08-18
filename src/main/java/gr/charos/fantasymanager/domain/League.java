package gr.charos.fantasymanager.domain;

import java.util.HashSet;
import java.util.Set;

public record League(String code, String name, LeagueType type, Set<LeagueParticipant> participants) {
    public League {
      if (participants == null ){
        participants = new HashSet<>();
      }
    }

  public void addParticipant(LeagueParticipant lp) {
    this.participants.add(lp);
  }

}
