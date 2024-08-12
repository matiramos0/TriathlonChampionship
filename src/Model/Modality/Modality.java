package Model.Modality;

import java.util.HashSet;
import java.util.Set;

import Model.Discipline.DistanceDiscipline;

public class Modality {
	
	private String description;
	private DistanceDiscipline swimming;
	private DistanceDiscipline cycling;
	private DistanceDiscipline pedestrianism;
	
	public Modality(String description, DistanceDiscipline swimming, DistanceDiscipline cycling, DistanceDiscipline pedestrianism) {
		this.description = description;
		this.swimming = swimming;
		this.cycling = cycling;
		this.pedestrianism = pedestrianism;
	}
	
	public float totalDistance() {
		return swimming.getDistance() + cycling.getDistance() + pedestrianism.getDistance();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
