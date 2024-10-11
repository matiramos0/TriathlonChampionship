package Model.Race;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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

public class Race extends Thread{

	private Modality modality;
	private City city;
	private ClimateCondition currentWeather;
	private Map <Integer, Provisioning> provisioningPedestrianism;
	private Map <Integer, Provisioning> provisioningCycling;
	private List <AthleteRaceInformation> listAthletes;
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
	    
	 }
	
	
	//Methods
	
	public void prepareRace(List<Athlete> athletes) {
		
		listAthletes = new ArrayList<AthleteRaceInformation>();
		
		for (Athlete athlete : athletes) {
			AthleteRaceInformation athleteRace = new AthleteRaceInformation(athlete, modality, currentWeather, provisioningCycling, provisioningPedestrianism);
			listAthletes.add(athleteRace);
			
		}
	}
	
	@Override
	public void run() {
		
		try {
			
			for (AthleteRaceInformation athlete: listAthletes)
				athlete.start();
			
			//listAthletes.get(0).start();
			
			float time = 0;
			
			while (!listAthletes.isEmpty() && time <= 40) {
				
				//System.out.println("Tiempo: " + time);
				
				Championship.getInstance().listenRefreshView(time, currentWeather); // O Atributo controller?
				Championship.getInstance().listenRefreshPositions();
				
				time = time + 0.1F;
				
				sleep(AthleteRaceInformation.speedOfRace);
			}
			
			Championship.getInstance().listenFinishRace();
			
			for (AthleteRaceInformation athlete: listAthletes)
				athlete.stop();
			
		} catch (InterruptedException e) {
			e.getStackTrace();
		}		
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
