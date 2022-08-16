package gr.charos.fantasymanager.service;

import gr.charos.fantasymanager.domain.Fixture;

import java.util.List;

public interface FixtureService {
  List<Fixture> getFixturesByTeamId(String teamId);
}
