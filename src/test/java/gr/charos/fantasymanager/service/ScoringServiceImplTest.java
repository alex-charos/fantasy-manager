package gr.charos.fantasymanager.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class ScoringServiceImplTest {
  ScoringService impl;

  @BeforeEach
  public void setup(){
    impl = new ScoringServiceImpl();
  }

  @Test
  public void testScores() {

    double score = impl.scorePrediction(LocalDateTime.now().minusHours(12), LocalDateTime.now(), 11);
    Assertions.assertEquals(66.0, score);

    score = impl.scorePrediction(LocalDateTime.now().minusMinutes(12), LocalDateTime.now(), 11);
    Assertions.assertEquals(0, score);

    score = impl.scorePrediction(LocalDateTime.now().minusDays(16), LocalDateTime.now(), 11);
    Assertions.assertEquals(165, score);
  }
}
