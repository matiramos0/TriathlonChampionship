package Model.Athlete;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Model.Discipline.Cycling;
import Model.Discipline.Discipline;
import Model.Discipline.Pedestrianism;
import Model.Discipline.Swimming;
import Model.Race.Race;

public abstract class Athlete implements Serializable{
	
    private static final long serialVersionUID = 1L;

	public enum Gender {MALE, FEMALE};

    protected String number;
    protected String name;
    protected String last;
    protected String nacionality;
    protected String category;
    protected Gender gender;
    protected int dni;
    protected int porcentageRacesCompleted;
    protected float weight;
    protected float height;
    protected double economy;
    protected String birthdate;
    protected Stats physicalsConditions;
	private List<Competence>championshipInformation;
	private int championshipPoints;
	private int championshipPosition;
	private int numberRaceOut;

    public Athlete(String number, String name, String last, String nacionality, int dni, int porcentageRacesCompleted,float weight, float height, double economy,String birthdate, Stats physicalsConditions) {
        this.number = number;
        this.name = name;
        this.last = last;
        this.nacionality = nacionality;
        this.dni = dni;
        this.porcentageRacesCompleted = porcentageRacesCompleted;
        this.weight = weight;
        this.height = height;
        this.economy = economy;
        this.birthdate = birthdate;
        this.physicalsConditions = physicalsConditions;
		this.championshipInformation = new ArrayList<>();
		this.championshipPoints = 0;
		this.numberRaceOut = 0;
    }
    
    public void newRace(Race race) {
    	championshipInformation.add(new Competence(race));
    }
    
    //Getters and Setters 
    
    public String getNumber() {
		return number;
	}

	public List<Competence> getChampionshipInformation() {
		return championshipInformation;
	}

	public void setChampionshipInformation(List<Competence> championshipInformation) {
		this.championshipInformation = championshipInformation;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}
	
	public Stats getPhysicalsConditions() {
		return physicalsConditions;
	}

	public void setPhysicalsConditions(Stats physicalsConditions) {
		this.physicalsConditions = physicalsConditions;
	}
	
	public String getNacionality() {
		return nacionality;
	}

	public void setNacionality(String nacionality) {
		this.nacionality = nacionality;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public double getEconomy() {
		return economy;
	}

	public void setEconomy(double economy) {
		this.economy = economy;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public int getNumberRaceOut() {
		return numberRaceOut;
	}

	public void setNumberRaceOut(int numberRaceOut) {
		this.numberRaceOut = numberRaceOut;
	}

	public void setChampionshipPoints(int championshipPoints) {
		this.championshipPoints = championshipPoints;
	}

	public int getChampionshipPosition() {
		return championshipPosition;
	}

	public void setChampionshipPosition(int championshipPosition) {
		this.championshipPosition = championshipPosition;
	}
	
	//Abstract Methods

	protected abstract String calculateCategory(int years);
	
	//Methods
	
	public void decreasesRaceOut() {
		this.numberRaceOut--;
	}
	
	public int calculateAge(String birthdate) {
		
		String[] birth = birthdate.split("-");
    	LocalDate current = LocalDate.now();
    	
    	int years = current.getYear() - Integer.parseInt(birth[0]);
    	
    	if ((current.getMonthValue() < Integer.parseInt(birth[1])))
    		years--;
    	
    	if (current.getDayOfMonth() < Integer.parseInt(birth[2]))
    		years--;
    	
    	return years;
	}
	
	// Receives the base fatigue decrease in each discipline
	// and returns the fatigue increase in the race
	
	public float increasesFatigue(Discipline discipline) {
		float effect = discipline.getEffectFatigue() - discipline.getEffectFatigue()*(physicalsConditions.getResistance()/100);
		return effect;
		
	}
	
	// In this methods getVelocity(), 
	// returns the speed based on the discipline and applies the statistical factor in each one as a percentage
	
	public float getVelocity(Swimming swimming) {
		
		float velocity = 0;
		
		velocity = swimming.getVelocityInDiscipline();
		velocity += velocity*(physicalsConditions.getSwimming()/100); 
		
		effectGenderFactor(velocity);
		effectAgeFactor(velocity);
		
		return velocity;
	}
	
	public float getVelocity(Cycling cycling) {
		
		float velocity = 0;
		
		velocity = cycling.getVelocityInDiscipline();
		velocity += velocity*(physicalsConditions.getCycling()/100);
		
		effectGenderFactor(velocity);
		effectAgeFactor(velocity);
		
		return velocity;
	}
	
	public float getVelocity(Pedestrianism running) {
		
		float velocity = 0;
		
		velocity = running.getVelocityInDiscipline();
		velocity += velocity*(physicalsConditions.getStoning()/100);
		
		effectGenderFactor(velocity);
		effectAgeFactor(velocity);

		return velocity;
	}
	
	private float effectGenderFactor(float velocity) { // gender factor applies
		
		if (gender.equals(Gender.FEMALE))
			velocity -= velocity*0.05;
		
		return velocity;
	}
	
	private float effectAgeFactor(float velocity) { // age factor applies
		
		return velocity -= velocity*(calculateAge(birthdate)*0.01F - 0.2F);
	}

	public int getChampionshipPoints() {
		int points = 0;
		
		for(int i = 0; i < championshipInformation.size(); i++) {
			if(championshipInformation.get(i).getRace().isFinished() && championshipInformation.get(i).isAbandon() == false)
					points += championshipInformation.get(i).getRace().getListAthletes().size() - championshipInformation.get(i).getPosition() + 1;
		}
		return points;
	}

	public int getRacesWon() {
		int wins = 0;

		for (Competence c : championshipInformation) {
			if (c.getRace().isFinished())
				if (c.getPosition() == 1)
					wins++;
		}

		return wins;
	}

	public int getRacesAbandoned() {
		int abandons = 0;

		for (Competence c : championshipInformation) {
			if (c.getRace().isFinished())
				if (c.isAbandon())
					abandons++;
		}

		return abandons;
	}

	public int getRacesFinished() {
		int finished = 0;

		for (Competence c : championshipInformation) {
			if (c.getRace().isFinished())
				if (c.getDistances().getLast().getDistance() >= c.getRace().getModality().getTotalDistance())
					finished++;
		}

		return finished;
	}

	public int getSwimmingStagesWon() {
		int wins = 0;

		for (Competence c : championshipInformation) {
			if (c.getRace().isFinished())
				if (c.getDistances().get(0).getDisciplinePosition() == 1)
					wins++;
		}

		return wins;
	}
	
	public int getCyclingStagesWon() {
		int wins = 0;

		for (Competence c : championshipInformation) {
			try{
				if (c.getRace().isFinished())
					if (c.getDistances().get(1).getDisciplinePosition() == 1)
						wins++;
			} catch(IndexOutOfBoundsException e) {
				
			}
		}

		return wins;
	}
	
	public int getPedestrianismStagesWon() {
		int wins = 0;

		for (Competence c : championshipInformation) {
			try {				
				if (c.getRace().isFinished())
					if (c.getDistances().get(2).getDisciplinePosition() == 1)
						wins++;
			} catch(IndexOutOfBoundsException e) {
				
			}
		}

		return wins;
	}
	
}
