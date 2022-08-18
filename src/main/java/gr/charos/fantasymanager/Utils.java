package gr.charos.fantasymanager;

import org.apache.commons.lang3.RandomStringUtils;

public class Utils {

  public static String generateCode(){
    return  RandomStringUtils.random(8,true,false).toUpperCase();

  }
}
