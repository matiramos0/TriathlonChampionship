package Model.Race;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import Controller.Championship;
import Model.Athlete.Athlete;
import Model.ClimateCondition.ClimateCondition;

import Model.Discipline.Cycling;
import Model.Discipline.Discipline;
import Model.Discipline.Pedestrianism;
import Model.Discipline.Provisioning;
import Model.Discipline.Swimming;
import Model.Modality.Modality;
import Model.View.AthletePanel;

public class AthleteRaceInformation extends Thread{
	
	public static final long speedOfRace = 100; // miliseconds
	public static final long timeOfTranscition = 1000;
	public static final long maxFatigue = 99;
	public static final float baseFatigueValue = 0.15F;
	
	private Athlete athlete;
	private Modality modality;
	private ClimateCondition climateCondition;
	private float advancedDistance;
	private float advancedTime;
	private float fatigue;
	private float velocity;
	private boolean isOut;
	private int position;
	private Map <Integer, Provisioning> provisioningCycling;
	private Map <Integer, Provisioning> provisioningPedestrianism;
	private boolean stopped;

	public AthleteRaceInformation(Athlete athlete, Modality modality, ClimateCondition climateCondition, 
			Map <Integer, Provisioning> provisioningCycling, Map <Integer, Provisioning> provisioningPedestrianism) {
		this.athlete = athlete;
		this.modality = modality;
		this.climateCondition = climateCondition;
		this.advancedDistance = 0;
		this.advancedTime = 0;
		this.fatigue = 0;
		this.isOut = false;
		this.provisioningCycling = provisioningCycling;
		this.provisioningPedestrianism = provisioningPedestrianism;
		this.stopped = false;
	}
	
	//Methods
	
	
	@Override
	public void run() {
		
		try {
			
			/*ArrayList<Provisioning> provisionings = new ArrayList<>();
			
			int nextProv = 1;
			
			while (provisioningCycling.containsKey(nextProv)) {
				Provisioning prov = provisioningCycling.get(nextProv);
				provisionings.add(prov);
			}
			
			while (provisioningPedestrianism.containsKey(nextProv)) {
				Provisioning prov = provisioningPedestrianism.get(nextProv);
				provisionings.add(prov);
			}*/
      
			while (advancedDistance < modality.getFirstTransition() && isOut != true) {
				if (fatigue > maxFatigue)
					isOut = true;
				
				//System.out.println(advancedDistance + "\t" + velocity + "\t" + fatigue + "\t" + athlete.getName());
				
				velocity = athlete.getVelocity(modality.getSwimming().getDiscipline());
				velocity -= getFatigueEffect();
				velocity -= getClimateConditionEffect(modality.getSwimming().getDiscipline());
				fatigue += baseFatigueValue - baseFatigueValue*(athlete.getPhysicalsConditions().getResistance()/100);
				advancedDistance += velocity;
				
				Championship.getInstance().listenAdvancePanel(this);// Atributo controller?
				
				if (stopped == true) {
					synchronized(this) {
						wait();
					}
				}else {
					synchronized(this) {
						notify();					
						}
				}
				
				sleep(speedOfRace);
			}
			
			sleep(timeOfTranscition);
			
			int nextProv = 1;
			
			Provisioning provisioning = provisioningCycling.get(nextProv);
			
			float pointProv = provisioning.getDistance();
			
			while (advancedDistance < modality.getSecondTransition() && isOut != true) {
				if (fatigue > maxFatigue)
					isOut = true;
				
				//System.out.println(advancedDistance + "\t" + velocity + "\t" + fatigue + "\t" + athlete.getName());
					
				velocity = athlete.getVelocity(modality.getCycling().getDiscipline());
				velocity -= getFatigueEffect();  
				velocity -= getClimateConditionEffect(modality.getCycling().getDiscipline());
				fatigue += baseFatigueValue - baseFatigueValue*(athlete.getPhysicalsConditions().getResistance()/100);
				advancedDistance += velocity;
				
				//System.out.println(advancedDistance);
				
				if (advancedDistance - modality.getFirstTransition() >= pointProv) {
					//System.out.println(nextProv);
					fatigue -= fatigue*0.1;
					nextProv++;
					
					if (nextProv <= provisioningCycling.size()) {
						provisioning = provisioningCycling.get(nextProv);
						pointProv = provisioning.getDistance();
					} else
						pointProv = modality.getSecondTransition();
				}

				Championship.getInstance().listenAdvancePanel(this);

				if (stopped == true) {
					synchronized(this) {
						wait();
					}
				}else {
					synchronized(this) {
						notify();					
						}
				}
				
				sleep(speedOfRace);
			}
			
			sleep(timeOfTranscition);
			
			nextProv = 1;
			
			provisioning = provisioningPedestrianism.get(nextProv);
			
			pointProv = provisioning.getDistance();
			
			while (advancedDistance < modality.getTotalDistance() && isOut != true) {
				if (fatigue > maxFatigue)
					isOut = true;
				
				//System.out.println(advancedDistance + "\t" + velocity + "\t" + fatigue + "\t" + athlete.getName());
				
				velocity = athlete.getVelocity(modality.getPedestrianism().getDiscipline());
				velocity -= getFatigueEffect();
				velocity -= getClimateConditionEffect(modality.getPedestrianism().getDiscipline());
				fatigue += baseFatigueValue - baseFatigueValue*(athlete.getPhysicalsConditions().getResistance()/100);
				advancedDistance += velocity;
				
				if (advancedDistance - modality.getSecondTransition() >= pointProv) {
					//System.out.println(nextProv);
					fatigue -= fatigue*0.15;
					nextProv++;
					if (nextProv <= provisioningPedestrianism.size()) {
						provisioning = provisioningPedestrianism.get(nextProv);
						pointProv = provisioning.getDistance();
					} else
						pointProv = modality.getTotalDistance();
				}
				
				Championship.getInstance().listenAdvancePanel(this);

				if (stopped == true) {
					synchronized(this) {
						wait();
					}
				}else {
					synchronized(this) {
						notify();					
						}
				}
				
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
	
	private float getClimateConditionEffect(Discipline discipline) {		
		float effect = 0;
		
		if (discipline instanceof Swimming)
			effect = (float) (velocity*(climateCondition.getSwimmingWear()/100));
		else if (discipline instanceof Cycling)
			effect = (float) (velocity*(climateCondition.getCyclingWear()/100));
		else if (discipline instanceof Pedestrianism)
			effect = (float) (velocity*(climateCondition.getRunningWear()/100));
		
		return effect;
	}

	public void info() {
		
		System.out.println("Atleta Nro: " + athlete.getNumber());
		System.out.println("Nombre: " + athlete.getName());
		System.out.println("DNI: " + athlete.getDni());
		System.out.println("Categoria: " + athlete.getCategory() + "\n");
	
	}
	
/*public void stopThread() throws InterruptedException {
	}*/
	
	//Getters and Setters

	public Athlete getAthlete() {
		return athlete;
	}

	public void setAthlete(Athlete athlete) {
		this.athlete = athlete;
	}
	
	public boolean isStopped() {
		return stopped;
	}

	public void setStopped(boolean stopped) {
		this.stopped = stopped;
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

	public boolean isOut() {
		return isOut;
	}		

	
}
