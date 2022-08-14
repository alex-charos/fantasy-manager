package gr.charos.fantasymanager.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Position {

	
	GOALKEEPER,
	DEFENDER,
	MIDFIELDER,
	ATTACKER;

 	@JsonCreator
	public static Position create(String position) {

		 if ("Goalkeeper".equalsIgnoreCase(position)) {
			 return GOALKEEPER;
		 }  else if ("Defence".equalsIgnoreCase(position) || "Defender".equalsIgnoreCase(position)) {
			 return DEFENDER;
		 } else if ("Midfield".equalsIgnoreCase(position) || "Midfielder".equalsIgnoreCase(position)) {
			 return  MIDFIELDER;
		 } else if ("Offence".equalsIgnoreCase(position) ||"Forward".equalsIgnoreCase(position) || "Attacker".equalsIgnoreCase(position) ) {
			 return  ATTACKER;
		 }
		throw new IllegalArgumentException("Could not map position : " + position);

	}

}
