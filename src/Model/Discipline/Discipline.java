package Model.Discipline;

import java.io.Serializable;

public abstract class Discipline implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected static final long CONTROL_VELOCITY = 300;
	
	private String description;
	
	public Discipline() {
	}
	
	//Methods

	//Abstract methods
	
	public abstract float getEffectFatigue(); // returns fatigue factor
	
	public abstract float getVelocityInDiscipline(); // In these methods, returns the average speed of the athlete in the discipline
		
	public abstract String getDescription();
	
	public abstract String[] getAccidents();
}
