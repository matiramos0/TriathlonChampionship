package Model.City;

import java.io.Serializable;

public class Country implements Serializable{

	private static final long serialVersionUID = 1L;
	private String description;
	
	public Country(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
