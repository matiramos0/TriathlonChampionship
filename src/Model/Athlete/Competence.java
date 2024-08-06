package Model.Athlete;

import java.time.LocalDate;

public class Competence extends Athlete {

public enum category{ JUNIOR, SUB23, ELITE };

private category category;
	
    public Competence(String number, String name, String last, String nacionality, int dni, int porcentageRacesCompleted, float weight, float height, double economy, String birthdate, Stats physicalsConditions) {
        super(number, name, last, nacionality, dni, porcentageRacesCompleted, weight, height, economy, birthdate, physicalsConditions);
    	//this.category = calculateCategory(birthdate);
    }

    /*private category calculateCategory(String birthDate) {	
    	//split the birthDate string and compare to the current date to get how years old the athlete is
    	String[] birth = birthDate.split("-");
    	LocalDate current = LocalDate.now();
    	
    	int years = current.getYear() - Integer.parseInt(birth[0]);
    	
    	if ((current.getMonthValue() < Integer.parseInt(birth[1])))
    		years--;
    	
    	if (current.getDayOfMonth() < Integer.parseInt(birth[3]))
    		years--;
    	
    	if ((years > 14) && (years<20))
    		return category.JUNIOR;
    		else if ((years > 19) && (years < 24))
    				return category.SUB23;
    			else return category.ELITE;
    }*/

    public category getCategory() {
    	return category;
    }
    
    @Override
    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
