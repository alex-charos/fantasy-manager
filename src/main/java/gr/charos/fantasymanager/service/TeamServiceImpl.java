package gr.charos.fantasymanager.service;

import gr.charos.fantasymanager.domain.Team;
import gr.charos.fantasymanager.gateway.TeamGateway;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class TeamServiceImpl implements  TeamService {

  Logger LOGGER = LoggerFactory.getLogger(TeamServiceImpl.class);
  @RestClient
  TeamGateway teamGateway;

  private static ConcurrentHashMap<String, List<Team>> competitionsCache = new ConcurrentHashMap<>();
  private static ConcurrentHashMap<String, Team> teamsCache = new ConcurrentHashMap<>();
  @Override
  public List<Team> getTeamsByCompetition(String competitionId) {
    if (!competitionsCache.containsKey(competitionId)) {
        List<Team> teams =teamGateway.getTeamsByCompetition(competitionId).teams;
        competitionsCache.put(competitionId, teams);
        teams.stream().forEach(team -> {
          if (!teamsCache.containsKey(team.id())) {
            teamsCache.put(team.id(), team);
          }
        });

    } else {
        LOGGER.debug("Competition {} teams returned from cache ", competitionId);
    }
    return competitionsCache.get(competitionId);
  }

  @Override
  public Team getTeamById(String teamId) {
    if (!teamsCache.containsKey(teamId)) {
      teamsCache.put(teamId, teamGateway.getTeamByTeamId(teamId));
    } else {
      LOGGER.debug("Team {} returned from cache ", teamId);
    }
    return  teamsCache.get(teamId);
  }
}
