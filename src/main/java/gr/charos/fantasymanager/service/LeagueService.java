package gr.charos.fantasymanager.service;

import gr.charos.fantasymanager.domain.League;
import gr.charos.fantasymanager.domain.Predictor;
import gr.charos.fantasymanager.exceptions.LeagueNotFoundException;

import java.util.List;

public interface LeagueService {

  League createLeague(League l);

  League joinLeague(String leagueCode, Predictor p) throws LeagueNotFoundException;

  League leaveLeague(String leagueCode, Predictor p) throws LeagueNotFoundException;

  League getLeagueByCode(String leagueCode) throws LeagueNotFoundException;

}
