package Model.Athlete;

import java.time.LocalDate;

import Model.Discipline.Discipline;

public class Amateur extends Athlete {

private String category;	

	public Amateur(String number, String name, String last, String nacionality, int dni, int porcentageRacesCompleted, float weight, float height, double economy, String birthdate, Stats physicalsConditions) {
        super(number, name, last, nacionality, dni, porcentageRacesCompleted, weight, height, economy, birthdate, physicalsConditions);
        this.category = calculateCategory(birthdate);
	}
	
	@Override
	protected String calculateCategory(String birthDate) {	
    	//split the birthDate string and compare to the current date to get how years old the athlete is
    	String[] birth = birthDate.split("-");
    	LocalDate current = LocalDate.now();
    	
    	int years = current.getYear() - Integer.parseInt(birth[0]);
    	if ((current.getMonthValue() < Integer.parseInt(birth[1]))) 
    		years--;
    		else if ((current.getMonthValue() == Integer.parseInt(birth[1])) && (current.getDayOfMonth() < Integer.parseInt(birth[3])))
    			years--;
    	String categoria;
		
    	if ((years >= 19) && (years <= 24))
    	  categoria = "U24";	
		else if ((years >= 25) && (years <= 29))
			categoria = "U29";	
				else if ((years >= 30) && (years <= 34)) 
					categoria = "U34";	
						else if ((years >= 35) && (years <= 39)) 
							categoria = "U39";	
								else if((years >= 40) && (years <= 44)) 
									categoria = "U44";	
										else if ((years >= 45) && (years <= 49)) 
											categoria = "U49";	
												else if ((years >= 50) && (years <= 54)) 
													categoria = "U54";	
														else if ((years >= 55) && (years <= 59)) 
															categoria = "U59";	
																else if ((years >= 60) && (years <= 64))
																	categoria = "U64";	
																		else if ((years >= 65) && (years <= 69)) 
																			categoria = "U69";	
																				else if((years >= 70) && (years <= 74))
																						categoria = "U74";	
																					else categoria = "+75";
    	return categoria;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
    public void setGender(Gender gender) {
        this.gender = gender;
    }

	@Override
	public float getVelocity(Discipline discipline) {
		// TODO Auto-generated method stub
		return 0;
	}

}
