package Model.Discipline;

public class Pedestrianism extends Discipline{
	
	public Pedestrianism() {}
	
	private final float fatigueValuePedestrianism = 0.1F; //porcentage
	private final float VELOCITY_STONING_PROM = 10.5F; // Km/h

	@Override
	public String getDescription() {
		return "Pedestrianism";
	}

	@Override
	public String[] getAccidents() {
		String[] pedestrianismAccidents = {
                "Resbalón en el tramo de pedestrismo",
                "Cordones desatados"
            };
		return pedestrianismAccidents;
	}

	@Override
	public float getVelocityInDiscipline() {
		return VELOCITY_STONING_PROM/CONTROL_VELOCITY;
	}

	@Override
	public float getEffectFatigue() {
		return fatigueValuePedestrianism;
	}
	
	
}
