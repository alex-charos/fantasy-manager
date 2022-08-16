package gr.charos.fantasymanager.service;

import gr.charos.fantasymanager.domain.Team;

import java.util.List;

public interface TeamService {

  List<Team> getTeamsByCompetition(String competitionId);

  Team getTeamById(String teamId);

}
