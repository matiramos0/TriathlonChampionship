package InitMain;

import Controller.Championship;
import Model.Race.Race;
import Model.View.MainView;

public class InitGame {

private static MainView mainView;
private static Championship championship;

	public static void main (String[] args) {
		
		championship = Championship.getInstance();	
		mainView = new MainView(championship);
		championship.setMainView(mainView);	

		mainView.setVisible(true);
		mainView.setLocationRelativeTo(null);
		
		}
}
