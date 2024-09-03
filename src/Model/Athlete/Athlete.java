package Model.Athlete;

import java.util.Date;

import Model.Discipline.Discipline;

public abstract class Athlete {

    public enum Gender {MALE, FEMALE};

    protected String number;
    protected String name;
    protected String last;
    protected String nacionality;
    protected Gender gender;
    protected int dni;
    protected int porcentageRacesCompleted;
    protected float weight;
    protected float height;
    protected double economy;
    protected String birthdate;
    protected Stats physicalsConditions;

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
    }
    
    //Getters and Setters
    
    public String getNumber() {
		return number;
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
	
	//Abstract Methods

	public abstract void setGender(Gender gender);
	
	protected abstract String calculateCategory(String birthdate);
	
	public abstract String getCategory();

	public abstract float getVelocity(Discipline discipline);
	
	//public abstract String getCategory();
}
