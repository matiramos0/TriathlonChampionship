package Model.Race;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import Controller.Championship;
import Model.Athlete.Athlete;
import Model.Athlete.Competence;
import Model.Discipline.Cycling;
import Model.Discipline.Discipline;
import Model.Discipline.Pedestrianism;
import Model.Discipline.Provisioning;
import Model.Discipline.Swimming;

public class AthleteRaceInformation extends Thread implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final long timeOfTranscition = 1000; // miliseconds
	public static final long maxFatigue = 99; //porcentage
	public static final float restoreFatigue = 0.4F; //porcentage
	
	private Athlete athlete;
	private Race race;
	private float advancedDistance;
	private float fatigue;
	private float velocity;
	private int position;
	private boolean isOut;
	
	//Methods
	
	
	public AthleteRaceInformation(Athlete athlete, Race race) {
		this.athlete = athlete;
		this.race = race;
		this.advancedDistance = 0;
		this.fatigue = 0;
		this.isOut = false;
	}

	@Override
	public void run() {
		
		try {
      
			while (advancedDistance < race.getModality().getFirstTransition() && isOut != true) {
				if (fatigue > maxFatigue) { 
					isOut = true;
					athlete.getChampionshipInformation().getLast().setAbandon(true);
				}
				
				Discipline currentDiscipline = race.getModality().getSwimming().getDiscipline();
				int levelVelocity = Championship.getInstance().listenChangeVelocity(this);
				
				velocity = (levelVelocity/50) + athlete.getVelocity((Swimming) currentDiscipline);
				velocity -= getFatigueEffect();
				velocity -= getClimateConditionEffect(currentDiscipline);
				fatigue += (levelVelocity/50) + athlete.increasesFatigue(currentDiscipline);
				advancedDistance += velocity;

				//Registrar avances en los objetos Competence y DistanceDiscipline correspondientes a la actual carrera del athlete

				athlete.getChampionshipInformation().getLast().advance(advancedDistance);
				athlete.getChampionshipInformation().getLast().setPosition(position);
				
				Accident injury = Accident.generateInjury(this, fatigue);
	            if (injury != null) {
	                System.out.println("Lesión: " + injury.getDescription());
	                
	                if (injury.getPenaltyTime() == -1) {
	                    isOut = true;
	                    athlete.getChampionshipInformation().getLast().setAbandon(isOut);
	                    athlete.setNumberRaceOut(injury.getNumberRaceOut());
	                    System.out.println(athlete.getName() + " ha sido eliminado por " + injury.getDescription());
	                } else {
	                    sleep(injury.getPenaltyTime());
	                    System.out.println("Penalización de tiempo: " + injury.getPenaltyTime() + " segundos");
	                }
	            }
	            
	            Accident randomAccident = Accident.generateRandomAccident(currentDiscipline);
	            if (randomAccident != null) {
	            	sleep(randomAccident.getPenaltyTime());
	                System.out.println("Accidente aleatorio en " + currentDiscipline.getDescription() + ": " + randomAccident.getDescription());
	                System.out.println("Penalización de tiempo: " + randomAccident.getPenaltyTime() + " segundos");
	                
	                // Restar el tiempo de penalización al avance total del atleta
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
			
			int nextProv = 1;
			
			Provisioning provisioning = race.getProvisioningCycling().get(nextProv);
			
			float pointProv = provisioning.getDistance();
			
			while (advancedDistance < race.getModality().getSecondTransition() && isOut != true) {
				
				if (fatigue > maxFatigue) { 
					isOut = true;
					athlete.getChampionshipInformation().getLast().setAbandon(true);
				}
				
				Discipline currentDiscipline = race.getModality().getCycling().getDiscipline();
				int levelVelocity = Championship.getInstance().listenChangeVelocity(this);

				velocity = (levelVelocity/50) + athlete.getVelocity((Cycling) currentDiscipline);
				velocity -= getFatigueEffect();  
				velocity -= getClimateConditionEffect(currentDiscipline);
				fatigue += (levelVelocity/50) + athlete.increasesFatigue(currentDiscipline);
				advancedDistance += velocity;
				
				//Registrar avances en los objetos Competence y DistanceDiscipline correspondientes a la actual carrera del athlete
				athlete.getChampionshipInformation().getLast().advance(advancedDistance);
				athlete.getChampionshipInformation().getLast().setPosition(position);

				if (advancedDistance - race.getModality().getFirstTransition() >= pointProv) {
					fatigue -= fatigue*restoreFatigue;
					nextProv++;
					
					if (nextProv <= race.getProvisioningCycling().size()) {
						provisioning = race.getProvisioningCycling().get(nextProv);
						pointProv = provisioning.getDistance();
					} else
						pointProv = race.getModality().getSecondTransition();
				}

				
				Accident injury = Accident.generateInjury(this, fatigue);
	            if (injury != null) {
	                System.out.println("Lesión: " + injury.getDescription());
	                
	                if (injury.getPenaltyTime() == -1) {
	                    isOut = true;
	                    athlete.getChampionshipInformation().getLast().setAbandon(isOut);
	                    athlete.setNumberRaceOut(injury.getNumberRaceOut());
	                    System.out.println(athlete.getName() + " ha sido eliminado por " + injury.getDescription());
	                } else {
	                    sleep(injury.getPenaltyTime());
	                    System.out.println("Penalización de tiempo: " + injury.getPenaltyTime() + " segundos");
	                }
	            }
	            
	            Accident randomAccident = Accident.generateRandomAccident(currentDiscipline);
	            if (randomAccident != null) {
	            	sleep(randomAccident.getPenaltyTime());
	                System.out.println("Accidente aleatorio en " + currentDiscipline.getDescription() + ": " + randomAccident.getDescription());
	                System.out.println("Penalización de tiempo: " + randomAccident.getPenaltyTime() + " segundos");
	                
	                // Restar el tiempo de penalización al avance total del atleta
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

				if (fatigue > maxFatigue) { 
					isOut = true;
					athlete.getChampionshipInformation().getLast().setAbandon(true);
				}

				Discipline currentDiscipline = race.getModality().getPedestrianism().getDiscipline();
				int levelVelocity = Championship.getInstance().listenChangeVelocity(this);
								
				velocity = (levelVelocity/50) + athlete.getVelocity( (Pedestrianism) currentDiscipline);
				velocity -= getFatigueEffect();
				velocity -= getClimateConditionEffect(currentDiscipline);
				fatigue += (levelVelocity/50) + athlete.increasesFatigue(currentDiscipline);
				advancedDistance += velocity;
				
				//Registrar avances en los objetos Competence y DistanceDiscipline correspondientes a la actual carrera del athlete
				
				athlete.getChampionshipInformation().getLast().advance(advancedDistance);
				athlete.getChampionshipInformation().getLast().setPosition(position);

				if (advancedDistance - race.getModality().getSecondTransition() >= pointProv) {
					fatigue -= fatigue*restoreFatigue;
					nextProv++;
					if (nextProv <= race.getProvisioningPedestrianism().size()) {
						provisioning = race.getProvisioningPedestrianism().get(nextProv);
						pointProv = provisioning.getDistance();
					} else
						pointProv = race.getModality().getTotalDistance();
				}
				
				
				Accident injury = Accident.generateInjury(this, fatigue);
	            if (injury != null) {
	                System.out.println("Lesión: " + injury.getDescription());
	                
	                if (injury.getPenaltyTime() == -1) {
	                    isOut = true;
	                    athlete.getChampionshipInformation().getLast().setAbandon(isOut);
	                    athlete.setNumberRaceOut(injury.getNumberRaceOut());
	                    System.out.println(athlete.getName() + " ha sido eliminado por " + injury.getDescription());
	                } else {
	                    sleep(injury.getPenaltyTime());
	                    System.out.println("Penalización de tiempo: " + injury.getPenaltyTime() + " segundos");
	                }
	            }
	            
	            Accident randomAccident = Accident.generateRandomAccident(currentDiscipline);
	            if (randomAccident != null) {
	            	sleep(randomAccident.getPenaltyTime());
	                System.out.println("Accidente aleatorio en " + currentDiscipline.getDescription() + ": " + randomAccident.getDescription());
	                System.out.println("Penalización de tiempo: " + randomAccident.getPenaltyTime() + " segundos");
	                
	                // Restar el tiempo de penalización al avance total del atleta
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
	
	public float getAdvancedDistance() {
		return advancedDistance;
	}

	public void setAdvancedDistance(float advancedDistance) {
		this.advancedDistance = advancedDistance;
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

	public float getVelocity() {
		return velocity;
	}

	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public void setOut(boolean isOut) {
		this.isOut = isOut;
	}
	
}
