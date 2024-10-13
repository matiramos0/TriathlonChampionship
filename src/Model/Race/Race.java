package Model.Race;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import Controller.Championship;
import DAO.WeatherConditionsDAO;
import Model.City.City;
import Model.Modality.Modality;
import Model.View.RaceView;
import Model.ClimateCondition.ClimateCondition;
import Model.Discipline.Provisioning;
import Model.Athlete.Athlete;

public class Race extends Thread implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public static final long speedOfRace = 50; // miliseconds
	
	private Modality modality;
	private City city;
	private ClimateCondition currentWeather;
	private boolean stopped;
	private static int remainingAthletes;
	private Map <Integer, Provisioning> provisioningPedestrianism;
	private Map <Integer, Provisioning> provisioningCycling;
	private List <AthleteRaceInformation> listAthletes;
	private List<AthleteRaceInformation> activeAthletes;
	//private RaceView raceView; 

	//Constructor Method

	public Race(Modality modality, City city, Map <Integer, Provisioning> cycling, 
			Map <Integer, Provisioning> pedestrianism) throws SQLException{
		this.modality = modality;
		this.city = city;
		this.provisioningCycling = cycling;
		this.provisioningPedestrianism = pedestrianism;
		List<ClimateCondition> weatherConditions = WeatherConditionsDAO.getAllWeatherConditions();
	    this.currentWeather = ClimateCondition.getRandomWeatherCondition(weatherConditions);  // Obtener clima aleatorio
	    this.stopped = false;
	    this.activeAthletes=new ArrayList<>();
	    this.remainingAthletes=0;
	 }
	
	
	//Methods
	
	public void prepareRace(List<Athlete> athletes) {
		
		listAthletes = new ArrayList<AthleteRaceInformation>();
		
		for (Athlete athlete : athletes) {
			AthleteRaceInformation athleteRace = new AthleteRaceInformation(athlete, modality, currentWeather, provisioningCycling, provisioningPedestrianism);
			listAthletes.add(athleteRace);
			activeAthletes.add(athleteRace);
			remainingAthletes++;
			
		}
	}
	
	@Override
	public void run() {
		
		try {
			
			float time = 0;
			
			for (AthleteRaceInformation athlete: listAthletes)
				athlete.start();
			
			//listAthletes.get(0).start();
			
			while (!listAthletes.isEmpty()) {
				//System.out.println("Tiempo: " + time);
				
				for (AthleteRaceInformation athlete : listAthletes) {
	                if (athlete.getAdvancedDistance() >= athlete.getModality().getTotalDistance()) {
	                    athleteFinished(athlete);
	                }
	            }
				
				try {
					Random random = new Random();
					if (random.nextInt(35) == 1) {
						List<ClimateCondition> weatherConditions = WeatherConditionsDAO.getAllWeatherConditions();
						currentWeather = ClimateCondition.getRandomWeatherCondition(weatherConditions);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			    
				Championship.getInstance().listenRefreshView(time, currentWeather); // O Atributo controller?
				Championship.getInstance().listenRefreshPositions();
				
				time = time + 0.1F;
				
				try {
					
					if (stopped) {
						synchronized (this) {
							while(stopped)
								sleep(0);
						}
					}
				} catch (InterruptedException i) {
					i.printStackTrace();
				}
				
				sleep(speedOfRace);
			}
			
			Championship.getInstance().listenFinishRace();
			
		} catch (InterruptedException e) {
			e.getStackTrace();
		}		
	}
	
	public void pauseRace() throws InterruptedException {
		for (AthleteRaceInformation athlete: listAthletes)
			athlete.setStopped(stopped);
	}
	
	public void resumeRace() throws InterruptedException {
		for (AthleteRaceInformation athlete: listAthletes)
			athlete.setStopped(stopped);
	}

	//Getters and Setters
	public Modality getModality() {
		return modality;
	}

	public void setModality(Modality modality) {
		this.modality = modality;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	public ClimateCondition getCurrentWeather() {
		return currentWeather;
	}


	public void setCurrentWeather(ClimateCondition currentWeather) {
		this.currentWeather = currentWeather;
	}

	public Map<Integer, Provisioning> getProvisioningPedestrianism() {
		return provisioningPedestrianism;
	}


	public void setProvisioningPedestrianism(Map<Integer, Provisioning> provisioningPedestrianism) {
		this.provisioningPedestrianism = provisioningPedestrianism;
	}


	public Map<Integer, Provisioning> getProvisioningCycling() {
		return provisioningCycling;
	}


	public void setProvisioningCycling(Map<Integer, Provisioning> provisioningCycling) {
		this.provisioningCycling = provisioningCycling;
	}


	public ArrayList<AthleteRaceInformation> getListAthletes() {
		return (ArrayList<AthleteRaceInformation>) listAthletes;
	}

	public void setListAthletes(List<AthleteRaceInformation> listathletes) {
		this.listAthletes = listathletes;
	}

	public boolean isStopped() {
		return stopped;
	}

	public void setStopped(boolean stopped) {
		this.stopped = stopped;
	}
	
	
	public List<AthleteRaceInformation> getActiveAthletes() {
		return activeAthletes;
	}

	public void athleteFinished(AthleteRaceInformation athlete) {
	    activeAthletes.remove(athlete);
	    remainingAthletes--;
	}
	    
	    public boolean isFinished() {
	        return remainingAthletes == 0;  
	    }

}
