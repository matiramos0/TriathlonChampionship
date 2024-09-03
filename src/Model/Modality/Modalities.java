package Model.Modality;

public enum Modalities { 
	
	SPRINT("Sprint"), OLYMPIC("Distancia olimpica"), 
	LONG("Larga distancia"), MIDDLE("Media distancia");
	
	private String description;

	Modalities(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
}
