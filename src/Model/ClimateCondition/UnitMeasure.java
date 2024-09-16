package Model.ClimateCondition;

public class UnitMeasure {
	
	public UnitMeasure(String description) {
		super();
		this.description = description;
	}

	private String description;

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "UnitMeasure [description=" + description + "]";
	}


}
