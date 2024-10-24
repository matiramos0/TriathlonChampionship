package Model.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Model.Athlete.Amateur;
import Model.Athlete.Athlete;
import Model.Athlete.Athlete.Gender;
import Model.Athlete.Competence;
import Model.Athlete.Competencia;
import Model.Race.AthleteRaceInformation;
import Model.Race.Race;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StatsPanel extends JPanel{

private static final long serialVersionUID = 1L;
private DefaultTableModel tableModel;
private JTable table;
private JScrollPane scroll;
private final ButtonGroup buttonGroup = new ButtonGroup();
private List<Athlete> athletes;

public StatsPanel(List<Athlete> athletesList) {
	setVisible(true);
	this.athletes = athletesList;
	
	scroll = new JScrollPane();
	scroll.setBounds(10, 68, 1300, 547);
	add(scroll);
	
	Collections.sort(athletes, new Comparator<Athlete>() {
		@Override
		public int compare(Athlete o1, Athlete o2) {
			return o1.getChampionshipInformation().getLast().getPosition() - o2.getChampionshipInformation().getLast().getPosition();	
		}	
	});
	
	tableModel = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; 
        }
    };	  
	tableModel.addColumn("Athlete Name");
	tableModel.addColumn("Race Position");
	tableModel.addColumn("Advanced Distance");
	tableModel.addColumn("Swimming Time");
	tableModel.addColumn("Cycling Time");
	tableModel.addColumn("Pedestrianism Time");
	tableModel.addColumn("Total Time");
	tableModel.addColumn("Abandon in:"); 
	table = new JTable(tableModel);
	table.setFont(new Font("Tahoma", Font.LAYOUT_LEFT_TO_RIGHT, 16));
    table.setFillsViewportHeight(true);
    table.setBorder(new LineBorder(new Color(0, 0, 0)));
    scroll.setViewportView(table);
    
    setInfo(athletes); 
	
	JCheckBox chckbxNewCheckBox = new JCheckBox("Competence");
	chckbxNewCheckBox.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			List<Athlete> filterRace = athletes.stream()
												.filter(athlete -> athlete instanceof Competence)
												.collect(Collectors.toList());
			setInfo(filterRace);   
		}
	});
	buttonGroup.add(chckbxNewCheckBox);
	chckbxNewCheckBox.setBounds(58, 15, 97, 21);
	add(chckbxNewCheckBox);
	
	JCheckBox chckbxNewCheckBox_1 = new JCheckBox("All");
	chckbxNewCheckBox_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			setInfo(athletes);   
		}
	});
	chckbxNewCheckBox_1.setSelected(true);
	buttonGroup.add(chckbxNewCheckBox_1);
	chckbxNewCheckBox_1.setBounds(6, 15, 50, 21);
	add(chckbxNewCheckBox_1);
	
	JCheckBox chckbxAmateur = new JCheckBox("Amateur");
	chckbxAmateur.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			List<Athlete> filterList = athletes.stream()
												.filter(athlete -> athlete instanceof Amateur)
										        .collect(Collectors.toList());
			setInfo(filterList);   
		}
	});
	buttonGroup.add(chckbxAmateur);
	chckbxAmateur.setBounds(157, 15, 77, 21);
	add(chckbxAmateur);
	
	JCheckBox chckbxMale = new JCheckBox("Male");
	chckbxMale.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			List<Athlete> filterList = athletes.stream()
												.filter(athlete -> athlete.getGender().equals(Gender.MALE))
												.collect(Collectors.toList());
			setInfo(filterList);   
		}
	});
	buttonGroup.add(chckbxMale);
	chckbxMale.setBounds(236, 15, 63, 21);
	add(chckbxMale);
	
	JCheckBox chckbxFemale = new JCheckBox("Female");
	chckbxFemale.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			List<Athlete> filterList = athletes.stream()
												.filter(athlete -> athlete.getGender().equals(Gender.FEMALE))
												.collect(Collectors.toList());
			setInfo(filterList);   
		}
	});
	buttonGroup.add(chckbxFemale);
	chckbxFemale.setBounds(301, 15, 78, 21);
	add(chckbxFemale);
	
}

	private void setInfo(List<Athlete> athletes) {
		tableModel.setRowCount(0);
		float bestTime = 0;
		Competencia lastCompetencia;

		for (Athlete a : athletes) {
			
			Object[] row = new Object[tableModel.getColumnCount()];
			lastCompetencia = a.getChampionshipInformation().getLast();

			row[0] = a.getName();
			row[1] = lastCompetencia.getPosition();
			row[2] = lastCompetencia.getDistances().getLast().getDistance();

			for (int i = 0; i < lastCompetencia.getDistances().size(); i++) 
					row[3 + i] = lastCompetencia.getDistances().get(i).getTime();		
			
				if (a.equals(athletes.getFirst()) && lastCompetencia.getDistances().getLast().getDistance()
													>lastCompetencia.getRace().getModality().getTotalDistance()){
				 //Si es el primero y termino la carrera:																					
					 bestTime = lastCompetencia.getTotalTime();
					 row[tableModel.getColumnCount() - 2] = bestTime;
				} else if (lastCompetencia.getDistances().getLast().getDistance() > lastCompetencia.getRace().getModality().getTotalDistance())// si termino la carrera
							row[tableModel.getColumnCount() - 2] = bestTime - lastCompetencia.getTotalTime();
			
			if(lastCompetencia.isAbandon())
				row[tableModel.getColumnCount() - 1] = lastCompetencia.getDistances().getLast().getDiscipline().getDescription();
					  
			tableModel.addRow(row);
	
		}
	}
	
}
