package Model.Athlete;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Model.Discipline.Cycling;
import Model.Discipline.DistanceDiscipline;
import Model.Discipline.Pedestrianism;
import Model.Discipline.Swimming;
import Model.Race.Race;

public class Competence implements Serializable{

private Race race;
private List<PenaltyAthlete> penaltys;
private List<DistanceDiscipline> distances;
private int position;
private boolean abandon;

private static final long serialVersionUID = 1L;

public Competence(Race race) {
	this.race = race;
	this.penaltys = new ArrayList<>();
	this.distances = new ArrayList<>();
	this.abandon = false;
	disciplineChange();
}

	public void disciplineChange() {
		int discipline = distances.size();
	//Cada vez que termina una disciplina, se indica el tiempo tardado y la position en la que termino en esa disciplina
	//Y se agrega la siguiente (si es que hay)
		
		if (discipline == 0)
			distances.add(new DistanceDiscipline(0, new Swimming()));
		else if (discipline == 1) {
			distances.getLast().setTime(race.getTime());
			distances.getLast().setDisciplinePosition(position);
			distances.add(new DistanceDiscipline(0, new Cycling()));
		} else if (discipline == 2) {
			distances.getLast().setTime(race.getTime() - distances.get(0).getTime());
			distances.getLast().setDisciplinePosition(position);
			distances.add(new DistanceDiscipline(0, new Pedestrianism()));
		} else { 
			distances.getLast().setDisciplinePosition(position);
			distances.getLast().setTime(race.getTime() - distances.get(1).getTime() - distances.get(0).getTime());
		}
	}
	
	public void advance(float newDistance) {
		//Distancia de la proxima transicion
		float distanceMax = race.getModality().getDistance(distances.getLast().getDiscipline());
		
		if(newDistance <= distanceMax)
			distances.getLast().setDistance(newDistance);
		else { 
		//Si supero la disciplina actual, Cambia
			disciplineChange();
			distances.getLast().setDistance(newDistance);
		}
	}
	
	public float getTotalTime() {
		float time = 0;
		for(DistanceDiscipline d : distances)
			time += d.getTime();
		return time;
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

	public int getPosition() {
		return position;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}

	public boolean isAbandon() {
		return abandon;
	}

	public void setAbandon(boolean abandon) {
		this.abandon = abandon;
	}

	@Override
	public String toString() {
		return race.toString() ;
	}

	
}
