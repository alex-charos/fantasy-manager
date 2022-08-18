package gr.charos.fantasymanager.exceptions;

public class LeagueNotFoundException extends Exception {


  public LeagueNotFoundException(String leagueCode) {
    super("League " + leagueCode + " does not exist");
  }
}
