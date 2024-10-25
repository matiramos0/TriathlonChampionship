package Model.ClimateCondition;

import java.io.Serializable;

public class UnitMeasure implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
