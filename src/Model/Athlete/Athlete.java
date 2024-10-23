package Model.Athlete;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Model.Discipline.Cycling;
import Model.Discipline.Discipline;
import Model.Discipline.Pedestrianism;
import Model.Discipline.Swimming;
import Model.Race.Race;

public abstract class Athlete {
	
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
	private List<Competencia>championshipInformation;

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
    }
    
    public void newRace(Race race) {
    	championshipInformation.add(new Competencia(race));
    }
    
    //Getters and Setters 
    
    public String getNumber() {
		return number;
	}

	public List<Competencia> getChampionshipInformation() {
		return championshipInformation;
	}

	public void setChampionshipInformation(List<Competencia> championshipInformation) {
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
	
	//Abstract Methods

	protected abstract String calculateCategory(int years);
	
	//Methods
	
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

	public float getVelocity(Discipline discipline) {
		
		float velocity = 0;
		
		if (discipline.getClass().equals(Swimming.class))
			velocity = physicalsConditions.getVelocitySwimming();
		else if (discipline.getClass().equals(Cycling.class))
			velocity = physicalsConditions.getVelocityCycling();
		else if (discipline.getClass().equals(Pedestrianism.class))
			velocity = physicalsConditions.getVelocityStoning();
		
		if (gender.equals(gender.FEMALE))
			velocity -= velocity*0.05;
		
		velocity -= velocity*(calculateAge(birthdate)*0.01F - 0.2F);
		
		return velocity;
	}
	
}
