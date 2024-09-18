package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import Model.Discipline.Provisioning;
import Model.Race.AthleteRaceInformation;

public class RaceView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel racePanel;
	//private AthletePanel athletePanel;
	
	public RaceView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 	setTitle("Race");
		setBounds(100, 100, 1410, 1000);
		setExtendedState(MAXIMIZED_BOTH);
		
		contentPane = new JPanel();
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblRaceTime = new JLabel("Race Time:");
		lblRaceTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRaceTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblRaceTime.setIcon(new ImageIcon("img\\gestion-del-tiempo.png"));
		lblRaceTime.setBounds(72, 84, 263, 41);
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
		
		JLabel lblClimateCondition = new JLabel("Climate condition:");
		lblClimateCondition.setIcon(new ImageIcon("img\\nublado.png"));
		lblClimateCondition.setHorizontalAlignment(SwingConstants.CENTER);
		lblClimateCondition.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblClimateCondition.setBounds(120, 35, 203, 39);
		contentPane.add(lblClimateCondition);
		
		JLabel lblTitulo = new JLabel("RACE:");
		lblTitulo.setFont(new Font("Montserrat Black", Font.BOLD, 50));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(465, 6, 466, 78);
		contentPane.add(lblTitulo);
		
		JButton btnExitRace = new JButton("Exit");
		btnExitRace.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnExitRace.setBounds(1217, 742, 109, 37);
		btnExitRace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnExitRace);
		
		racePanel = new JPanel();
		racePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		racePanel.setBounds(233, 135, 1056, 580);
		contentPane.add(racePanel);
	}
	
	public void initializePanels(AthleteRaceInformation athlete, int startPosition, Map <Integer, Provisioning> listPrivisioning) {
		AthletePanel athletePanel = new AthletePanel(startPosition, listPrivisioning, athlete.getModality());
		athlete.setPanel(athletePanel);
		racePanel.add(athletePanel);	
	}
}
