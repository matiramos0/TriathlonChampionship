package Model.Athlete;

import java.time.LocalDate;

import Model.Discipline.Cycling;
import Model.Discipline.Discipline;
import Model.Discipline.Pedestrianism;
import Model.Discipline.Swimming;

public class Competition extends Athlete {

//public enum category{ JUNIOR, SUB23, ELITE };
	
    public Competition(String number, String name, String last, String nacionality, int dni, int porcentageRacesCompleted, float weight, float height, double economy, String birthdate, Stats physicalsConditions) {
        super(number, name, last, nacionality, dni, porcentageRacesCompleted, weight, height, economy, birthdate, physicalsConditions);
    	this.category = calculateCategory(calculateAge(birthdate));
    }
    
    @Override
    public String calculateCategory(int years) {	
    	//split the birthDate string and compare to the current date to get how years old the athlete is
    	if ((years > 14) && (years<20))
    		return "JUNIOR";							
    	else if ((years > 19) && (years < 24))
    		return "SUB23";												
    	else 
    		return "ELITE";			
    }
    
}
