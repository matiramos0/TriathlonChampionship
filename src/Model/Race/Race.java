package Model.Race;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import Model.City.City;
import Model.Modality.Modality;
import Model.ClimateCondition.ClimateCondition;
import Model.Discipline.Provisioning;
import Model.Athlete.Athlete;

public class Race {

	private Modality modality;
	private City city;
	private Map <Integer, Provisioning> listPrivisioning;
	private List <ClimateCondition> listClimateCondition;
	private List <AthleteRaceInformation> listAthletes;
	
	//Constructor Method
	
	public Race(Modality modality, City city, Map <Integer, Provisioning> list) {
		this.modality = modality;
		this.city = city;
		this.listPrivisioning = list;
	}
	
	//Methods
	
	public void prepareRace(List<Athlete> athletes) {
		
		listAthletes = new ArrayList<AthleteRaceInformation>();
		
		ClimateCondition climateCondition = new ClimateCondition();
		
		for (Athlete athlete: athletes) {
			AthleteRaceInformation athleteRace = new AthleteRaceInformation(athlete, modality, climateCondition);
			listAthletes.add(athleteRace);
			
		}
	}
	
	public void startRace() {
		
		Random random = new Random();
		
		Thread athlete1 = listAthletes.get(0);
		//Thread athlete2 = listAthletes.get(1);
		
		athlete1.start();
		//athlete2.start();
		
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
