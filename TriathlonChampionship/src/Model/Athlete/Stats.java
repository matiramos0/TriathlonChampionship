package Model.Athlete;

public class Stats {
	
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
