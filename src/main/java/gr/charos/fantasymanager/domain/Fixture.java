package gr.charos.fantasymanager.domain;

import java.time.LocalDateTime;

public record Fixture(String id, LocalDateTime date, Team homeTeam, Team awayTeam) {

}
