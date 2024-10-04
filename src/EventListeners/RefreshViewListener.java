package EventListeners;

import Model.ClimateCondition.ClimateCondition;
import Model.Race.AthleteRaceInformation;

public interface RefreshViewListener {

	void listenRefreshView(int time, ClimateCondition currentWeather);
	
	void listenAdvancePanel(AthleteRaceInformation athleteRace);
	
	void listenRefreshPositions();
}
