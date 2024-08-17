package Model.Race;

import java.util.ArrayList;
import java.util.List;
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
	//private List <Provisioning> listPrivisioning = new ArrayList<>();
	//private List <ClimateCondition> listClimateCondition = new ArrayList<>();
	private List <AthleteRaceInformation> listAthletes;
	
	//Constructor Method
	
	public Race(Modality modality, City city) {
		this.modality = modality;
		this.city = city;
	}
	
	//Methods
	
	public void prepareRace(List<Athlete> athletes) {
		
		listAthletes = new ArrayList<AthleteRaceInformation>();
		
		for (Athlete athlete: athletes) {
			AthleteRaceInformation athleteRace = new AthleteRaceInformation(athlete);
			listAthletes.add(athleteRace);
			
		}
		
	}
	
	public void startRace() {
		
		Random random = new Random();
		
		AthleteRaceInformation athlete = listAthletes.get(random.nextInt(listAthletes.size()));
		
		System.out.println("Info Atleta" + "\n");
		System.out.println("Numero: " + athlete.getAthlete().getNumber());
		System.out.println("Nombre: " + athlete.getAthlete().getName());
		System.out.println("DNI: " + athlete.getAthlete().getDni());
		System.out.println("Categoria: " + athlete.getAthlete().getCategory());

		
		Timer timer = new Timer();
		
		TimerTask task = new TimerTask() {
			
			int i = 0;

			@Override
			public void run() {
				
				System.out.println("Distancia del atleta: " + athlete.getAdvancedDistance());
				System.out.println("Tiempo: " + i);
				i++;
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
