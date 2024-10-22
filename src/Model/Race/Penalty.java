package Model.Race;

public class Penalty {
	private String description;
	private boolean disqualification;
	
	public Penalty(String description, boolean disqualification) {
		super();
		this.description = description;
		this.disqualification = disqualification;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDisqualification() {
		return disqualification;
	}

	public void setDisqualification(boolean disqualification) {
		this.disqualification = disqualification;
	}
	
}
