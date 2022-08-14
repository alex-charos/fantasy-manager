package gr.charos.fantasymanager.service;

import java.time.LocalDateTime;

public interface ScoringService {
  double scorePrediction(LocalDateTime predictionDate, LocalDateTime fixtureDate, int correct);

}
