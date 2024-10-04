package Model.ClimateCondition;

import java.util.List;
import java.util.Random;

public class ClimateCondition {

	private int wearId;
	private String description;
	private UnitMeasure unitmeasure;
	private double lowerMarck;
	private double upperMark;
	private double swimmingWear;
	private double cyclingWear;
	private double runningWear;

	
	public ClimateCondition(int wearId, String description, UnitMeasure unitmeasure, double lowerMarck,
			double upperMark, double swimmingWear, double cyclingWear, double runningWear) {
		this.wearId = wearId;
		this.description = description;
		this.unitmeasure = unitmeasure;
		this.lowerMarck = lowerMarck;
		this.upperMark = upperMark;
		this.swimmingWear = swimmingWear;
		this.cyclingWear = cyclingWear;
		this.runningWear = runningWear;
	}


	public int getWearId() {
		return wearId;
	}


	public String getDescription() {
		return description;
	}


	public UnitMeasure getUnitmeasure() {
		return unitmeasure;
	}


	public double getLowerMarck() {
		return lowerMarck;
	}


	public double getUpperMark() {
		return upperMark;
	}


	public double getSwimmingWear() {
		return swimmingWear;
	}


	public double getCyclingWear() {
		return cyclingWear;
	}


	public double getRunningWear() {
		return runningWear;
	}


	@Override
	public String toString() {
		return "ClimateCondition [wearId=" + wearId + ", description=" + description + ", unitmeasure=" + unitmeasure
				+ ", lowerMarck=" + lowerMarck + ", upperMark=" + upperMark + ", swimmingWear=" + swimmingWear
				+ ", cyclingWear=" + cyclingWear + ", runningWear=" + runningWear + "]";
	}
	
	
    public static ClimateCondition getRandomWeatherCondition(List<ClimateCondition> weatherConditions) {
        Random random = new Random();
        int randomIndex = random.nextInt(weatherConditions.size());
        return weatherConditions.get(randomIndex);
    }


}
