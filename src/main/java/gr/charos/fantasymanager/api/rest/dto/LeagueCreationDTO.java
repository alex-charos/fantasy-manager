package gr.charos.fantasymanager.api.rest.dto;

import gr.charos.fantasymanager.domain.League;
import gr.charos.fantasymanager.domain.LeagueParticipant;
import gr.charos.fantasymanager.domain.LeagueType;
import gr.charos.fantasymanager.domain.Predictor;
import org.jboss.logging.annotations.ValidIdRange;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Valid
public record LeagueCreationDTO(@Valid @NotNull @NotEmpty String name) {

  public  League  toLeague(Predictor p) {
    Set<LeagueParticipant> participants = new HashSet<>();
    participants.add(new LeagueParticipant(p,0));
    League league = new League(null, this.name, LeagueType.USER, participants);
    return league;
  }
};
