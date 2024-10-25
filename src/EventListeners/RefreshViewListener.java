package EventListeners;

import Model.ClimateCondition.ClimateCondition;
import Model.Race.AthleteRaceInformation;

public interface RefreshViewListener {

	void listenRefreshView(float time, ClimateCondition currentWeather);
	
	void listenAdvancePanel(AthleteRaceInformation athleteRace);
	
	void listenRefreshPositions();
	
	int listenChangeVelocity(AthleteRaceInformation athleteRace);
}
