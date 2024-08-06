package Model.Athlete;

import java.util.Date;

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
    
    public abstract void setGender(Gender gender);
}
