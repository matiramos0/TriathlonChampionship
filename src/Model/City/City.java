package Model.City;

import java.io.Serializable;

public class City implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String description;	
	private Country country;
	
	public City(String description, Country country) {
		this.description = description;
		this.country = country;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
}
