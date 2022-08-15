package gr.charos.fantasymanager.entity;

import gr.charos.fantasymanager.domain.Player;
import gr.charos.fantasymanager.domain.Predictor;
import gr.charos.fantasymanager.domain.Team;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.Document;

import java.time.LocalDateTime;
import java.util.List;

@MongoEntity
public class SquadPredictionEntity extends PanacheMongoEntity {

  public Predictor predictor;
  public String fixtureId;
  public Team team;
  public List<Player> players;
  public LocalDateTime predictionDate;

  public Predictor getPredictor() {
    return predictor;
  }

  public void setPredictor(Predictor predictor) {
    this.predictor = predictor;
  }

  public String getFixtureId() {
    return fixtureId;
  }

  public void setFixtureId(String fixtureId) {
    this.fixtureId = fixtureId;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public void setPlayers(List<Player> players) {
    this.players = players;
  }

  public static List<SquadPredictionEntity> findByPredictorId(String id) {
    return list("predictor.id" ,id);
  }
  public static List<SquadPredictionEntity> findByFixtureIdAndTeam(String fixtureId, String teamId) {
    Document doc = new Document();
    doc.put("team.id" , teamId);
    doc.put("fixtureId", fixtureId);
    return list(doc);
  }

  public static void deleteByPredictorAndFixtureIdAndTeam(String predictorId,String fixtureId, String teamId) {
    Document doc = new Document();
    doc.put("team.id" , teamId);
    doc.put("fixtureId", fixtureId);
    doc.put("predictor.id", predictorId);

    delete(doc);

  }




}
