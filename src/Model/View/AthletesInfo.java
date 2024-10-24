package Model.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Model.Athlete.Amateur;
import Model.Athlete.Athlete;
import Model.Athlete.Competence;
import Model.Athlete.Competencia;
import Model.Race.AthleteRaceInformation;
import Model.Race.Race;

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
												.filter(athlete -> athlete instanceof Competence)
												.collect(Collectors.toList());
			setInfo(filterRace);   
		}
	});
	buttonGroup.add(chckbxNewCheckBox);
	chckbxNewCheckBox.setBounds(58, 15, 97, 21);
	//add(chckbxNewCheckBox);
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
	//add(chckbxNewCheckBox_1);
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
	//add(chckbxAmateur);
	panel_1.add(chckbxAmateur);
	
}

	private void setInfo(List<Athlete> athletes) {
	/*
		Listado de atletas conteniendo Nombre, nacionalidad, cantidad de etapas ganadas
		en cada disciplina en el campeonato, cantidad de carreras ganadas, cantidad de
		abandonos, cantidad de carreras finalizadas.
	*/
		tableModel.setRowCount(0);
		Competencia lastCompetencia;
		float bestTime = 0 ;
		
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
