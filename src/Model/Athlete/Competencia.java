package Model.Athlete;

import java.util.ArrayList;
import java.util.List;

import Model.Discipline.Cycling;
import Model.Discipline.DistanceDiscipline;
import Model.Discipline.Pedestrianism;
import Model.Discipline.Swimming;
import Model.Race.Race;

public class Competencia {

private Race race;
private List<PenaltyAthlete> penaltys;
private List<DistanceDiscipline> distances;

public Competencia(Race race) {
	this.race = race;
	this.penaltys = new ArrayList<>();
	this.distances = new ArrayList<>();
	disciplineChange();
}

	public void disciplineChange() {
		int discipline = distances.size();
		System.out.println(discipline);
		if (discipline == 0)
			distances.add(new DistanceDiscipline(0, new Swimming()));
		else if (discipline == 1) {
			distances.getLast().setTime(race.getTime());
			distances.add(new DistanceDiscipline(0, new Cycling()));
		}
		else if (discipline == 2) {
			distances.getLast().setTime(race.getTime());
			distances.add(new DistanceDiscipline(0, new Pedestrianism()));
		} else
			distances.getLast().setTime(race.getTime());

	}
	

	public void advance(float newDistance) {
		float distanceMax = race.getModality().getDistance(distances.getLast().getDiscipline());
		if(newDistance <= distanceMax)
			distances.getLast().setDistance(newDistance);
		else {
			disciplineChange();
			if(!(distances.size() == 4))
				distances.getLast().setDistance(newDistance);
		}
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public List<PenaltyAthlete> getPenaltys() {
		return penaltys;
	}

	public void setPenaltys(List<PenaltyAthlete> penaltys) {
		this.penaltys = penaltys;
	}

	public List<DistanceDiscipline> getDistances() {
		return distances;
	}

	public void setDistances(List<DistanceDiscipline> distances) {
		this.distances = distances;
	}

}
