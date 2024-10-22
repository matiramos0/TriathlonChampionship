package Model.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Model.Athlete.Amateur;
import Model.Athlete.Competence;
import Model.Race.AthleteRaceInformation;
import Model.Race.Race;

public class AthletesInfo extends JPanel {

private static final long serialVersionUID = 1L;
private DefaultTableModel tableModel;
private JTable table;
private JScrollPane scroll;
private final ButtonGroup buttonGroup = new ButtonGroup();

public AthletesInfo(Race race) {
	setVisible(true);
	 
	scroll = new JScrollPane();
	scroll.setBounds(10, 59, 951, 547);
	add(scroll);
	
	tableModel = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; 
        }
    };	  
	tableModel.addColumn("Athlete Name");
	tableModel.addColumn("Nacionalidad");
	tableModel.addColumn("Races won per discipline");
	tableModel.addColumn("Total races won");
	tableModel.addColumn("Total races finished");
	tableModel.addColumn("Total races abandon");
	
	table = new JTable(tableModel);
	table.setFont(new Font("Tahoma", Font.LAYOUT_LEFT_TO_RIGHT, 16));
    table.setFillsViewportHeight(true);
    table.setBorder(new LineBorder(new Color(0, 0, 0)));
    scroll.setViewportView(table);
    
	setInfo(race.getListAthletes());   

	//raceTable.getColumnModel().getColumn(0).setPreferredWidth(75);
	
	JCheckBox chckbxNewCheckBox = new JCheckBox("Competence");
	chckbxNewCheckBox.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			List<AthleteRaceInformation> filterRace = race.getListAthletes().stream()
														  .filter(athlete -> athlete.getAthlete() instanceof Competence)
														  .collect(Collectors.toList());
			setInfo(filterRace);   
		}
	});
	buttonGroup.add(chckbxNewCheckBox);
	chckbxNewCheckBox.setBounds(300, 16, 118, 21);
	add(chckbxNewCheckBox);
	
	JCheckBox chckbxNewCheckBox_1 = new JCheckBox("All");
	chckbxNewCheckBox_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			setInfo(race.getListAthletes());   
		}
	});
	chckbxNewCheckBox_1.setSelected(true);
	buttonGroup.add(chckbxNewCheckBox_1);
	chckbxNewCheckBox_1.setBounds(206, 16, 92, 21);
	add(chckbxNewCheckBox_1);
	
	JLabel lblNewLabel = new JLabel("Filter Athletes by:");
	lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
	lblNewLabel.setBounds(63, 6, 137, 36);
	add(lblNewLabel);
	
	JCheckBox chckbxAmateur = new JCheckBox("Amateur");
	chckbxAmateur.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			List<AthleteRaceInformation> filterList = race.getListAthletes().stream()
														  .filter(athlete -> athlete.getAthlete() instanceof Amateur)
														  .collect(Collectors.toList());
			setInfo(filterList);   
		}
	});
	buttonGroup.add(chckbxAmateur);
	chckbxAmateur.setBounds(420, 16, 118, 21);
	add(chckbxAmateur);
	
	
}

	private void setInfo(List<AthleteRaceInformation> athletes) {
		/*for(Race r : finishedRaces) 
		for(AthleteRaceInformation a : r.getListAthletes()) {
			Object[] row = {a.getAthlete().getName(), a.getPosition(),a.getAdvancedDistance() };
			tableModel.addRow(row);
		}Championship stats*/
		tableModel.setRowCount(0);
		for(AthleteRaceInformation a : athletes) {
				Object[] row = {a.getAthlete().getName(), a.getAthlete().getNacionality(),a.getAdvancedTime() ,String.format("%.3f km", a.getAdvancedDistance())};
				tableModel.addRow(row);
		}
	}

}
