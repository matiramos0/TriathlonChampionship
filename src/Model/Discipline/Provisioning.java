package Model.Discipline;

import java.io.Serializable;

public class Provisioning implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Discipline discipline;
	private float distance;
	private int number;

	public Provisioning(int number, float distance, Discipline discipline) {
		this.number = number;
		this.distance = distance;
		this.discipline = discipline;
	}
	
	public float getDistance() {
		return distance;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}
	
	public Discipline getDiscipline() {
		return discipline;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

}
