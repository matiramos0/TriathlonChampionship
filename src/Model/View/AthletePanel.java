package Model.View;

import javax.swing.JPanel;
import javax.swing.JSpinner;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;

import Model.Discipline.Cycling;
import Model.Discipline.Pedestrianism;
import Model.Discipline.Provisioning;
import Model.Modality.Modality;
import Model.Race.AthleteRaceInformation;

import javax.swing.event.ChangeEvent;
import javax.swing.SwingConstants;

public class AthletePanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JLabel lblAthlete;
	private JLabel lblEnergy;
	private JLabel lblDistance;
	private JSpinner spinnerSpeed;
	//private int position;  in athleteRace
	private Map <Integer, Provisioning> listPrivisioning;
	private Modality modality;
	
	public AthletePanel(int startingPosition, Map <Integer, Provisioning> listProvisioning, Modality modality) {
		setLayout(null);
		setBounds(256, 73 + 70*startingPosition, 1056, 70);

		this.listPrivisioning = listProvisioning;
		this.modality = modality;
		
		spinnerSpeed = new JSpinner();
		spinnerSpeed.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//Event of speed altered
				}
		});
		spinnerSpeed.setBounds(10, 27, 40, 20);
		add(spinnerSpeed);
		
		lblAthlete = new JLabel();
		lblAthlete.setBounds(10, 4, 150, 20);
		add(lblAthlete);
		
		lblEnergy = new JLabel("Energy:");
		lblEnergy.setBounds(10, 45, 150, 20);
		add(lblEnergy);
		
		lblDistance = new JLabel("");
		lblDistance.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDistance.setBounds(56, 21, 32, 32);
		lblDistance.setIcon(new ImageIcon("img\\nadando.png"));
		add(lblDistance);
		
	}

	
	@Override
	public void paint(Graphics g) {
		int transition1 = (this.getWidth()-56)/3;//-56 due to the start of the lbldistance
		int transition2 = transition1*2;
		super.paint(g);
		g.setColor((Color.BLACK));
		g.drawLine(56, 37, this.getWidth(), 37);//Race line
		g.setColor(Color.RED);
		g.drawLine(transition1, 0, transition1, 75);
		g.drawLine(transition2, 0, transition2, 75);
		g.setColor(Color.GREEN);
		for(int i = 1; i <= this.listPrivisioning.size(); i++) {
			Integer mapKey = Integer.valueOf(i);
			if (listPrivisioning.get(mapKey).getDiscipline() instanceof Cycling ) {
				float relation = (modality.getCycling().getDistance() / this.listPrivisioning.get(mapKey).getDistance());//Position of the provision line in relation to the transition line(in pixels)
				g.drawLine((int)(transition1 + transition1/relation), 0, (int)(transition1 + transition1/relation), 75);
			} else if(this.listPrivisioning.get(mapKey).getDiscipline() instanceof Pedestrianism) {
				float relation = (modality.getCycling().getDistance() / this.listPrivisioning.get(mapKey).getDistance());
				g.drawLine((int)((transition2 + transition2/relation)), 0, (int)((transition2 + transition2/relation)), 75);
			}
		}
			
	}
	
	public synchronized void advance(float advancedDistance) {
		int panelDist = (this.getWidth()-56)/3;//-56 due to the start of the lbldistance
		int lblDimensionDistance = 0;
		
		float t1 = modality.getFirstTransition();
		float t2 = modality.getSecondTransition(); 
		float swimmingEqual = panelDist / modality.getSwimming().getDistance();
		float cyclingEqual = panelDist / modality.getCycling().getDistance();  
		float pedestrianismEqual = panelDist / modality.getPedestrianism().getDistance();  

		if (advancedDistance < t1)
			 lblDimensionDistance += (int)(advancedDistance*swimmingEqual);  //Swimming
		else if(advancedDistance < t2)
			 	lblDimensionDistance += (int)(333 + (advancedDistance-t1)*cyclingEqual);//Cycling
			else 
				lblDimensionDistance += (int)(666 + (advancedDistance-(t1+t1))*pedestrianismEqual);//Pedestrianism
		
		this.lblDistance.setLocation(lblDimensionDistance, 21);

		int transitionLine1 = panelDist;
		int transitionLine2 = panelDist* 2;
		
		String actual;
		actual = "nadando";
		if(((transitionLine1<lblDimensionDistance)&&(lblDimensionDistance<transitionLine2))&& actual!="ciclismo") {
			this.lblDistance.setIcon(new ImageIcon("img\\ciclismo.png"));
			actual ="ciclismo";
			}	else if(((lblDimensionDistance > transitionLine2) && (actual != "capacitacion"))) {
						this.lblDistance.setIcon(new ImageIcon("img\\capacitacion.png"));
						actual = "capacitacion";
						}
	}
	
	public synchronized void refreshPositions(int position) {
		//this.setBounds(256, 73 + 70*position, 1056, 70);
		this.setAlignmentY(73 + 70* position);
		if(position <= 8) {
			this.setVisible(true);
			this.repaint();
		} else {			      // show only first 8 athletes
			  this.setVisible(false);
		}
	}
	
	//Getters and Setters
	public JLabel getLblDistance() {
		return lblDistance;
	}

	public void setLblDistance(JLabel lblDistance) {
		this.lblDistance = lblDistance;
	}
	
	public JLabel getLblAthlete() {
		return lblAthlete;
	}

	public void setLblAthlete(JLabel lblAthlete) {
		this.lblAthlete = lblAthlete;
	}

	public JLabel getLblEnergy() {
		return lblEnergy;
	}

	public void setLblEnergy(JLabel lblEnergy) {
		this.lblEnergy = lblEnergy;
	}

	public JSpinner getSpinnerSpeed() {
		return spinnerSpeed;
	}

	public void setSpinnerSpeed(JSpinner spinnerSpeed) {
		this.spinnerSpeed = spinnerSpeed;
	}



}
