package Model.Athlete;

import java.time.LocalDate;

public class Amateur extends Athlete {

private int category;	

	public Amateur(String number, String name, String last, String nacionality, int dni, int porcentageRacesCompleted, float weight, float height, double economy, String birthdate, Stats physicalsConditions) {
        super(number, name, last, nacionality, dni, porcentageRacesCompleted, weight, height, economy, birthdate, physicalsConditions);
        this.category = calculateCategory(birthdate);
	}
	
	private int calculateCategory(String birthDate) {	
    	//split the birthDate string and compare to the current date to get how years old the athlete is
    	String[] birth = birthDate.split("-");
    	LocalDate current = LocalDate.now();
    	
    	int years = current.getYear() - Integer.parseInt(birth[0]);
    	if ((current.getMonthValue() < Integer.parseInt(birth[1]))) 
    		years--;
    		else if ((current.getMonthValue() == Integer.parseInt(birth[1])) && (current.getDayOfMonth() < Integer.parseInt(birth[3])))
    			years--;
    	int categoria = 0;
    	/*switch(years) {
    		case years >= 19 && years <= 24 : categoria = 1;	
    		break;
    		case years >= 25 && years <= 29 : categoria = 2;	
    		break;
    		case years >= 30 && years <= 34 : categoria = 3;	
    		break;
    		case years >= 35 && years <= 39 : categoria = 4;	
    		break;
    		case years >= 40 && years <= 44 : categoria = 5;	
    		break;
    		case years >= 45 && years <= 49 : categoria = 6;	
    		break;
    		case years >= 50 && years <= 54 : categoria = 7;	
    		break;
    		case years >= 55 && years <= 59 : categoria = 8;	
    		break;
    		case years >= 60 && years <= 64 : categoria = 9;	
    		break;
    		case years >= 65 && years <= 69 : categoria = 10;	
    		break;
    		case years >= 70 && years <= 74 : categoria = 11;	
    		break;
    		case years >= 75 				: categoria = 12;
    		break;   		    		    		    		    		    		    		    		    		    		    		  
    	}*/
    	
    	return categoria;
	}	

	@Override
    public void setGender(Gender gender) {
        this.gender = gender;
    }

}
