package Controller;

import java.util.ArrayList;
import java.util.List;

import Model.Athlete.Athlete;
import Model.Race.Race;
import XML.XMLCharge;

public class Championship {
	
	private static List<Race> races = new ArrayList<Race>();
	private static List<Athlete> athletes = new ArrayList<Athlete>();

	public static void main(String[] args) {
		
		XMLCharge.chargeTriathlon(athletes, races);
		
		//for(Athlete athlete: athletes) {
			
			//System.out.println(athlete);
			
		//}

	}
	
	 

}
