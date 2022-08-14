package gr.charos.fantasymanager.entity;

import gr.charos.fantasymanager.domain.PredictionResult;
import gr.charos.fantasymanager.domain.Predictor;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

import java.time.LocalDateTime;
import java.util.List;

@MongoEntity
public class PredictionResultEntity  extends PanacheMongoEntity {
  public PredictionResultEntity(){

  }

  public PredictionResultEntity(Predictor predictor, String fixtureId, String teamId, double score, LocalDateTime predictionDate, int correctPredictions, LocalDateTime fixtureDate, LocalDateTime setllementDate) {
    this.predictor = predictor;
    this.fixtureId = fixtureId;
    this.teamId = teamId;
    this.score = score;
    this.predictionDate = predictionDate;
    this.correctPredictions = correctPredictions;
    this.fixtureDate = fixtureDate;
    this.setllementDate = setllementDate;
  }

  public Predictor predictor;
  public String fixtureId;
  public String teamId;
  public double score;
  public LocalDateTime predictionDate;
  public int correctPredictions;
  public LocalDateTime fixtureDate;
  public LocalDateTime setllementDate;


  public static PredictionResultEntity of(PredictionResult pr) {
    return new PredictionResultEntity(pr.predictor(), pr.fixtureId(), pr.teamId(), pr.score(), pr.predictionDate(), pr.correctPredictions(), pr.fixtureDate(), pr.setllementDate());
  }

  public static List<PredictionResultEntity> findByPredictor(String predictorId) {
    return list("predictor.id", predictorId);
  }

  public  PredictionResult toPredictionResult() {
    return new PredictionResult(this.predictor, this.fixtureId, this.teamId, this.correctPredictions, this.score, this.predictionDate, this.fixtureDate, this.setllementDate);

  }

}
