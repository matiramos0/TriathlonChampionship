package Model.Discipline;

public class Cycling extends Discipline {
	
	private final float fatigueValueCycling = 0.000000015F; //porcentage
	private final float VELOCITY_CYCLING_PROM = 30F; // Km/h

	public Cycling() {}

	@Override
	public String getDescription() {
		return "Cycling";
	}

	@Override
	public String[] getAccidents() {
		String[] cyclingAccidents = {
                "Pinchazo de neumático",
                "Problemas técnicos con la bicicleta",
                "Caída en la bicicleta"
            };
		return cyclingAccidents;
	}

	@Override
	public float getVelocityInDiscipline() {
		return VELOCITY_CYCLING_PROM/CONTROL_VELOCITY;
	}

	@Override
	public float getEffectFatigue() {
		return fatigueValueCycling;
	}
	
	
	
}
