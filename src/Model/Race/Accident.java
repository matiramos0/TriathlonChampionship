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
	    private float penaltyTime;  

	    public Accident(String description, boolean causesInjury,float penaltyTime) {
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


		public float getPenaltyTime() {
			return penaltyTime;
		}

		public void setPenaltyTime(float penaltyTime) {
			this.penaltyTime = penaltyTime;
		}
	    
		 public static Accident generateInjury(Athlete athlete, float fatigue) {
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
		             float penaltyTime = 0;
		             boolean causesInjury = true;    
		            
		             if (isSevereInjury) {
		                 injuryDescription = severeInjuries[random.nextInt(severeInjuries.length)];
		                 penaltyTime = -1;  
		             } else {
		                 injuryDescription = minorInjuries[random.nextInt(minorInjuries.length)];
		                 penaltyTime =  0.5f + random.nextFloat() * 10;
		             }
		             
		             return new Accident(injuryDescription, causesInjury, penaltyTime);
		        }
		        return null;
		    }
		 
		 public static Accident generateRandomAccident(Discipline discipline) {
		        Random random = new Random();	        
		  
		        float accidentProbability = 0.002f;  
		        
		        if (random.nextFloat(500) < accidentProbability) {
		            String accidentDescription = null;
		            float penaltyTime = 10.0f;  // Tiempo de penalización fijo (10 segundos)
		            
		            String[] accidents = discipline.getAccidents();  // Obtener la lista de accidentes por diciplina(aplicamos polimorfismo)
		            accidentDescription= accidents[random.nextInt(accidents.length)];
		            
		            if (accidentDescription != null) {
		                return new Accident(accidentDescription, false, penaltyTime);  // Sin lesión, pero con tiempo de penalización fijo
		            }
		        }
		        
		        return null;
		    }
	    

	   
}
