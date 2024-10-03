package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import Model.Athlete.Athlete;
import Model.Race.Race;
import XML.XMLCharge;
import Model.View.MainView;
import Model.View.RaceView;

public class Championship {
	
	private static Championship currentInstance;
	
	private static List<Race> races;
	private static List<Athlete> athletes;
	private static Race currentRace;

	public Championship() {
		races = new ArrayList<>();
		athletes = new ArrayList<>();	
		XMLCharge.chargeTriathlon(athletes, races);
		
	}
	
	public static Race createNewRace() {
		
		Random random = new Random();
				
		Race newRace = races.get(random.nextInt(races.size()));
		
		newRace.prepareRace(athletes);
		
		System.out.println("Datos de la carrera" + "\n");
		System.out.println("Modalidad: " + newRace.getModality().getModalities().getDescription());
		System.out.println("Ubicacion: " + newRace.getCity().getDescription() + "\t" + newRace.getCity().getCountry().getDescription());
		System.out.println("Distancia: " + newRace.getModality().getTotalDistance() + "\n");
		
		return newRace;
	}
	
	 public void listenStartNewChampionship() {	
		currentRace = createNewRace();	
		currentRace.startRace();
		
	 }
	 
	 public void OpenWindowRace() {
		 RaceView raceView = new RaceView();
		 raceView.setVisible(true);
	 }
	 
	 public static Championship getInstance() {
	        if (currentInstance == null) {
	        	currentInstance = new Championship();
	        }
	        
	        return currentInstance;
	    }

}
