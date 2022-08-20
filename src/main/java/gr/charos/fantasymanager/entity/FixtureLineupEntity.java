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


  public static FixtureLineupEntity of(FixtureLineup lineup) {
    return new FixtureLineupEntity(lineup.fixtureId(), lineup.team().id(),lineup.lineup());
  }

  public static FixtureLineupEntity findByFixtureIdAndTeam(String fixtureId, Team team) {
    Document doc = new Document();
    doc.put("teamId" , team.id());
    doc.put("fixtureId", fixtureId);
    return find(doc).firstResult();
  }
}
