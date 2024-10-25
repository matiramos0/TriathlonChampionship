package Model.Discipline;

import java.io.Serializable;

public class DistanceDiscipline implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private float distance;
	private float time;
	private Discipline discipline;
	private int disciplinePosition;
	
	public DistanceDiscipline(float distance, Discipline discipline) {
		this.distance = distance;
		this.discipline = discipline;
		this.time = 0;
		this.disciplinePosition = 0;
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

	public int getDisciplinePosition() {
		return disciplinePosition;
	}

	public void setDisciplinePosition(int disciplinePosition) {
		this.disciplinePosition = disciplinePosition;
	}

}
