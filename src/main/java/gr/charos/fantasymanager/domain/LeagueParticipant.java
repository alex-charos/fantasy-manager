package gr.charos.fantasymanager.domain;

import java.math.BigDecimal;
import java.util.Objects;

public record LeagueParticipant(Predictor predictor, double points) implements Comparable<LeagueParticipant> {

  public LeagueParticipant addPoints(double points) {
    return new LeagueParticipant(this.predictor, BigDecimal.valueOf(points).add(BigDecimal.valueOf(this.points)).doubleValue());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    LeagueParticipant that = (LeagueParticipant) o;
    return predictor.equals(that.predictor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(predictor);
  }

  @Override
  public int compareTo(LeagueParticipant o) {
    if (o.points() < this.points) {
      return -1;
    } else if (this.points<o.points()) {
      return 1;
    }
    return 0;
  }

  @Override
  public String toString() {
    return "LeagueParticipant{" +
      "predictor=" + predictor +
      ", points=" + points +
      '}';
  }
}
