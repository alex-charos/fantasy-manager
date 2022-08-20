package gr.charos.fantasymanager.service;

import gr.charos.fantasymanager.domain.*;
import gr.charos.fantasymanager.entity.FixtureLineupEntity;
import gr.charos.fantasymanager.entity.LeagueEntity;
import gr.charos.fantasymanager.entity.PredictionResultEntity;
import gr.charos.fantasymanager.entity.SquadPredictionEntity;
import gr.charos.fantasymanager.repository.FixtureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

  Logger LOGGER = LoggerFactory.getLogger(LineupService.class);

  @Inject
  ScoringService scoringService;

  @Inject
  FixtureRepository fixtureRepository;

  public void lineupReceived(FixtureLineup lineup) {


    FixtureLineupEntity existing =  FixtureLineupEntity.findByFixtureIdAndTeam(lineup.fixtureId(), lineup.team());
    if (existing!=null) {
      LOGGER.info("Fixture Entity Already exists {}", existing);
      existing.lineup = lineup.lineup();
      existing.update();
    } else {
      FixtureLineupEntity fle = FixtureLineupEntity.of(lineup);
      fle.persist();
    }

    List<SquadPredictionEntity> predictions = SquadPredictionEntity.findByFixtureIdAndTeam(lineup.fixtureId(), lineup.team().id());

    Fixture fixture = fixtureRepository.getFixtureById(lineup.fixtureId());
    Team team  = lineup.team().id().equalsIgnoreCase(fixture.homeTeam().id())? fixture.homeTeam():fixture.awayTeam();

    List<PredictionResult> results = new ArrayList<>();

      for (SquadPredictionEntity prediction : predictions) {
        int correct = lineup.lineup().stream().map(p->prediction.getPlayers().contains(p)).filter(Boolean::booleanValue).collect(Collectors.toList()).size();

        double score = scoringService.scorePrediction(prediction.predictionDate, fixture.date().toLocalDateTime(),  correct );
        PredictionResult result = new PredictionResult(prediction.predictor,
                                                        fixture.id(),
                                                        team.id(),
                                                        correct,
                                                        score,
                                                        prediction.predictionDate,
                                                        fixture.date().toLocalDateTime(),
                                                        LocalDateTime.now()
                                          );

        List<LeagueEntity> leagues = LeagueEntity.findByPredictor(prediction.predictor.id());
        leagues.stream().forEach(league-> {
          if (league.getScoredFixtureIds().add(fixture.id())) {
            league.participants = league.participants.stream().map(p->  {
                if (p.predictor().id().equals(prediction.predictor.id())) {
                  p = p.addPoints(score);
                }
                return  p;
              })

              .collect(Collectors.toSet());
            league.update();
          }
          }
        });

        results.add(result);
      }

      results.stream().map(PredictionResultEntity::of).forEach( p-> p.persist());

  }

}
