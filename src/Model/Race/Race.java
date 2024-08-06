package Model.Race;

import java.util.ArrayList;
import java.util.List;

import Model.City.City;
import Model.Modality.Modality;
import Model.ClimateCondition.ClimateCondition;
import Model.Discipline.Provisioning;
import Model.Athlete.Athlete;

public class Race {

	private Modality modality;
	private City city;
	private List <Provisioning> listPrivisioning = new ArrayList<>();
	//private List <ClimateCondition> listClimateCondition = new ArrayList<>();
	private List <Athlete> listAthlete = new ArrayList<>();
	
	public Race(Modality modality, City city, ArrayList<Provisioning> listPrivisioning) {
		this.modality = modality;
		this.city = city;
		this.listPrivisioning = listPrivisioning;
	}

	public Modality getModality() {
		return modality;
	}

	public void setModality(Modality modality) {
		this.modality = modality;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public ArrayList<Provisioning> getListprivisioning() {
		return (ArrayList<Provisioning>) listPrivisioning;
	}

	public void setListprivisioning(ArrayList<Provisioning> listprivisioning) {
		this.listPrivisioning = listprivisioning;
	}
	
	public ArrayList<Athlete> getListathlete() {
		return (ArrayList<Athlete>) listAthlete;
	}

	public void setListathlete(ArrayList<Athlete> listathlete) {
		this.listAthlete = listathlete;
	}
	
	
	
}
