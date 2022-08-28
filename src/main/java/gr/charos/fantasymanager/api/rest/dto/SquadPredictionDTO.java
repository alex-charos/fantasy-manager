package gr.charos.fantasymanager.api.rest.dto;

import gr.charos.fantasymanager.domain.*;
import gr.charos.fantasymanager.entity.SquadPredictionEntity;

import java.util.List;
import java.util.Optional;

public record SquadPredictionDTO(String fixtureId, Team team, List<Player> predicted, List<Player> actual) {

  public static SquadPredictionDTO from(SquadPredictionEntity spe, Optional<FixtureLineup> lineup) {
    if (lineup.isPresent()) {
      return new SquadPredictionDTO(spe.getFixtureId(), spe.getTeam(), spe.getPlayers(), lineup.get().lineup());
    } else {
      return new SquadPredictionDTO(spe.getFixtureId(), spe.getTeam(), spe.getPlayers(), null);
    }
  }
}
