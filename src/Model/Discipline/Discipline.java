package Model.Discipline;

import java.io.Serializable;

public abstract class Discipline implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String description;
	
	public Discipline() {
	}

	//Abstract methods
		
	public abstract String getDescription();
}
