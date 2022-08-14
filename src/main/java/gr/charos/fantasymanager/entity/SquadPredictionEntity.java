package gr.charos.fantasymanager.entity;

import gr.charos.fantasymanager.domain.Player;
import gr.charos.fantasymanager.domain.Predictor;
import gr.charos.fantasymanager.domain.Team;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

import java.util.List;

@MongoEntity
public class SquadPredictionEntity extends PanacheMongoEntity {


  public Predictor predictor;
  public String fixtureId;
  public Team team;
  public List<Player> players;



}
