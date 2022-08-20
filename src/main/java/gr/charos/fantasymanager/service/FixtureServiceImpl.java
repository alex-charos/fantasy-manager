package gr.charos.fantasymanager.service;

import gr.charos.fantasymanager.domain.Fixture;
import gr.charos.fantasymanager.domain.Team;
import gr.charos.fantasymanager.gateway.FixtureGateway;
import gr.charos.fantasymanager.gateway.TeamGateway;
import gr.charos.fantasymanager.repository.FixtureRepository;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class FixtureServiceImpl implements FixtureService {

  @Inject
  FixtureRepository fixtureRepository;

  @Override
  public List<Fixture> getFixturesByTeamId(String teamId) {
    return  fixtureRepository.getFixturesByTeamId(teamId);
  }


}
