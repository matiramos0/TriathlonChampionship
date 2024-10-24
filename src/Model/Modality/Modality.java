package Model.Modality;

import java.io.Serializable;

import Model.Discipline.Cycling;
import Model.Discipline.Discipline;
import Model.Discipline.DistanceDiscipline;
import Model.Discipline.Swimming;

public class Modality implements Serializable{
	
	private static final long serialVersionUID = 1L;
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
	
	public float getDistance(Discipline d) {
		if (d instanceof Swimming)
			return getFirstTransition();
		else if (d instanceof Cycling)
			return getSecondTransition();
		else
			return getTotalDistance();
	}
	
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
