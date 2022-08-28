package gr.charos.fantasymanager.entity;

import gr.charos.fantasymanager.domain.Fixture;
import gr.charos.fantasymanager.domain.FixtureLineup;
import gr.charos.fantasymanager.domain.Player;
import gr.charos.fantasymanager.domain.Team;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.Document;

import java.util.List;

@MongoEntity
public class FixtureLineupEntity extends PanacheMongoEntity {

  public FixtureLineupEntity() {
  }

  public FixtureLineupEntity(String fixtureId, String teamId, List<Player> lineup) {
    this.fixtureId = fixtureId;
    this.teamId = teamId;
    this.lineup = lineup;
  }

  public String fixtureId;
  public String teamId;
  public List<Player> lineup;


  public FixtureLineup toLineup(){
    return new FixtureLineup(this.fixtureId, this.teamId, this.lineup);
  }

  public static FixtureLineupEntity of(FixtureLineup lineup) {
    return new FixtureLineupEntity(lineup.fixtureId(), lineup.teamId(),lineup.lineup());
  }


  public static FixtureLineupEntity findByFixtureIdAndTeam(String fixtureId, Team team) {
    return findByFixtureIdAndTeamId(fixtureId, team.id());
  }
  public static FixtureLineupEntity findByFixtureIdAndTeamId(String fixtureId, String teamId) {
    Document doc = new Document();
    doc.put("teamId" , teamId);
    doc.put("fixtureId", fixtureId);
    return find(doc).firstResult();
  }

}
