package gr.charos.fantasymanager.service;

import gr.charos.fantasymanager.domain.Fixture;
import gr.charos.fantasymanager.domain.Player;
import gr.charos.fantasymanager.domain.PredictionResult;
import gr.charos.fantasymanager.domain.Team;
import gr.charos.fantasymanager.entity.LeagueEntity;
import gr.charos.fantasymanager.entity.PredictionResultEntity;
import gr.charos.fantasymanager.entity.SquadPredictionEntity;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Singleton
public class LineupService {

  @Inject
  ScoringService scoringService;

  public void lineupReceived(Fixture fixture,  Team team, List<Player> lineup) {
    List<SquadPredictionEntity> predictions = SquadPredictionEntity.findByFixtureIdAndTeam(fixture.id(), team.id());

    List<PredictionResult> results = new ArrayList<>();

      for (SquadPredictionEntity prediction : predictions) {
        List<Boolean> correct = lineup.stream().map(p->prediction.getPlayers().contains(p)).collect(Collectors.toList());
        double score = scoringService.scorePrediction(prediction.predictionDate, fixture.date().toLocalDateTime(), correct.size() );
        PredictionResult result = new PredictionResult(prediction.predictor, fixture.id(), team.id(), correct.size(), score,prediction.predictionDate, fixture.date().toLocalDateTime(), LocalDateTime.now());

        List<LeagueEntity> leagues = LeagueEntity.findByPredictor(prediction.predictor.id());
        leagues.stream().forEach(league-> {
          league.participants = league.participants.stream().map(p-> p.addPoints(score)).collect(Collectors.toSet());
        });

        results.add(result);
      }

      results.stream().map(PredictionResultEntity::of).forEach( p-> p.persist());

  }

}
