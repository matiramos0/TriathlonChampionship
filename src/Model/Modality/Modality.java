package Model.Modality;

import java.util.HashSet;
import java.util.Set;

import Model.Discipline.DistanceDiscipline;

public class Modality {
	
	private Modalities modality;
	private DistanceDiscipline swimming;
	private DistanceDiscipline cycling;
	private DistanceDiscipline pedestrianism;
	
	public Modality(DistanceDiscipline swimming, DistanceDiscipline cycling, DistanceDiscipline pedestrianism) {
		this.swimming = swimming;
		this.cycling = cycling;
		this.pedestrianism = pedestrianism;
	}
	
	// Methods
	
	public float getFirstTransition() {
		return swimming.getDistance();
	}
	
	public float getSecondTransition() {
		return swimming.getDistance() + cycling.getDistance();
	}
	
	public float getTotalDistance() {
		return swimming.getDistance() + cycling.getDistance() + pedestrianism.getDistance();
	}
	
	//Getters and Setters
	
	public Modalities getModalities() {
		return modality;
	}

	public void setModalities(Modalities modality) {
		this.modality = modality;
	}

	public DistanceDiscipline getSwimming() {
		return swimming;
	}

	public void setSwimming(DistanceDiscipline swimming) {
		this.swimming = swimming;
	}

	public DistanceDiscipline getCycling() {
		return cycling;
	}

	public void setCycling(DistanceDiscipline cycling) {
		this.cycling = cycling;
	}

	public DistanceDiscipline getPedestrianism() {
		return pedestrianism;
	}

	public void setPedestrianism(DistanceDiscipline pedestrianism) {
		this.pedestrianism = pedestrianism;
	}	
	
}
