package gr.charos.fantasymanager.service;

import gr.charos.fantasymanager.Utils;
import gr.charos.fantasymanager.domain.League;
import gr.charos.fantasymanager.domain.LeagueParticipant;
import gr.charos.fantasymanager.domain.Predictor;
import gr.charos.fantasymanager.entity.LeagueEntity;
import gr.charos.fantasymanager.exceptions.LeagueNotFoundException;

import javax.inject.Singleton;
import java.util.HashSet;
import java.util.Optional;

@Singleton
public class LeagueServiceImpl implements LeagueService {


  @Override
  public League createLeague(League l) {
    LeagueEntity le = LeagueEntity.of(l);
    do {
      le.code = Utils.generateCode();
    } while (LeagueEntity.findByCode(l.code()) !=null);
    le.persist();

    return le.toLeague();
  }

  @Override
  public League joinLeague(String leagueCode, Predictor p) throws LeagueNotFoundException  {
    LeagueEntity l = Optional.ofNullable(LeagueEntity.findByCode(leagueCode)).orElseThrow(()-> new LeagueNotFoundException(leagueCode));
    if (l.participants == null) {
      l.participants = new HashSet<>();
    }
    l.participants.add(new LeagueParticipant(p,0));
    l.update();
    return l.toLeague();
  }

  @Override
  public League leaveLeague(String leagueCode, Predictor p) throws LeagueNotFoundException {
    LeagueEntity l = Optional.ofNullable(LeagueEntity.findByCode(leagueCode)).orElseThrow(()-> new LeagueNotFoundException(leagueCode));
    if (l.participants == null) {
      l.participants = new HashSet<>();
    }
    l.participants.remove(new LeagueParticipant(p,0));
    l.update();
    return l.toLeague();  }

  @Override
  public League getLeagueByCode(String leagueCode) throws LeagueNotFoundException {
    return Optional.ofNullable(LeagueEntity.findByCode(leagueCode)).orElseThrow(()-> new LeagueNotFoundException(leagueCode)).toLeague();
  }
}
