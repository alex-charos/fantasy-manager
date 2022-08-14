package gr.charos.fantasymanager.domain;

import java.util.List;

public record SquadPrediction(Predictor predictor, String fixtureId, HomeAway side, List<Player> players) {

}
