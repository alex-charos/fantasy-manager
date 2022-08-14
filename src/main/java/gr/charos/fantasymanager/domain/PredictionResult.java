package gr.charos.fantasymanager.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PredictionResult( Predictor predictor,
                                String fixtureId,
                               String teamId,
                               int correctPredictions,
                               double score,
                               LocalDateTime predictionDate,
                               LocalDateTime fixtureDate,
                               LocalDateTime setllementDate) {}
