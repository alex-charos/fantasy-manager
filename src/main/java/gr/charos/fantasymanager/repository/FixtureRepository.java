package gr.charos.fantasymanager.repository;

import gr.charos.fantasymanager.domain.Fixture;

import java.util.List;

public interface FixtureRepository {
  List<Fixture> getFixturesByTeamId(String teamId);

  Fixture getFixtureById(String fixtureId);

}
