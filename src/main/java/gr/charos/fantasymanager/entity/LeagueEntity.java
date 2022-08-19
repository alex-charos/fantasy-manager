package gr.charos.fantasymanager.entity;

import gr.charos.fantasymanager.domain.League;
import gr.charos.fantasymanager.domain.LeagueParticipant;
import gr.charos.fantasymanager.domain.LeagueType;
import gr.charos.fantasymanager.domain.Predictor;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

import java.util.List;
import java.util.Set;

@MongoEntity
public class LeagueEntity extends PanacheMongoEntity  {

  public String code;
  public String name;
  public LeagueType type;
  public Set<LeagueParticipant> participants;

  public LeagueEntity() {
  }

  public LeagueEntity(String code, String name, LeagueType type, Set<LeagueParticipant> participants) {
    this.code = code;
    this.name = name;
    this.type = type;
    this.participants = participants;
  }

  public static List<LeagueEntity> findByPredictor(String predictorId) {
    return list("participants.predictor.id", predictorId);
  }

  public static LeagueEntity findByCode(String code) {
    return find("code" ,code).firstResult();

  }

  public static LeagueEntity of(League league){
    return new LeagueEntity(league.code(), league.name(), league.type(), league.participants());
  }

  public League toLeague() {
    return new League(this.code, this.name, this.type, this.participants);
  }

}
