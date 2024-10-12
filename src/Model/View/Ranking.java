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

public class Ranking extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel racePane;
	private JTable raceTable;
	private DefaultTableModel tableModel;
	private JPanel championshipPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	
	public Ranking() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1010, 700);
		//setAlwaysOnTop(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 976, 643);
		contentPane.add(tabbedPane);
		
		racePane = new JPanel();
		tabbedPane.addTab("Race ranking", null, racePane, null);
		tabbedPane.setEnabledAt(0, true);
		racePane.setLayout(null);
		
		tableModel = new DefaultTableModel(){
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false; // Hacer que todas las celdas sean no editables
	        }
	    };	    	
		tableModel.addColumn("Nombre");
		tableModel.addColumn("Categoria");
		tableModel.addColumn("Monto");
		tableModel.addColumn("Descripcion");
		tableModel.addColumn("Fecha");
		tableModel.addColumn("numero pago");
		
		raceTable = new JTable(tableModel);
		raceTable.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    raceTable.setFillsViewportHeight(true);
	    raceTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		raceTable.setBounds(10, 59, 951, 547);
		racePane.add(raceTable);
		
		raceTable.getColumnModel().getColumn(0).setPreferredWidth(75);
		raceTable.getColumnModel().getColumn(1).setPreferredWidth(75);
		raceTable.getColumnModel().getColumn(2).setPreferredWidth(60);
		raceTable.getColumnModel().getColumn(3).setPreferredWidth(260);
		raceTable.getColumnModel().getColumn(4).setPreferredWidth(80);
		raceTable.getColumnModel().getColumn(5).setPreferredWidth(40);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Competence");
		buttonGroup.add(chckbxNewCheckBox);
		chckbxNewCheckBox.setBounds(300, 16, 118, 21);
		racePane.add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("All");
		chckbxNewCheckBox_1.setSelected(true);
		buttonGroup.add(chckbxNewCheckBox_1);
		chckbxNewCheckBox_1.setBounds(206, 16, 92, 21);
		racePane.add(chckbxNewCheckBox_1);
		
		JLabel lblNewLabel = new JLabel("Filter Positions by:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(63, 6, 137, 36);
		racePane.add(lblNewLabel);
		
		JCheckBox chckbxAmateur = new JCheckBox("Amateur");
		buttonGroup.add(chckbxAmateur);
		chckbxAmateur.setBounds(420, 16, 118, 21);
		racePane.add(chckbxAmateur);
		
		championshipPane = new JPanel();
		tabbedPane.addTab("Championship Ranking", null, championshipPane, null);
		tabbedPane.setEnabledAt(1, true);
	}
	
	public void showRaceRanking(List<Race> finishedRaces, Race race) {
		/*for(Race r : finishedRaces) 
			for(AthleteRaceInformation a : r.getListAthletes()) {
				Object[] row = {a.getAthlete().getName(), a.getPosition(),a.getAdvancedDistance() ,"hola", "3/2/2", 34};
				tableModel.addRow(row);
			}Championship stats*/
		for(AthleteRaceInformation a : race.getListAthletes()) {
			Object[] row = {a.getAthlete().getName(), a.getPosition(),a.getAdvancedTime() ,"hola", "3/2/2", 34};
			tableModel.addRow(row);
		}
	}
	
	//public void showRaceRanking(Race race) {
		
	//}
}
