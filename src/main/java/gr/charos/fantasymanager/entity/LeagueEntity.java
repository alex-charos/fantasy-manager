package gr.charos.fantasymanager.entity;

import gr.charos.fantasymanager.domain.League;
import gr.charos.fantasymanager.domain.LeagueParticipant;
import gr.charos.fantasymanager.domain.LeagueType;
import gr.charos.fantasymanager.domain.Predictor;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@MongoEntity
public class LeagueEntity extends PanacheMongoEntity  {

  public String code;
  public String name;
  public LeagueType type;
  public Set<LeagueParticipant> participants;
  public Set<String> scoredFixtureIds;

  public LeagueEntity() {
  }

  public LeagueEntity(String code, String name, LeagueType type, Set<LeagueParticipant> participants) {
    this.code = code;
    this.name = name;
    this.type = type;
    this.participants = participants;
  }


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LeagueType getType() {
    return type;
  }

  public void setType(LeagueType type) {
    this.type = type;
  }

  public Set<LeagueParticipant> getParticipants() {
    if (participants==null) {
      participants = new HashSet<>();
    }
    return participants;
  }

  public void setParticipants(Set<LeagueParticipant> participants) {
    this.participants = participants;
  }

  public Set<String> getScoredFixtureIds() {
    if (scoredFixtureIds == null) {
      scoredFixtureIds = new HashSet<>();
    }
    return scoredFixtureIds;
  }

  public void setScoredFixtureIds(Set<String> scoredFixtureIds) {
    this.scoredFixtureIds = scoredFixtureIds;
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
