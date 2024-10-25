package EventListeners;

import Model.ClimateCondition.ClimateCondition;
import Model.Race.AthleteRaceInformation;

public interface RefreshViewListener {

	void listenRefreshView(float time, ClimateCondition currentWeather);
	
	void listenAdvancePanel(AthleteRaceInformation athleteRace);
		
	int listenChangeVelocity(AthleteRaceInformation athleteRace);

	void listenInterruptRace(boolean interruption)throws InterruptedException;
	
	void listenShowCurrentRanking();

}
