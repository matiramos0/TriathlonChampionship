package Model.Race;

import java.sql.SQLException;
import java.util.ArrayList;
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

public class Race {

	private Modality modality;
	private City city;
	private ClimateCondition currentWeather;
	private Map <Integer, Provisioning> provisioningPedestrianism;
	private Map <Integer, Provisioning> provisioningCycling;
	private List <AthleteRaceInformation> listAthletes;
	//private RaceView raceView; 

	//Constructor Method

	public Race(Modality modality, City city, Map <Integer, Provisioning> cycling, Map <Integer, Provisioning> pedestrianism) throws SQLException{
		this.modality = modality;
		this.city = city;
		this.provisioningCycling = cycling;
		this.provisioningPedestrianism = pedestrianism;
		List<ClimateCondition> weatherConditions = WeatherConditionsDAO.getAllWeatherConditions();
	    this.currentWeather = ClimateCondition.getRandomWeatherCondition(weatherConditions);  // Obtener clima aleatorio
	    
	 }
	
	
	//Methods
	
	public void prepareRace(List<Athlete> athletes) {
		
		listAthletes = new ArrayList<AthleteRaceInformation>();
		
		for (Athlete athlete : athletes) {
			AthleteRaceInformation athleteRace = new AthleteRaceInformation(athlete, modality, currentWeather, provisioningPedestrianism, provisioningCycling);
			listAthletes.add(athleteRace);
			
		}
	}
	
	public void startRace() {
		
		for (AthleteRaceInformation athlete: listAthletes) {
			athlete.start();
		}
		
		Timer timer = new Timer();
		
		TimerTask task = new TimerTask() {
			
			int time =0;
			
			@Override
			public void run() {
				
				System.out.println("Tiempo: " + time);
				time++;
				
				Championship.getInstance().listenRefreshView(time, currentWeather); // O Atributo controller?
				Championship.getInstance().listenRefreshPositions();
				
				if (time == 150) {
					timer.cancel();
					Championship.getInstance().listenFinishRace();
				}
			}
		};
		
		timer.schedule(task, 0, 1000);
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
	
	
	
}
