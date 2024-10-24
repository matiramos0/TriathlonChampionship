package Model.Discipline;

import java.util.List;

public abstract class Discipline {
	
	private String description;
	
	public Discipline() {
	}

	//Abstract methods
		
	public abstract String getDescription();
	
	public abstract String[] getAccidents();
}
