package Model.Race;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

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
	private Map <Integer, Provisioning> provisioningPedestrianism;
	private Map <Integer, Provisioning> provisioningCycling;
	private List <AthleteRaceInformation> listAthletes;
	private int finishedAthletesCount;
	private float time;
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
	 }
	
	
	//Methods
	
	public void prepareRace(List<Athlete> athletes) {
		
		listAthletes = new ArrayList<AthleteRaceInformation>();
		
		for (Athlete athlete : athletes) {
			AthleteRaceInformation athleteRace = new AthleteRaceInformation(athlete, this);
			athlete.newRace(this);
			listAthletes.add(athleteRace);
			
		}
	}
	
	@Override
	public void run() {
		
		try {
		    
			finishedAthletesCount = 0;
			time = 0;
			
			for (AthleteRaceInformation athlete: listAthletes)
				athlete.start();
						
			while(finishedAthletesCount < listAthletes.size()) {
			
				try {
					Random random = new Random();
					if (random.nextInt(250) == 1) {
						List<ClimateCondition> weatherConditions = WeatherConditionsDAO.getAllWeatherConditions();
						currentWeather = ClimateCondition.getRandomWeatherCondition(weatherConditions);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			    
				Championship.getInstance().listenRefreshView(time, currentWeather); // O Atributo controller?
				
				try {
					 Championship.getInstance().listenRefreshRacePositions();	
				} catch(IllegalArgumentException e) {
					 Championship.getInstance().listenInterruptRace(true);	
					 JOptionPane.showMessageDialog(null, "There was a small problem updating the positions, press 'OK' to continue the race.");
					 e.printStackTrace();
				}
			
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
	
	//Methods
	
	public void interruptRace(boolean interruption) throws InterruptedException {
		this.stopped = interruption;
		//for (AthleteRaceInformation athlete: listAthletes)
			//athlete.setStopped(interruption);
	}
	
	public synchronized void countFinishAthlete() {
		this.finishedAthletesCount++ ;
	}


	public void cancelRace() {
		synchronized(this) {
			listAthletes.forEach(a -> {	synchronized(a) {
									   a.setIsOut(true);}
								});
			this.interrupt();
		 }		
	}
	
	//Getters and Setters
	public Modality getModality() {
		return modality;
	}

	public float getTime() {
		return time;
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

	@Override
	public int hashCode() {
		return Objects.hash(city, currentWeather, finishedAthletesCount, listAthletes, modality, provisioningCycling,
				provisioningPedestrianism, stopped, time);
	}

	
	
	@Override
	public String toString() {
		return city.getDescription() ;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Race other = (Race) obj;
		return Objects.equals(city, other.city) && Objects.equals(currentWeather, other.currentWeather)
				&& finishedAthletesCount == other.finishedAthletesCount
				&& Objects.equals(listAthletes, other.listAthletes) && Objects.equals(modality, other.modality)
				&& Objects.equals(provisioningCycling, other.provisioningCycling)
				&& Objects.equals(provisioningPedestrianism, other.provisioningPedestrianism)
				&& stopped == other.stopped && Float.floatToIntBits(time) == Float.floatToIntBits(other.time);
	}
	    
	public boolean isFinished() {
	    return finishedAthletesCount == listAthletes.size();  
	}


  
}
