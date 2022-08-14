package gr.charos.fantasymanager.domain;

import java.time.ZonedDateTime;

public record FootballFixture(ZonedDateTime date, FootballMatchdaySquad home, FootballMatchdaySquad away) /* implements Fixture */{

}
