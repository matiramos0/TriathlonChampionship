package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import Model.Athlete.Athlete;
import Model.Race.Race;
import XML.XMLCharge;

public class Championship {
	
	private static Championship currentInstance;
	
	private static List<Race> races;
	private static List<Athlete> athletes;

	public static void main(String[] args) {
		
		races = new ArrayList<>();
		athletes = new ArrayList<>();
		
		XMLCharge.chargeTriathlon(athletes, races);
		
		Race race = createNewRace();
		
		race.startRace();
		

	}
	
	public static Race createNewRace() {
		
		Random random = new Random();
				
		Race newRace = races.get(random.nextInt(races.size()));
		
		newRace.prepareRace(athletes);
		
		System.out.println("Datos de la carrera" + "\n");
		System.out.println("Modalidad: " + newRace.getModality().getModality().getDescription());
		System.out.println("Ubicacion: " + newRace.getCity().getDescription() + "\t" + newRace.getCity().getCountry().getDescription());
		System.out.println("Distancia: " + newRace.getModality().getTotalDistance() + "\n");
		
		return newRace;
	}
	
	 

}
