package Model.Race;

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
	
	public static final long timeOfTranscition = 1000; //miliseconds
	public static final long maxFatigue = 99;		   //porcentage
	public static final float restoreFatigue = 0.2F;   //porcentage
	public static final float baseFatigueValue = 0.12F;//porcentage
	
	private Athlete athlete;
	private Race race;
	private float advancedDistance;
	private float advancedTime;
	private float fatigue;
	private float velocity;
	private int position;
	private boolean isOut;
	//private boolean stopped; // Usar el stopped de race?
	
	//Methods
	
	
	public AthleteRaceInformation(Athlete athlete, Race race) {
		this.athlete = athlete;
		this.race = race;
		this.advancedDistance = 0;
		this.advancedTime = 0;
		this.fatigue = 0;
		this.isOut = false;
		//this.stopped = false;
	}

	@Override
	public void run() {
		
		try {
      
			while (advancedDistance < race.getModality().getFirstTransition() && isOut != true) {
				if (fatigue > maxFatigue) 
					isOut = true;		
				
				System.out.println(advancedDistance + "\t" + velocity + "\t" + fatigue + "\t" + athlete.getName());
				
				velocity = athlete.getVelocity(race.getModality().getSwimming().getDiscipline());
				velocity -= getFatigueEffect();
				velocity -= getClimateConditionEffect(race.getModality().getSwimming().getDiscipline());
				fatigue += baseFatigueValue - baseFatigueValue*(athlete.getPhysicalsConditions().getResistance()/100);
				advancedDistance += velocity;
				
				Championship.getInstance().listenAdvancePanel(this); // Atributo controller?
				
				try {
					
					if (race.isStopped()) {
						synchronized (this) {
							while(race.isStopped())
								sleep(0);
						}
					}
					
				} catch (InterruptedException i) {
					i.printStackTrace();
				}
				
				sleep(Race.speedOfRace);
			}
			
			sleep(timeOfTranscition);
			
			int nextProv = 1;
			
			Provisioning provisioning = race.getProvisioningCycling().get(nextProv);
			
			float pointProv = provisioning.getDistance();
			
			while (advancedDistance < race.getModality().getSecondTransition() && isOut != true) {
				if (fatigue > maxFatigue)
					isOut = true;
									
				velocity = athlete.getVelocity(race.getModality().getCycling().getDiscipline());
				velocity -= getFatigueEffect();  
				velocity -= getClimateConditionEffect(race.getModality().getCycling().getDiscipline());
				fatigue += baseFatigueValue - baseFatigueValue*(athlete.getPhysicalsConditions().getResistance()/100);
				advancedDistance += velocity;
								
				if (advancedDistance - race.getModality().getFirstTransition() >= pointProv) {
					fatigue -= fatigue*restoreFatigue;
					nextProv++;
					
					if (nextProv <= race.getProvisioningCycling().size()) {
						provisioning = race.getProvisioningCycling().get(nextProv);
						pointProv = provisioning.getDistance();
					} else
						pointProv = race.getModality().getSecondTransition();
				}

				Championship.getInstance().listenAdvancePanel(this);
				
				try {
					
					if (race.isStopped()) {
						synchronized (this) {
							while(race.isStopped())
								sleep(0);
						}
					}
					
				} catch (InterruptedException i) {
					i.printStackTrace();
				}
				
				sleep(Race.speedOfRace);
			}
			
			sleep(timeOfTranscition);
			
			nextProv = 1;
			
			provisioning = race.getProvisioningPedestrianism().get(nextProv);
			
			pointProv = provisioning.getDistance();
			
			while (advancedDistance < race.getModality().getTotalDistance() && isOut != true) {
				if (fatigue > maxFatigue)
					isOut = true;
								
				velocity = athlete.getVelocity(race.getModality().getPedestrianism().getDiscipline());
				velocity -= getFatigueEffect();
				velocity -= getClimateConditionEffect(race.getModality().getPedestrianism().getDiscipline());
				fatigue += baseFatigueValue - baseFatigueValue*(athlete.getPhysicalsConditions().getResistance()/100);
				advancedDistance += velocity;
				
				if (advancedDistance - race.getModality().getSecondTransition() >= pointProv) {
					fatigue -= fatigue*restoreFatigue;
					nextProv++;
					if (nextProv <= race.getProvisioningPedestrianism().size()) {
						provisioning = race.getProvisioningPedestrianism().get(nextProv);
						pointProv = provisioning.getDistance();
					} else
						pointProv = race.getModality().getTotalDistance();
				}
				
				Championship.getInstance().listenAdvancePanel(this);
				
				try {
					
					if (race.isStopped()) {
						synchronized (this) {
							while(race.isStopped())
								sleep(0);
						}
					}
					
				} catch (InterruptedException i) {
					i.printStackTrace();
				}
				
				sleep(Race.speedOfRace);
			}

			race.countFinishAthlete();
			
		} catch (InterruptedException e) {
			e.printStackTrace(System.err);
		}
	}
	
	private float getFatigueEffect() {
		float effect = velocity*(fatigue/100);
		return effect;
	}
	
	private float getClimateConditionEffect(Discipline discipline) {		
		float effect = 0;
		
		if (discipline instanceof Swimming)
			effect = (float) (velocity*(race.getCurrentWeather().getSwimmingWear()/100));
		else if (discipline instanceof Cycling)
			effect = (float) (velocity*(race.getCurrentWeather().getCyclingWear()/100));
		else if (discipline instanceof Pedestrianism)
			effect = (float) (velocity*(race.getCurrentWeather().getRunningWear()/100));
		
		return effect;
	}

	public void info() {
		
		System.out.println("Atleta Nro: " + athlete.getNumber());
		System.out.println("Nombre: " + athlete.getName());
		System.out.println("DNI: " + athlete.getDni());
		System.out.println("Categoria: " + athlete.getCategory() + "\n");
	
	}
	
	/*public void stopThread() throws InterruptedException {
		synchronized (this) {
			currentThread().wait();
		}
	}*/
	
	//Getters and Setters

	public Athlete getAthlete() {
		return athlete;
	}

	public void setAthlete(Athlete athlete) {
		this.athlete = athlete;
	}
	
	public Race getRace() {
		return race;
	}
	
	/*public boolean isStopped() {
		return stopped;
	}

	public void setStopped(boolean stopped) {
		this.stopped = stopped;
	}
*/
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
