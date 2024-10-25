package Model.Modality;

import java.io.Serializable;

public enum Modalities implements Serializable{ 
	
	SPRINT("Sprint"), OLYMPIC("Distancia olímpico"), 
	LONG("Larga distancia"), MIDDLE("Media distancia");
	
	private String description;

	Modalities(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
}
