package Model.Discipline;

public class Cycling extends Discipline {

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
	
	
	
}
