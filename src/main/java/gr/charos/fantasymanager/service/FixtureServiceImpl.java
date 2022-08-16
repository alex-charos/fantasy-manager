package gr.charos.fantasymanager.service;

import gr.charos.fantasymanager.domain.Fixture;
import gr.charos.fantasymanager.domain.Team;
import gr.charos.fantasymanager.gateway.FixtureGateway;
import gr.charos.fantasymanager.gateway.TeamGateway;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class FixtureServiceImpl implements FixtureService {

  @RestClient
  TeamGateway teamGateway;

  private static ConcurrentHashMap<String, List<Fixture>> fixturesCache = new ConcurrentHashMap<>();

  @Override
  public List<Fixture> getFixturesByTeamId(String teamId) {
    if (!fixturesCache.containsKey(teamId)) {
      fixturesCache.put(teamId, teamGateway.getMatchesByTeamId(teamId).matches);
    }
    return fixturesCache.get(teamId);
  }
}
