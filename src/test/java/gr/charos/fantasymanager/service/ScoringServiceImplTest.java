package gr.charos.fantasymanager.service;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
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

  @Test
  public void test() {

    ConnectionString connectionString = new ConnectionString("mongodb://fantasymanagerservice:e293tWZf8ClBPkSS@ac-6arjs0t-shard-00-00.jkgehaw.mongodb.net:27017,ac-6arjs0t-shard-00-01.jkgehaw.mongodb.net:27017,ac-6arjs0t-shard-00-02.jkgehaw.mongodb.net:27017/?ssl=true&replicaSet=atlas-wssay1-shard-0&authSource=admin&retryWrites=true&w=majority");
    MongoClientSettings settings = MongoClientSettings.builder()
      .applyConnectionString(connectionString)
      .build();
    MongoClient mongoClient = MongoClients.create(settings);
    MongoDatabase database = mongoClient.getDatabase("test");

  }
}
