package Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

import EventListeners.FinishRaceListener;
import EventListeners.NewChampionshipListener;
import EventListeners.NewRaceListener;
import EventListeners.RefreshViewListener;
import Model.Athlete.Athlete;
import Model.ClimateCondition.ClimateCondition;
import Model.Discipline.Provisioning;
import Model.Race.AthleteRaceInformation;
import Model.Race.Race;
import XML.XMLCharge;
import Model.View.AthletePanel;
import Model.View.MainView;
import Model.View.RaceView;

public class Championship implements NewRaceListener, RefreshViewListener, FinishRaceListener, NewChampionshipListener{
	
	private static Championship currentInstance;
	
	private static List<Race> races;
	private static List<Athlete> athletes;
	private static Race currentRace;
	private static RaceView currentRaceView;
	private Map<AthleteRaceInformation, AthletePanel> panels;
	private static List<Race> finishedRaces;
	
	public Championship() {
		races = new ArrayList<>();
		athletes = new ArrayList<>();	
		XMLCharge.chargeTriathlon(athletes, races);
		finishedRaces = new ArrayList<>();
	}
	
	public Race createNewRace() {
		Random random = new Random();			
		Race newRace = races.get(random.nextInt(races.size()));
		//Race newRace = races.get(0);

		newRace.prepareRace(athletes); // los carga como AthleteRace
		
		currentRaceView = new RaceView(newRace.getCity().getDescription(), currentInstance);
		currentRaceView.setVisible(true);
		
		panels = createPanels(newRace.getListAthletes(), newRace.getProvisioningCycling(), newRace.getProvisioningPedestrianism());
		
		System.out.println("Datos de la carrera" + "\n");
		System.out.println("Modalidad: " + newRace.getModality().getModalities().getDescription());
		System.out.println("Ubicacion: " + newRace.getCity().getDescription() + "\t" + newRace.getCity().getCountry().getDescription());
		System.out.println("Distancia: " + newRace.getModality().getTotalDistance() + "\n");
		
		return newRace;
	}
	
	 
	 
	 
	public HashMap<AthleteRaceInformation, AthletePanel> createPanels(List<AthleteRaceInformation> athletes, Map<Integer, Provisioning> provisioningCycling, Map<Integer, Provisioning> provisioningPedestrianism) {
		 
		 HashMap<AthleteRaceInformation, AthletePanel> map= new HashMap<AthleteRaceInformation, AthletePanel>();
		 int pos = 0;
		 for(AthleteRaceInformation athleteRace : athletes) {
				pos++;
				AthletePanel athletePanel = new AthletePanel(pos, provisioningCycling, provisioningPedestrianism,athleteRace.getModality());
				currentRaceView.getContentPane().add(athletePanel);
				athletePanel.setVisible(true);
				athletePanel.getLblAthlete().setText("Athlete: "+ athleteRace.getAthlete().getName());
				
				map.put(athleteRace, athletePanel);
			}
		 
		 return map;
	}
	 
	 //	Listeners

	 @Override    
	public synchronized void listenAdvancePanel(AthleteRaceInformation athleteRace) {//se ejecuta cada actualizacion de athleteRace
		try{
			AthletePanel panel = panels.get(athleteRace); 
			
			panel.advance(athleteRace.getAdvancedDistance());
			panel.getLblEnergy().setText(String.format("Fatigue: %.2f" , athleteRace.getFatigue()));

		}catch(Exception e) {
			 e.printStackTrace(System.err);
		}
	}
	 
	 @Override
	public void listenStartNewChampionship() {	
		currentInstance = this;
		listenStartNewRace();		
	 }
	 
	 @Override  
	public void listenStartNewRace() {	
		currentRace = createNewRace();	
		currentRace.startRace();
	 }
	 
	 @Override
	public void listenFinishRace() {
		finishedRaces.add(currentRace); // Remove from races?
		currentRaceView.seeRanking(finishedRaces, currentRace);
		
		currentRaceView.dispose();		
		if(currentRaceView.askNewRace()) {
			 listenStartNewRace();
		}  
	}
	 
	public void listenShowCurrentRanking(){
		currentRaceView.seeRanking(finishedRaces, currentRace);
	}
	 
	 @Override
	public void listenRefreshView(int time, ClimateCondition currentWeather) {
		currentRaceView.getLblRaceTime().setText("Race Time: " + Integer.toString(time) + " seconds");
		currentRaceView.getLblClimateCondition().setText("Climate condition:"+currentWeather.getDescription());
		
	}	
	 
	 @Override
	public void listenRefreshPositions() {
		 /**/Collections.sort(currentRace.getListAthletes(), new Comparator<AthleteRaceInformation>() {
						@Override
						public int compare(AthleteRaceInformation o1, AthleteRaceInformation o2) {
							if ((o2.getAdvancedDistance() - o1.getAdvancedDistance()) < 0)
								return -1;
								else   return 1;
						}
				 		}); 
		 
		 for (int i = 0; i < currentRace.getListAthletes().size(); i++)
			 currentRace.getListAthletes().get(i).setPosition(i+1);;	
			 //Change Position attribute of each athlete sorted by advanced distance		 
			 
		 for (AthleteRaceInformation athleteRace : panels.keySet()) {		 
			 AthletePanel panel = panels.get(athleteRace);	    
			 panel.refreshPositions(athleteRace.getPosition(), athleteRace.isOut());
		 }	 		
	}

	//Getters and Setters
	 public static Championship getInstance() {
	        if (currentInstance == null) {
	        	currentInstance = new Championship();
	        }     
	        return currentInstance;
	    }

	public void listenPauseRace() throws InterruptedException {
		//currentRace.pauseRace();
	/**/for(AthleteRaceInformation a : currentRace.getListAthletes()) 
		a.setStopped(true);
			//synchronized(currentRace.getListAthletes().get(i)) 
			
			
	}

	public void listenResumeGame() throws InterruptedException {
		/*
		for(AthleteRaceInformation athlete : currentRace.getListAthletes()) 
				athlete.setStopped(false);
		*/		
	}

	

	

	
	
}
