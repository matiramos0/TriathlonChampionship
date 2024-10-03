package Model.Race;

import Model.Athlete.Athlete;
import Model.ClimateCondition.ClimateCondition;
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
	private AthletePanel panel; //panel de carrera
	
	public AthleteRaceInformation(Athlete athlete, Modality modality, ClimateCondition climateCondition) {
		this.athlete = athlete;
		this.modality = modality;
		this.climateCondition = climateCondition;
		this.advancedDistance = 0;
		this.advancedTime = 0;
		this.fatigue = 0;
		this.isOut = false;
		
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
				panel.advance(/*this.panel,*/ advancedDistance);
				//getLblEnergy --> show energy 
				sleep(SpeedOfRace);
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

	public AthletePanel getPanel() {
		return panel;
	}

	public void setPanel(AthletePanel panel) {
		this.panel = panel;
	}

	public Modality getModality() {
		return modality;
	}

	public void setModality(Modality modality) {
		this.modality = modality;
	}		

}
