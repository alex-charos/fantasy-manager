package gr.charos.fantasymanager.domain;

import java.util.Objects;

public record LeagueParticipant(Predictor predictor, double points) {

  public LeagueParticipant addPoints(double points) {
    return new LeagueParticipant(this.predictor, Double.sum(this.points, points));
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
}
