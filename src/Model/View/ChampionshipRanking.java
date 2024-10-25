package Model.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
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
import Model.Athlete.Competition;
import Model.Athlete.Competence;
import Model.Athlete.Athlete.Gender;
import Model.Race.Race;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ChampionshipRanking extends JPanel {

private static final long serialVersionUID = 1L;

private DefaultTableModel tableModel;
private JTable table;
private JScrollPane scroll;
private final ButtonGroup buttonGroupFilter = new ButtonGroup();
private JComboBox<String> comboBoxRace;
private JCheckBox chckbxAll;
private List<Athlete> athletes;
private JCheckBox chckbxSortChampionshipPosition;

	public ChampionshipRanking(List<Athlete> athletesList) {
		setVisible(true);
		this.athletes = athletesList;
		
		scroll = new JScrollPane();
		scroll.setBounds(10, 68, 1300, 538);
		add(scroll);		
		
		tableModel = new DefaultTableModel(){
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false; 
	        }
	    };	  
		tableModel.addColumn("Athlete Name");
		tableModel.addColumn("Championship Position");
		tableModel.addColumn("Race");
		tableModel.addColumn("Race Position");
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
	    
	    table.getColumnModel().getColumn(2).setPreferredWidth(130);
	    
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Filter By", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(75, 10, 385, 48);
		add(panel_1);
		panel_1.setLayout(null);
		
		chckbxAll = new JCheckBox("All");
		chckbxAll.setBounds(6, 15, 50, 21);
		chckbxAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setInfo(athletes, comboBoxRace.getSelectedIndex());   
			}
		});
		chckbxAll.setSelected(true);
		buttonGroupFilter.add(chckbxAll);	
		panel_1.add(chckbxAll);
		
		
	    //Inicio Panel contenedor de titulo "Sort By"
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Sort By", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(486, 3, 174, 65);
		panel.setLayout(null);
		add(panel);
		
	    ButtonGroup sortGroup = new ButtonGroup();

		JCheckBox chckbxSortName = new JCheckBox("Name");
		chckbxSortName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Collections.sort(athletes, new Comparator<Athlete>() {
					@Override
					public int compare(Athlete o1, Athlete o2) {
						return o1.getName().compareTo(o2.getName());
					}
				});
				
				chckbxAll.setSelected(true);
				setInfo(athletes, comboBoxRace.getSelectedIndex());
			}
		});
		chckbxSortName.setBounds(6, 15, 107, 21);
		panel.add(chckbxSortName);
		sortGroup.add(chckbxSortName);
		
		chckbxSortChampionshipPosition = new JCheckBox("Championship Position");
		chckbxSortChampionshipPosition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Collections.sort(athletes, new Comparator<Athlete>() {
					@Override
					public int compare(Athlete o1, Athlete o2) {
						return o1.getChampionshipPosition() - o2.getChampionshipPosition();
					}
				});
				
				chckbxAll.setSelected(true);
				setInfo(athletes, comboBoxRace.getSelectedIndex());
			}
		});
		chckbxSortChampionshipPosition.setBounds(6, 38, 150, 21);
		panel.add(chckbxSortChampionshipPosition);
		sortGroup.add(chckbxSortChampionshipPosition);
	    //Fin Panel contenedor de titulo "Sort By"

		
	    JLabel lblSelectRace = new JLabel("Select Race");
		lblSelectRace.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelectRace.setBounds(670, 10, 126, 20);
		add(lblSelectRace);
		
		comboBoxRace = new JComboBox<String>();
		comboBoxRace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckbxAll.doClick();
			}
		});
		comboBoxRace.setBounds(670, 37, 151, 21);
		add(comboBoxRace);
		
		//Carga las Competence(carreras) terminadas al comboBox 
		for(Competence comp : athletes.getFirst().getChampionshipInformation()){
			comboBoxRace.addItem(comp.getRace().toString());
		}

		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Competence");
		chckbxNewCheckBox.setBounds(58, 15, 97, 21);
		panel_1.add(chckbxNewCheckBox);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Athlete> filterRace = athletes.stream()
													.filter(athlete -> athlete instanceof Competition)
													.collect(Collectors.toList());
				setInfo(filterRace, comboBoxRace.getSelectedIndex());   
			}
		});
		buttonGroupFilter.add(chckbxNewCheckBox);
		
		JCheckBox chckbxAmateur = new JCheckBox("Amateur");
		chckbxAmateur.setBounds(157, 15, 77, 21);
		panel_1.add(chckbxAmateur);
		chckbxAmateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Athlete> filterList = athletes.stream()
													.filter(athlete -> athlete instanceof Amateur)
											        .collect(Collectors.toList());
				setInfo(filterList, comboBoxRace.getSelectedIndex());   
			}
		});
		buttonGroupFilter.add(chckbxAmateur);
		
		JCheckBox chckbxMale = new JCheckBox("Male");
		chckbxMale.setBounds(236, 15, 63, 21);
		panel_1.add(chckbxMale);
		chckbxMale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Athlete> filterList = athletes.stream()
													.filter(athlete -> athlete.getGender().equals(Gender.MALE))
													.collect(Collectors.toList());
				setInfo(filterList, comboBoxRace.getSelectedIndex());   
			}
		});
		buttonGroupFilter.add(chckbxMale);
	
		
		JCheckBox chckbxFemale = new JCheckBox("Female");
		chckbxFemale.setBounds(301, 15, 78, 21);
		panel_1.add(chckbxFemale);
		chckbxFemale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Athlete> filterList = athletes.stream()
													.filter(athlete -> athlete.getGender().equals(Gender.FEMALE))
													.collect(Collectors.toList());
				setInfo(filterList, comboBoxRace.getSelectedIndex());   
			}
		});
		buttonGroupFilter.add(chckbxFemale);
			
		
		chckbxSortChampionshipPosition.doClick();
	    //setInfo(athletes, comboBoxRace.getSelectedIndex());
	    
	}

		private void setInfo(List<Athlete> athletes, int competenciaIndex) {
			tableModel.setRowCount(0);
			float bestTime = 0;
			Competence competencia;
			//int athleteIndex = 0;
			/*
			   Grilla mostrando en las columnas la posición en el
				campeonato, nombre y, para cada carrera de la temporada, la posición en la carrera
				y los tiempos en cada disciplina. Se debe poder ordenar la grilla por posición en el
				campeonato o por orden alfabético
			 */

			for (Athlete a : athletes) {
				//athleteIndex++;
				
				Object[] row = new Object[tableModel.getColumnCount()];
				competencia = a.getChampionshipInformation().get(competenciaIndex);

				row[0] = a.getName();
				row[1] = a.getChampionshipPosition(); 
				row[2] = competencia.getRace().getCity().getDescription()+": "+competencia.getRace().getModality().getModalities().name();
				row[3] = competencia.getPosition(); 

				for (int i = 0; i < competencia.getDistances().size(); i++) 
						row[4 + i] = competencia.getDistances().get(i).getTime();		
				
					if (a.equals(athletes.getFirst()) && competencia.getDistances().getLast().getDistance()
														>competencia.getRace().getModality().getTotalDistance()){
					 //Si es el primero y termino la carrera:																					
						 bestTime = competencia.getTotalTime();
						 row[tableModel.getColumnCount() - 2] = bestTime;
					} else if (competencia.getDistances().getLast().getDistance() > competencia.getRace().getModality().getTotalDistance())// si termino la carrera
								row[tableModel.getColumnCount() - 2] = bestTime - competencia.getTotalTime();
				
				if(competencia.isAbandon())
					row[tableModel.getColumnCount() - 1] = competencia.getDistances().getLast().getDiscipline().getDescription();
						  
				tableModel.addRow(row);
		
			}
		}
}
