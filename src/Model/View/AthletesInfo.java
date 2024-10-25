package Model.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Model.Athlete.Amateur;
import Model.Athlete.Athlete;
import Model.Athlete.Athlete.Gender;
import Model.Athlete.Competence;
import Model.Athlete.Competition;
import Model.Discipline.Cycling;
import Model.Discipline.Pedestrianism;
import Model.Discipline.Swimming;

public class AthletesInfo extends JPanel {

private static final long serialVersionUID = 1L;
private DefaultTableModel tableModel;
private JTable table;
private JScrollPane scroll;
private final ButtonGroup buttonGroup = new ButtonGroup();

public AthletesInfo(List<Athlete> athletes) {
	setVisible(true);
	 
	scroll = new JScrollPane();
	scroll.setBounds(10, 68, 1300, 547);
	add(scroll);
	
	tableModel = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; 
        }
    };	  
	tableModel.addColumn("Athlete Name");
	tableModel.addColumn("Nacionalidad");
	tableModel.addColumn("Stages won in swimming");
	tableModel.addColumn("Stages won in cycling");
	tableModel.addColumn("Stages won in pedestrianism");
	tableModel.addColumn("Total races won");
	tableModel.addColumn("Total races finished");
	tableModel.addColumn("Total races abandon");

	table = new JTable(tableModel);
	table.setFont(new Font("Tahoma", Font.LAYOUT_LEFT_TO_RIGHT, 16));
    table.setFillsViewportHeight(true);
    table.setBorder(new LineBorder(new Color(0, 0, 0)));
    scroll.setViewportView(table);
    
	setInfo(athletes);   
	
	JPanel panel_1 = new JPanel();
	panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Filter By", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	panel_1.setBounds(75, 10, 385, 48);
	add(panel_1);
	panel_1.setLayout(null);
	
	
	JCheckBox chckbxNewCheckBox = new JCheckBox("Competence");
	chckbxNewCheckBox.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			List<Athlete> filterRace = athletes.stream()
												.filter(athlete -> athlete instanceof Competition)
												.collect(Collectors.toList());
			setInfo(filterRace);   
		}
	});
	buttonGroup.add(chckbxNewCheckBox);
	chckbxNewCheckBox.setBounds(58, 15, 97, 21);
	panel_1.add(chckbxNewCheckBox);
	
	
	JCheckBox chckbxNewCheckBox_1 = new JCheckBox("All");
	chckbxNewCheckBox_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			setInfo(athletes);   
		}
	});
	chckbxNewCheckBox_1.setSelected(true);
	buttonGroup.add(chckbxNewCheckBox_1);
	chckbxNewCheckBox_1.setBounds(6, 15, 50, 21);
	panel_1.add(chckbxNewCheckBox_1);
	
	
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
	panel_1.add(chckbxAmateur);
	
	
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
	panel_1.add(chckbxMale);
	
	
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
	panel_1.add(chckbxFemale);
	
}

	private void setInfo(List<Athlete> athletes) {

		tableModel.setRowCount(0);
		Competence lastCompetencia;
		
		for (Athlete a : athletes) {
			
			Object[] row = new Object[tableModel.getColumnCount()];
			lastCompetencia = a.getChampionshipInformation().getLast();

			row[0] = a.getName();
			row[1] = a.getNacionality();
			row[2] = a.getStagesWon(new Swimming());
			row[3] = a.getStagesWon(new Cycling());
			row[4] = a.getStagesWon(new Pedestrianism());
			row[5] = a.getRacesWon();
			row[6] = a.getRacesFinished();
			row[7] = a.getRacesAbandoned();
	  
			tableModel.addRow(row);
	
		}
	}

}
