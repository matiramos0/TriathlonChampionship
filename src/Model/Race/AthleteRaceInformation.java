package Model.Race;

import java.util.Map;

import Controller.Championship;
import Model.Athlete.Athlete;
import Model.ClimateCondition.ClimateCondition;
import Model.Discipline.Provisioning;
import Model.Modality.Modality;
import Model.View.AthletePanel;

public class AthleteRaceInformation extends Thread{
	
	private static final long speedOfRace = 100; // miliseconds
	private static final long timeOfTranscition = 1000;
	private static final long maxFatigue = 99;
	private static final float baseFatigueValue = 0.2F;
	
	private Athlete athlete;
	private Modality modality;
	private ClimateCondition climateCondition;
	private float advancedDistance;
	private float advancedTime;
	private float fatigue;
	private float velocity;
	private boolean isOut;
	private int position;
	private Map <Integer, Provisioning> listProvisioning;

	public AthleteRaceInformation(Athlete athlete, Modality modality, ClimateCondition climateCondition, Map <Integer, Provisioning> provisioningCycling, Map <Integer, Provisioning> provisioningPedestrianism){
		this.athlete = athlete;
		this.modality = modality;
		this.climateCondition = climateCondition;
		this.advancedDistance = 0;
		this.advancedTime = 0;
		this.fatigue = 0;
		this.isOut = false;
		this.listProvisioning = listProvisioning;
	}
	
	//Methods
	
	
	@Override
	public void run() {
		
		try {
      
			while (advancedDistance < modality.getFirstTransition() && isOut != true) {
				if (fatigue > maxFatigue)
					isOut = true;
				
				System.out.println(advancedDistance + "\t" + velocity + "\t" + fatigue + "\t" + athlete.getName());
				
				velocity = athlete.getVelocity(modality.getSwimming().getDiscipline());
				velocity -= getFatigueEffect();  
				fatigue += baseFatigueValue - baseFatigueValue*(athlete.getPhysicalsConditions().getResistance()/100);
				advancedDistance += velocity;
				
				Championship.getInstance().listenAdvancePanel(this);// Atributo controller?
				
				sleep(speedOfRace);
			}
			
			sleep(timeOfTranscition);
			
			while (advancedDistance < modality.getSecondTransition() && isOut != true) {
				if (fatigue > maxFatigue)
					isOut = true;
				
				System.out.println(advancedDistance + "\t" + velocity + "\t" + fatigue + "\t" + athlete.getName());
				
				velocity = athlete.getVelocity(modality.getCycling().getDiscipline());
				velocity -= getFatigueEffect();  
				fatigue += baseFatigueValue - baseFatigueValue*(athlete.getPhysicalsConditions().getResistance()/100);
				advancedDistance += velocity;

				Championship.getInstance().listenAdvancePanel(this);

				sleep(speedOfRace);
			}
			
			sleep(timeOfTranscition);
			
			while (advancedDistance < modality.getTotalDistance() && isOut != true) {
				if (fatigue > maxFatigue)
					isOut = true;
				
				System.out.println(advancedDistance + "\t" + velocity + "\t" + fatigue + "\t" + athlete.getName());
				
				velocity = athlete.getVelocity(modality.getPedestrianism().getDiscipline());
				velocity -= getFatigueEffect();  
				fatigue += baseFatigueValue - baseFatigueValue*(athlete.getPhysicalsConditions().getResistance()/100);
				advancedDistance += velocity;
				
				Championship.getInstance().listenAdvancePanel(this);

				sleep(speedOfRace);
			}
			
			
		} catch (InterruptedException e) {
			System.out.println("Error hilo " + e);
		}
	}
	
	private float getFatigueEffect() {
		float effect = velocity*(fatigue/100);
		return effect;
	}

	public void info() {
		
		System.out.println("Atleta Nro: " + athlete.getNumber());
		System.out.println("Nombre: " + athlete.getName());
		System.out.println("DNI: " + athlete.getDni());
		System.out.println("Categoria: " + athlete.getCategory() + "\n");
		
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

	public float getFatigue() {
		return fatigue;
	}

	public void setFatigue(float fatigue) {
		this.fatigue = fatigue;
	}


	public Modality getModality() {
		return modality;
	}

	public void setModality(Modality modality) {
		this.modality = modality;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}		

	
}
