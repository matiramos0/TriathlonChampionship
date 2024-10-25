package Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import Model.Race.*;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import java.util.stream.Collectors;

import EventListeners.FinishRaceListener;
import EventListeners.NewChampionshipListener;
import EventListeners.NewRaceListener;
import EventListeners.RefreshPositionListener;
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
import Model.View.Ranking;

public class Championship implements Serializable, NewRaceListener, RefreshViewListener, RefreshPositionListener, FinishRaceListener, NewChampionshipListener{
	
	private static final long serialVersionUID = 1L;

	private static Championship currentInstance;
	
	private List<Race> races;
	private List<Athlete> athletes;
	private Race currentRace;
	private RaceView currentRaceView;
	private Map<AthleteRaceInformation, AthletePanel> panels;
	//private List<Race> finishedRaces;
	
	public Championship() {
		races = new ArrayList<Race>();
		athletes = new ArrayList<Athlete>();	
		XMLCharge.chargeTriathlon(athletes, races);
		//finishedRaces = new ArrayList<>();
	}
	
	public Race createNewRace() {

		Random random = new Random();			

		Race newRace = races.get(random.nextInt(races.size()));

		newRace.prepareRace(athletes); // los carga como AthleteRace
		
		currentRaceView = new RaceView(newRace.getCity().getDescription() +" - " + newRace.getModality().getModalities().name());
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
				AthletePanel athletePanel = new AthletePanel(pos, provisioningCycling, provisioningPedestrianism, athleteRace.getRace().getModality());
				currentRaceView.getContentPane().add(athletePanel);
				athletePanel.setVisible(true);
				athletePanel.getLblAthlete().setText("Athlete: "+ athleteRace.getAthlete().getName());
				
				map.put(athleteRace, athletePanel);
			}
		 
		 return map;
	}
	 
	 //	Event Listeners

	 @Override    
	public void listenAdvancePanel(AthleteRaceInformation athleteRace) {//se ejecuta cada actualizacion de distancia de athleteRace
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
		if(currentRaceView != null && currentRaceView.isActive())
			currentRaceView.dispose();
		
		if(races.size() == 0)
			listenFinishChampionship();
		currentRace = createNewRace();
	 }
	 
	@Override
	public void listenStartRace() {
		 currentRace.start();
	}
	 
	@Override
	public void listenFinishRace() {
		if (currentRace.isFinished()) {
			listenRefreshChampionshipPositions();
			races.remove(currentRace);
			currentRaceView.finishRace();
		} else
			currentRace.cancelRace(); // Se ha abandonado la carrera sin terminar, los datos no se guardan y se interrumpen los hilos
	}
	
	private void listenFinishChampionship() {
		JOptionPane.showInternalMessageDialog(null, "The Winner of the Championship is: " + athletes.getFirst().getName(),"Championship End", 4, (new ImageIcon("img\\trofeo.png")));
	}

	@Override 
	public void listenShowCurrentRanking() {
		Ranking r = new Ranking(athletes);
		r.setLocationRelativeTo(null);
		r.setVisible(true);
		currentRaceView.pause();
	}

	@Override
	public void listenRefreshView(float time, ClimateCondition currentWeather) {
		currentRaceView.getLblRaceTime().setText("Race Time: " + Float.valueOf(time).shortValue() + " seconds");
		currentRaceView.getLblClimateCondition().setText("Climate condition:" + currentWeather.getDescription());

	}	
	 
	@Override
	public synchronized void listenRefreshRacePositions() {
		 //Ordena por distancia avanzada 
		 Collections.sort(currentRace.getListAthletes(), new Comparator<AthleteRaceInformation>() {
				@Override
				public int compare(AthleteRaceInformation o1, AthleteRaceInformation o2) {
					//IF anidado con el proposito de no seguir actualizando posiciones de atletas que ya llegaron a la meta
					//Si ambos terminaron, no se cambia la posicion
					if (o1.getAdvancedDistance() >= currentRace.getModality().getTotalDistance()
					 && o2.getAdvancedDistance() >= currentRace.getModality().getTotalDistance())
						return 0;   
					else if (o1.getAdvancedDistance() >= currentRace.getModality().getTotalDistance())
						return -1;  //Si uno termino y el otro no, deja primero el que ya termino 
					else if (o2.getAdvancedDistance() >= currentRace.getModality().getTotalDistance())
						return 1;

					if (o2.getAdvancedDistance() - o1.getAdvancedDistance() < 0)
						return -1;
					else if (o2.getAdvancedDistance() - o1.getAdvancedDistance() > 0)
						return 1;
					else
						return 0;
				}	
		 });
		 //Actualizar atributo Position de cada atleta basado en el ordenamiento por distancia avanzada
		 for (int i = 0; i < currentRace.getListAthletes().size(); i++)								 
			 if(currentRace.getListAthletes().get(i).isOut() == false)
				 currentRace.getListAthletes().get(i).setPosition(i+1);	
		
		 //Actualiza segun las posiciones de los atletas sus respectivos paneles y ubica los primeros 8 visibles 	 
		 for (AthleteRaceInformation athleteRace : panels.keySet()) {		 
			 AthletePanel panel = panels.get(athleteRace);	    
			 panel.refreshPositions(athleteRace.getPosition(), athleteRace.isOut());
		}	 		
	}
	 
	@Override
	public void listenRefreshChampionshipPositions() {
		Collections.sort(athletes, new Comparator<Athlete>() {
			@Override
			public int compare(Athlete o1, Athlete o2) {
				return -1 * (o1.getChampionshipPoints() - o2.getChampionshipPoints());
			}
		});

		for (int i = 0; i < athletes.size(); i++)
			athletes.get(i).setChampionshipPosition(i + 1);
	}

	@Override
	public void listenInterruptRace(boolean interruption) throws InterruptedException {
		currentRace.interruptRace(interruption);
	}

	// Getters and Setters
	public static Championship getInstance() {
		if (currentInstance == null) {
			currentInstance = new Championship();
		}
		return currentInstance;
	}
	
}
