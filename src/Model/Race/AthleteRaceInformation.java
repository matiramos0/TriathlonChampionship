package Model.Race;

import Model.Athlete.Athlete;
import Model.ClimateCondition.ClimateCondition;
import Model.Modality.Modality;

public class AthleteRaceInformation extends Thread{
	
	public static final long SpeedOfRace = 100; // miliseconds
	
	private static final long timeOfTranscition = 1000;
	
	private Athlete athlete;
	private Modality modality;
	private ClimateCondition climateCondition;
	private float advancedDistance;
	private float advancedTime;
	private float energy;
	private float velocity;
	private boolean isOut = false;
	
	public AthleteRaceInformation(Athlete athlete, Modality modality, ClimateCondition climateCondition) {
		this.athlete = athlete;
		this.modality = modality;
		this.climateCondition = climateCondition;
		this.advancedDistance = 0;
		this.advancedTime = 0;
		this.energy = 100;
	}
	
	//Methods
	
	@Override
	public void run() {
		
		try {
			
			while (advancedDistance < modality.getFirstTransition() && isOut != true) {
				if (energy == 0)
					isOut = true;
				
				System.out.println(advancedDistance + "\t" + velocity);
				
				velocity = athlete.getVelocity(modality.getSwimming().getDiscipline());
				advancedDistance += velocity;
				
				sleep(SpeedOfRace);
			}
			
			sleep(timeOfTranscition);
			
			while (advancedDistance < modality.getSecondTransition() && isOut != true) {
				if (energy == 0)
					isOut = true;
				
				System.out.println(advancedDistance + "\t" + velocity);
				
				velocity = athlete.getVelocity(modality.getCycling().getDiscipline());
				advancedDistance += velocity;
				
				sleep(SpeedOfRace);
			}
			
			sleep(timeOfTranscition);
			
			while (advancedDistance < modality.getTotalDistance() && isOut != true) {
				
				System.out.println(advancedDistance + "\t" + velocity);
				
				velocity = athlete.getVelocity(modality.getPedestrianism().getDiscipline());
				advancedDistance += velocity;
				
				sleep(SpeedOfRace);
			}
			
			
		} catch (InterruptedException e) {
			System.out.println("Error hilo " + e);
		}
	}

	public void info() {
		
		System.out.println("Atleta Nro: " + athlete.getNumber());
		System.out.println("Nombre: " + athlete.getName());
		System.out.println("DNI: " + athlete.getDni());
		System.out.println("Categoria: " + athlete.getCategory() + "\n");
		
	}
	
	public float getEnergyWear() {
		return 0;
	}
	
	//Getters and Setters

	public Athlete getAthlete() {
		return athlete;
	}

	public void setAthlete(Athlete athlete) {
		this.athlete = athlete;
	}

	public float getAdvancedDistance() {
		return advancedDistance;
	}

	public void setAdvancedDistance(float advancedDistance) {
		this.advancedDistance = advancedDistance;
	}

	public float getAdvancedTime() {
		return advancedTime;
	}

	public void setAdvancedTime(float advancedTime) {
		this.advancedTime = advancedTime;
	}

	public float getEnergy() {
		return energy;
	}

	public void setEnergy(float energy) {
		this.energy = energy;
	}		

}
