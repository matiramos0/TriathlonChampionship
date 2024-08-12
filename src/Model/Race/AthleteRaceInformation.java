package Model.Race;

import Model.Athlete.Athlete;

public class AthleteRaceInformation {
	
	private Athlete athlete;
	private float advancedDistance;
	private float advancedTime;
	private float energy;
	
	public AthleteRaceInformation(Athlete athlete) {
		this.athlete = athlete;
		this.advancedDistance = 0;
		this.advancedTime = 0;
		this.energy = 100;
	}
	
	//Methods
	
	public void advanceRace() {
		 
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
