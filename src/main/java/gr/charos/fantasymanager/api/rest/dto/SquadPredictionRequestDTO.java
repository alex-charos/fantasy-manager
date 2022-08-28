package gr.charos.fantasymanager.api.rest.dto;

import gr.charos.fantasymanager.domain.Player;
import gr.charos.fantasymanager.domain.Team;

import java.util.List;

public record SquadPredictionRequestDTO(String fixtureId, Team team, List<Player> players) {
}
