package gr.charos.fantasymanager.domain;

import java.util.List;

public record FixtureLineup(String fixtureId, Team team, List<Player> lineup) {
}
