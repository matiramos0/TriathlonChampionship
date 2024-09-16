package Model.Race;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import DAO.WeatherConditionsDAO;
import Model.City.City;
import Model.Modality.Modality;
import view.RaceView;
import Model.ClimateCondition.ClimateCondition;
import Model.Discipline.Provisioning;
import Model.Athlete.Athlete;

public class Race {

	private Modality modality;
	private City city;
	private Map <Integer, Provisioning> listPrivisioning;
	private ClimateCondition currentWeather;
	private List <AthleteRaceInformation> listAthletes;
	
	//Constructor Method
	
	public Race(Modality modality, City city, Map <Integer, Provisioning> list) throws SQLException{
		this.modality = modality;
		this.city = city;
		this.listPrivisioning = list;
		
		  List<ClimateCondition> weatherConditions = WeatherConditionsDAO.getAllWeatherConditions();
	        this.currentWeather = ClimateCondition.getRandomWeatherCondition(weatherConditions);  // Obtener clima aleatorio
	 }
	
	
	//Methods
	
	public void prepareRace(List<Athlete> athletes) {
		
		listAthletes = new ArrayList<AthleteRaceInformation>();
		
	//	ClimateCondition climateCondition = new ClimateCondition(); 
		
		for (int i = 0; i < 7; i++) {
      Athlete athlete = athletes.get(i);
			AthleteRaceInformation athleteRace = new AthleteRaceInformation(athlete, modality, currentWeather);
			listAthletes.add(athleteRace);
			
		}
	}
	
	public void startRace() {
		
    for (AthleteRaceInformation athlete: listAthletes) {
      athlete.start();
    }
		
		Timer timer = new Timer();
		
		TimerTask task = new TimerTask() {
			
			int time = 0;
			
			@Override
			public void run() {
				System.out.println("Tiempo: " + time);
				time++;
				
				if (time == 50)
					timer.cancel();
			}
		};
		
		timer.schedule(task, 0, 1000);
	}

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

	/*public ArrayList<Provisioning> getListprivisioning() {
		return (ArrayList<Provisioning>) listPrivisioning;
	}

	public void setListprivisioning(ArrayList<Provisioning> listprivisioning) {
		this.listPrivisioning = listprivisioning;
	}*/
	
	public ArrayList<AthleteRaceInformation> getListAthletes() {
		return (ArrayList<AthleteRaceInformation>) listAthletes;
	}

	public void setListAthletes(List<AthleteRaceInformation> listathletes) {
		this.listAthletes = listathletes;
	}
	
	
	
}
