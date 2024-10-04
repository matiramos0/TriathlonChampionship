package Model.View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import Controller.Championship;
import Model.Discipline.Provisioning;
import Model.Race.AthleteRaceInformation;
import javax.swing.JTabbedPane;

public class RaceView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblClimateCondition;
	private JLabel lblRaceTime;
	private JLabel lblTitulo;
	private Championship controller;
	
	public RaceView(String titulo, Championship controller) {
		this.controller = controller;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 	setTitle("Race");
		setBounds(100, 100, 1410, 1000);
		setExtendedState(MAXIMIZED_BOTH);
		
		contentPane = new JPanel();
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		lblRaceTime = new JLabel("Race Time:");
		lblRaceTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRaceTime.setHorizontalAlignment(SwingConstants.LEFT);
		lblRaceTime.setIcon(new ImageIcon("img\\gestion-del-tiempo.png"));
		lblRaceTime.setBounds(65, 84, 202, 41);
		contentPane.add(lblRaceTime);
		
		JLabel lblNewLabel_2 = new JLabel("SWIMMING");
		lblNewLabel_2.setIcon(new ImageIcon("img\\nadando.png"));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(363, 111, 118, 29);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("CYCLISM");
		lblNewLabel_2_1.setIcon(new ImageIcon("img\\ciclismo.png"));
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(720, 111, 118, 29);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("PEDESTRISM");
		lblNewLabel_2_2.setIcon(new ImageIcon("img\\capacitacion.png"));
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_2.setBounds(1049, 106, 138, 39);
		contentPane.add(lblNewLabel_2_2);
		
		lblClimateCondition = new JLabel("Climate condition:");
		lblClimateCondition.setIcon(new ImageIcon("img\\nublado.png"));
		lblClimateCondition.setHorizontalAlignment(SwingConstants.LEFT);
		lblClimateCondition.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblClimateCondition.setBounds(65, 35, 344, 39);
		contentPane.add(lblClimateCondition);
		
		lblTitulo = new JLabel("RACE: " + titulo);
		lblTitulo.setFont(new Font("Montserrat Black", Font.BOLD, 50));
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setBounds(520, 6, 846, 78);
		contentPane.add(lblTitulo);	
		
		JButton btnExitRace = new JButton("Exit");
		//btnExitRace.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnExitRace.setBounds(41, 246, 187, 57);
		btnExitRace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnExitRace);
		
		JButton btnNewButton = new JButton("Championship \r\nStatistics");
		btnNewButton.setBounds(41, 350, 187, 62);
		contentPane.add(btnNewButton);
		
		
	}
	/*
	public void initializePanels(AthleteRaceInformation athlete, int startPosition, Map <Integer, Provisioning> listPrivisioning) {

		AthletePanel athletePanel = new AthletePanel(startPosition, listPrivisioning, athlete.getModality());
		athlete.setPanel(athletePanel);
		
		athletePanel.getLblAthlete().setText("Athlete: "+athlete.getAthlete().getName());
		athletePanel.setVisible(true);
		contentPane.add(athletePanel);
	}
	 
	 */
	public Boolean askNewRace() {
		if (JOptionPane.showConfirmDialog(null, "Do you want to start the following race? ", "Championship", JOptionPane.OK_CANCEL_OPTION) == 0)
			return true;
		else
			return false;
	}
	
	//Getters and Setters
	 public JPanel getContentPane() {
		return contentPane;
	}
	 /*
	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
	}
	*/
	public JLabel getLblClimateCondition() {
		return lblClimateCondition;
	}

	public void setLblClimateCondition(JLabel lblClimateCondition) {
		this.lblClimateCondition = lblClimateCondition;
	}

	public JLabel getLblRaceTime() {
		return lblRaceTime;
	}

	public void setLblRaceTime(JLabel lblRaceTime) {
		this.lblRaceTime = lblRaceTime;
	}

	public JLabel getLblTitulo() {
		return lblTitulo;
	}

	public void setLblTitulo(JLabel lblTitulo) {
		this.lblTitulo = lblTitulo;
	}
}
