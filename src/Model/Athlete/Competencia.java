package Model.Athlete;

import java.util.ArrayList;
import java.util.List;

import Model.Discipline.DistanceDiscipline;
import Model.Race.Race;

public class Competencia {

private Race race;
private List<PenaltyAthlete> penaltys;
private List<DistanceDiscipline> distances;

public Competencia(Race race) {
	this.race = race;
	this.penaltys = new ArrayList<>();
	this.distances = new ArrayList<>();
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
