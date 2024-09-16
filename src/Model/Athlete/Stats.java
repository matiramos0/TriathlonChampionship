package Model.Athlete;

public class Stats {
	
	private static final float VELOCITY_STONING_PROM = 10.5F; // Km/h
	private static final float VELOCITY_SWIMMING_PROM = 2.8F; // Km/h
	private static final float VELOCITY_CYCLING_PROM = 30F; // Km/h
	private static final long CONTROL_VELOCITY = 300;
	
	private float swimming;
	private float cycling;
	private float stoning;
	private float resistance;
	private float psychologicalStrenght;
	
	public Stats(float swimming, float cycling, float stoning, float resistance, float psychologicalStrenght) {
		this.swimming = swimming;
		this.cycling = cycling;
		this.stoning = stoning;
		this.resistance = resistance;
		this.psychologicalStrenght = psychologicalStrenght;
	}
	
	//Methods
	
	// In these methods, returns the average speed of the athlete,
	// adding their physical capacity in the discipline as a percentage.
	
	public float getVelocitySwimming() { 
		float velocity = VELOCITY_SWIMMING_PROM/CONTROL_VELOCITY;
		//velocity += velocity*(swimming/100);
		
		return velocity;
	}
	
	public float getVelocityCycling() {
		float velocity = VELOCITY_CYCLING_PROM/CONTROL_VELOCITY;
		//velocity += velocity*(cycling/100);
		
		return velocity;
	}
	
	public float getVelocityStoning() {
		float velocity = VELOCITY_STONING_PROM/CONTROL_VELOCITY;
		//velocity += velocity*(stoning/100);
		
		return velocity;
	}
	
	//Getters and Setters

	public float getSwimming() {
		return swimming;
	}

	public void setSwimming(float swimming) {
		this.swimming = swimming;
	}

	public float getCycling() {
		return cycling;
	}

	public void setCycling(float cycling) {
		this.cycling = cycling;
	}

	public float getStoning() {
		return stoning;
	}

	public void setStoning(float stoning) {
		this.stoning = stoning;
	}

	public float getResistance() {
		return resistance;
	}

	public void setResistance(float resistance) {
		this.resistance = resistance;
	}

	public float getPsychologicalStrenght() {
		return psychologicalStrenght;
	}

	public void setPsychologicalStrenght(float psychologicalStrenght) {
		this.psychologicalStrenght = psychologicalStrenght;
	}
	
}
