package Model.Discipline;


public class DistanceDiscipline {
	
	private float distance;
	private float time;
	private Discipline discipline;
	
	public DistanceDiscipline(float distance, Discipline discipline) {
		this.distance = distance;
		this.discipline = discipline;
	}
	
	//Getters and Setters
	
	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public float getTime() {
		return time;
	}

	public void setTime(float time) {
		this.time = time;
	}

}
