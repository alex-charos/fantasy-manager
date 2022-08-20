package gr.charos.fantasymanager.service;

import javax.inject.Singleton;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@Singleton
public class ScoringServiceImpl implements  ScoringService {
  @Override
  public double scorePrediction(LocalDateTime predictionDate, LocalDateTime fixtureDate, int correct) {
    long hoursAgo = Duration.between(predictionDate, fixtureDate).toHours();
    double timeWeight = 1.0;

    // >= 2 hours ago = 0 points
    // >= 1 day ago   = 1.0 accumulator
    // >= 3 days ago  = 1.1 accumulator
    // >= 5 days ago  = 1.2 accumulator
    // >= 7 days ago  = 1.4 accumulator
    // >= 14 days ago = 2.0 accumulator
    // anything else  = 4.0 accumulator
    if (2l >= hoursAgo) {
      timeWeight = 0.0;
    } else if (24 >= hoursAgo) {
      timeWeight = 1.0;
    } else if ( (24*3) >= hoursAgo) {
      timeWeight = 1.1;
    } else if ( (24*5) >= hoursAgo) {
      timeWeight = 1.2;
    } else if ( (24*7) >= hoursAgo) {
      timeWeight = 1.5;
    }else if ( (24*14) >= hoursAgo) {
      timeWeight = 2.0;
    } else {
      timeWeight = 2.5;
    }

    BigDecimal points = BigDecimal.ZERO;
    for (; correct >0; correct--) {
     points = points.add( BigDecimal.valueOf(correct).multiply(BigDecimal.valueOf(timeWeight)));

    }
    return points.doubleValue();
  }
}
