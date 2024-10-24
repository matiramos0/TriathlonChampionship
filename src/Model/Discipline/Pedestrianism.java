package Model.Discipline;

public class Pedestrianism extends Discipline{

	public Pedestrianism() {}

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
	
	
}
