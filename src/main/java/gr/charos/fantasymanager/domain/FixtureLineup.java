package gr.charos.fantasymanager.domain;

import java.util.List;

public record FixtureLineup(String fixtureId, String teamId, List<Player> lineup) {
}
