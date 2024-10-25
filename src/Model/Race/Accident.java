package Model.Race;

import java.util.Random;
import Model.Athlete.Athlete;
import Model.Discipline.Cycling;
import Model.Discipline.Discipline;
import Model.Discipline.Pedestrianism;
import Model.Discipline.Swimming;

public class Accident {
	
	 	private String description;
	    private boolean causesInjury;
	    private long penaltyTime;
	    private int numberRaceOut;

	    public Accident(String description, boolean causesInjury,long penaltyTime, int numberRaceOut) {
	        this.description = description;
	        this.causesInjury = causesInjury;
	        this.penaltyTime= penaltyTime;
	        this.numberRaceOut = numberRaceOut;
	    }
	    
	    public Accident(String description, boolean causesInjury,long penaltyTime) {
	        this.description = description;
	        this.causesInjury = causesInjury;
	        this.penaltyTime= penaltyTime;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public boolean isCausesInjury() {
	        return causesInjury;
	    }

	    public void setCausesInjury(boolean causesInjury) {
	        this.causesInjury = causesInjury;
	    }


		public long getPenaltyTime() {
			return penaltyTime;
		}

		public void setPenaltyTime(long penaltyTime) {
			this.penaltyTime = penaltyTime;
		}
	    
		 public int getNumberRaceOut() {
			return numberRaceOut;
		}

		public void setNumberRaceOut(int numberRaceOut) {
			this.numberRaceOut = numberRaceOut;
		}

		public static Accident generateInjury(AthleteRaceInformation athlete, float fatigue) {
		        Random random = new Random();
		        
		        float injuryProbability = 0.01f * fatigue; 
		        
		        
		        
		        if (random.nextFloat(4000) < injuryProbability) {
		        	
		        	String[] minorInjuries = {
		                    "Calambres en las piernas",
		                    "Fatiga muscular"
		                };
		                String[] severeInjuries = {
		                    "Torcedura de tobillo",
		                    "Desgarro muscular"
		                };
		                
		             boolean isSevereInjury = random.nextFloat(500) < injuryProbability;
		             String injuryDescription;
		             long sleepTime = 0;
		             int numberRaceOut = 0;
		             boolean causesInjury = true;
		            
		             if (isSevereInjury) {
		                 injuryDescription = severeInjuries[random.nextInt(severeInjuries.length)];
		                 sleepTime = -1;
		                 numberRaceOut = random.nextInt(2) + 1;
		             } else {
		                 injuryDescription = minorInjuries[random.nextInt(minorInjuries.length)];
		                 sleepTime = random.nextLong(500) + 1001;
		             }
		             
		             return new Accident(injuryDescription, causesInjury, sleepTime, numberRaceOut);
		        }
		        return null;
		    }
		 
		 public static Accident generateRandomAccident(Discipline discipline) {
		        Random random = new Random();	        
		  
		        float accidentProbability = 0.002f;  
		        
		        if (random.nextFloat(4000) < accidentProbability) {
		            String accidentDescription = null;
		            long sleepTime = 2000;  // Tiempo de penalización fijo (10 segundos)
		            
		            String[] accidents = discipline.getAccidents();  // Obtener la lista de accidentes por diciplina(aplicamos polimorfismo)
		            accidentDescription= accidents[random.nextInt(accidents.length)];
		            
		            if (accidentDescription != null) {
		                return new Accident(accidentDescription, false, sleepTime);  // Sin lesión, pero con tiempo de penalización fijo
		            }
		        }
		        
		        return null;
		    }
	    

	   
}
