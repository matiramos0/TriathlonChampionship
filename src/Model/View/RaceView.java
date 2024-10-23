package Model.View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
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
import Model.Race.Race;
import Persistence.Persistence;

import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

public class RaceView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblClimateCondition;
	private JLabel lblRaceTime;
	private JLabel lblTitulo;
	private Ranking ranking;
	private final ButtonGroup buttonGroupPause = new ButtonGroup();
	private JRadioButton rdbtnPause;
	private JRadioButton rdbtnResume;
	private JButton btnSerialize;
	
	public RaceView(String titleRace) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 	setTitle("Race");
		setBounds(100, 100, 1410, 1000);
		setExtendedState(MAXIMIZED_BOTH);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(41, 28, 328, 106);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		lblRaceTime = new JLabel("Race Time:");
		lblRaceTime.setBounds(6, 55, 202, 41);
		panel_1.add(lblRaceTime);
		lblRaceTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRaceTime.setHorizontalAlignment(SwingConstants.LEFT);
		lblRaceTime.setIcon(new ImageIcon("img\\gestion-del-tiempo.png"));
		
		lblClimateCondition = new JLabel("Climate condition:");
		lblClimateCondition.setBounds(6, 15, 322, 39);
		panel_1.add(lblClimateCondition);
		lblClimateCondition.setIcon(new ImageIcon("img\\nublado.png"));
		lblClimateCondition.setHorizontalAlignment(SwingConstants.LEFT);
		lblClimateCondition.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_2 = new JLabel("SWIMMING");
		lblNewLabel_2.setIcon(new ImageIcon("img\\nadando.png"));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(431, 111, 118, 29);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("CYCLISM");
		lblNewLabel_2_1.setIcon(new ImageIcon("img\\ciclismo.png"));
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(782, 111, 118, 29);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("PEDESTRISM");
		lblNewLabel_2_2.setIcon(new ImageIcon("img\\capacitacion.png"));
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_2.setBounds(1100, 106, 138, 39);
		contentPane.add(lblNewLabel_2_2);
		
		lblTitulo = new JLabel("RACE: " + titleRace);
		lblTitulo.setFont(new Font("Montserrat Black", Font.BOLD, 50));
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setBounds(520, 6, 846, 78);
		contentPane.add(lblTitulo);	
		
		JButton btnExitRace = new JButton("Exit");
		//btnExitRace.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnExitRace.setBounds(41, 273, 187, 57);
		btnExitRace.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(DISPOSE_ON_CLOSE);
				//try {
				//	controller.listenPauseRace();
				//} catch (InterruptedException e1) {
				//}
			}
		});
		contentPane.add(btnExitRace);
		
		JButton btnStartRace = new JButton("Start");
		btnStartRace.setBounds(41, 469, 187, 57);
		btnStartRace.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					Championship.getInstance().listenStartRace();
					buttonGroupPause.setSelected(rdbtnResume.getModel(), true);
			}
		});
		contentPane.add(btnStartRace);
		
		JButton btnNewButton = new JButton("Championship \r\nStatistics");
		btnNewButton.setBounds(41, 368, 187, 62);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Championship.getInstance().listenShowCurrentRanking();
			}
			
		});
		contentPane.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(64, 64, 64), Color.BLACK, new Color(192, 192, 192), null));
		panel.setBounds(41, 160, 199, 85);
		contentPane.add(panel);
		panel.setLayout(null);
		
		rdbtnPause = new JRadioButton("Pause Race");
		rdbtnPause.setBounds(6, 15, 187, 21);
		panel.add(rdbtnPause);
		rdbtnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
						Championship.getInstance().listenInterruptRace(true);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
			}
		});
		buttonGroupPause.add(rdbtnPause);
		
		rdbtnResume = new JRadioButton("Resume Race");
		rdbtnResume.setBounds(6, 52, 187, 21);
		panel.add(rdbtnResume);
		rdbtnResume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
						Championship.getInstance().listenInterruptRace(false);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		buttonGroupPause.add(rdbtnResume);
		
		btnSerialize = new JButton("Serialize Race");
		btnSerialize.setEnabled(false);
		btnSerialize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Persistence.championshipPersistence(Championship.getInstance());
			}
		});
		
		btnSerialize.setBounds(41, 540, 187, 62);
		contentPane.add(btnSerialize);}

		public void listenFinishRace() {
		    if (Championship.getInstance().FinishedRace()) {
		        btnSerialize.setEnabled(true);
		    }
	}
	 
//	Methods    
	
	/*public void seeRanking(List<Race> finishedRaces, Race race) {
		ranking = new Ranking();
		ranking.showRaceRanking(finishedRaces, race);
		ranking.setVisible(true);
		ranking.setLocationRelativeTo(null);		
	}*/
	
	public Boolean askNewRace() {
		if (JOptionPane.showConfirmDialog(null, "Do you want to start the following race? ", "Championship", JOptionPane.OK_CANCEL_OPTION) == 0)
			return true;
		else
			return false;
	}
	
	public void pause() {
	 	rdbtnPause.doClick();
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
