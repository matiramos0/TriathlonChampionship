package InitMain;

import Controller.Championship;
import Model.View.MainView;

public class InitGame {

private static MainView mainView;
private static Championship championship;

	public static void main (String[] args) {
		
		mainView = new MainView(Championship.getInstance());
		mainView.setVisible(true);
		mainView.setLocationRelativeTo(null);
		Championship.setMainView(mainView);	
		//Race race = Championship.createNewRace();
		//race.startRace();
		
		//championship = Championship.getInstance();	
		//championship.getMainView();
	}
}
