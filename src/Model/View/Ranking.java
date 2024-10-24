package Model.View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Model.Athlete.Athlete;
import Model.Race.AthleteRaceInformation;
import Model.Race.Race;

import javax.swing.JLayeredPane;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JProgressBar;
import javax.swing.JTree;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;

public class Ranking extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel racePane;
	private JPanel championshipPane;
	private JPanel athletesInfoPane;
	
	
	public Ranking(List<Athlete> athletes) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1340, 700);
		//setAlwaysOnTop(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 1300, 643);
		contentPane.add(tabbedPane);
		
		racePane = new StatsPanel(athletes);
		//racePane.setBackground(new Color(255, 255, 255));
		racePane.setLayout(null);

		tabbedPane.addTab("Race ranking", null, racePane, null);
		tabbedPane.setEnabledAt(0, true);
		
		athletesInfoPane = new AthletesInfo(athletes); 
		//athletesInfoPane.setBackground(new Color(255, 255, 255));
		athletesInfoPane.setLayout(null);
		
		tabbedPane.addTab("Athletes", null, athletesInfoPane, null);
		tabbedPane.setEnabledAt(1, true);
		
		championshipPane = new ChampionshipRanking(athletes);

		tabbedPane.addTab("Championship Ranking", null, championshipPane, null);
		tabbedPane.setEnabledAt(2, true);
	}
	
	
}
