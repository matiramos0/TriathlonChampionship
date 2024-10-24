package Model.Discipline;

public class Swimming extends Discipline {

	public Swimming() {}

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
	
	
	
	
}
