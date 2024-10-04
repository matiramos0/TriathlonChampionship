package InitMain;

import Controller.Championship;
import Model.Race.Race;
import Model.View.MainView;

public class InitGame {

	public static void main (String[] args) {
		
		//Championship currentChampionship = new Championship();
		MainView mainView = new MainView();
		mainView.setVisible(true);
		mainView.setLocationRelativeTo(null);
	}
}
