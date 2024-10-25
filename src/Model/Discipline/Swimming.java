package Model.Discipline;

public class Swimming extends Discipline {

	public Swimming() {}
	
	private final float fatigueValueSwimming = 0.14F; // porcentage
	private final float VELOCITY_SWIMMING_PROM = 2.8F; // Km/h

	@Override
	public String getDescription() {
		return "Swimming";
	}

	@Override
	public String[] getAccidents() {
		String[] swimmingAccidents = {
                "Desprendimiento de gafas",
                "Problemas con la respiración"
            };
		return swimmingAccidents;
	}

	@Override
	public float getVelocityInDiscipline() {
		return VELOCITY_SWIMMING_PROM/CONTROL_VELOCITY;
	}

	@Override
	public float getEffectFatigue() {
		return fatigueValueSwimming;
	}	
	 
}
