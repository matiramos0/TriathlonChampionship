package Model.Athlete;

import java.time.LocalDate;

import Model.Discipline.Cycling;
import Model.Discipline.Discipline;
import Model.Discipline.Pedestrianism;
import Model.Discipline.Swimming;

public class Competence extends Athlete {

//public enum category{ JUNIOR, SUB23, ELITE };

private String category;
	
    public Competence(String number, String name, String last, String nacionality, int dni, int porcentageRacesCompleted, float weight, float height, double economy, String birthdate, Stats physicalsConditions) {
        super(number, name, last, nacionality, dni, porcentageRacesCompleted, weight, height, economy, birthdate, physicalsConditions);
    	this.category = calculateCategory(birthdate);
    }

    protected String calculateCategory(String birthDate) {	
    	//split the birthDate string and compare to the current date to get how years old the athlete is
    	String[] birth = birthDate.split("-");
    	LocalDate current = LocalDate.now();
    	int idCategoria = 0;
    	
    	int years = current.getYear() - Integer.parseInt(birth[0]);
    	
    	if ((current.getMonthValue() < Integer.parseInt(birth[1])))
    		years--;
    	
    	if (current.getDayOfMonth() < Integer.parseInt(birth[2]))
    		years--;
    	
    	if ((years > 14) && (years<20))
    	return "JUNIOR";							
    		else if ((years > 19) && (years < 24))
    			 return "SUB23";												
    				else return "ELITE";			
    }
    
    public String getCategory() {	
    	return this.category;
    }

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
    public void setGender(Gender gender) {
        this.gender = gender;
    }
	
	/*public String getCategory() {
    	String categoria;
    	if ((this.getCategory() > 14) && (this.getCategory() <20))
    		 categoria="junior";							
    		else if ((this.getCategory() > 19) && (this.getCategory() < 24))
    			categoria="sub23";					
    				else  categoria="elite";	
    	return categoria;
    }*/

	@Override
	public float getVelocity(Discipline discipline) {
    
		float velocity = 0;
		
		if (discipline.getClass().equals(Swimming.class))
			velocity = physicalsConditions.getVelocitySwimming();
		else if (discipline.getClass().equals(Cycling.class))
			velocity = physicalsConditions.getVelocityCycling();
		else if (discipline.getClass().equals(Pedestrianism.class))
			velocity = physicalsConditions.getVelocityStoning();
		
		return velocity;
	}
}
