package gr.charos.fantasymanager.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public record Fixture(String id, @JsonSetter("utcDate") ZonedDateTime date, Team homeTeam, Team awayTeam) {

}
