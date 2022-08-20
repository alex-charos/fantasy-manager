package gr.charos.fantasymanager.repository;

import gr.charos.fantasymanager.domain.Fixture;
import gr.charos.fantasymanager.gateway.FixtureGateway;
import gr.charos.fantasymanager.gateway.TeamGateway;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Singleton;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class FixtureRepositoryImpl implements FixtureRepository {

  @RestClient
  TeamGateway teamGateway;

  @RestClient
  FixtureGateway fixtureGateway;

  private static ConcurrentHashMap<String, List<Fixture>> fixturesByTeamCache = new ConcurrentHashMap<>();
  private static ConcurrentHashMap<String, Fixture> fixturesCache = new ConcurrentHashMap<>();

  @Override
  public List<Fixture> getFixturesByTeamId(String teamId) {
    if (!fixturesByTeamCache.containsKey(teamId)) {
      fixturesByTeamCache.put(teamId, teamGateway.getMatchesByTeamId(teamId).matches);
    }
    return fixturesByTeamCache.get(teamId);
  }

  @Override
  public Fixture getFixtureById(String fixtureId) {
    if (!fixturesCache.containsKey(fixtureId)) {
      fixturesCache.put(fixtureId, fixtureGateway.getById(fixtureId));
    }
    return fixturesCache.get(fixtureId);
  }
}
