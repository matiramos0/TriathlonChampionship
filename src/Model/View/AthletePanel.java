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

import javax.swing.event.ChangeEvent;
import javax.swing.SwingConstants;

public class AthletePanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JLabel lblAthlete;
	private JLabel lblEnergy;
	private JLabel lblDistance;
	private JSpinner spinnerSpeed;
	private int position;
	private Map <Integer, Provisioning> listPrivisioning;
	private Modality modality;
	
	public AthletePanel(int position, Map <Integer, Provisioning> listProvisioning, Modality modality) {
		this.position = position;
		setLayout(null);
		setBounds(256, 73 + 70*position, 1056, 70);
		//setBounds(5, 5+70*position, 1050,70);
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
		lblDistance.setBounds(50, 21, 32, 32);
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
		g.drawLine(transition1, 0, transition1, 75);//Transition 1 line
		g.drawLine(transition2, 0, transition2, 75);//Transition 2 line
		g.setColor(Color.GREEN);
		for(int i = 1; i <= this.listPrivisioning.size(); i++) {
			Integer mapKey = Integer.valueOf(i);
			if (listPrivisioning.get(mapKey).getDiscipline() instanceof Cycling ) {
				float relation = (modality.getCycling().getDistance() / this.listPrivisioning.get(mapKey).getDistance());//Position of the provision line in relation to the transition line(in pixels)
				g.drawLine((int)(transition1 + transition1/relation), 0, (int)(transition1 + transition1/relation), 75);
			} else if(this.listPrivisioning.get(mapKey).getDiscipline() instanceof Pedestrianism) {
				float relation = (modality.getCycling().getDistance() / this.listPrivisioning.get(mapKey).getDistance());
				g.drawLine((int)((transition2 + transition2/relation)), 0, (int)((transition2 + transition2/relation)), 75);// - 50 porque ahi arranca el label a correr
			}
		}
			
	}
	
	public void advance(/*AthletePanel panel,*/ float advancedDistance) {
		
		this.lblDistance.setLocation(50 + (int)(advancedDistance*35), 21);
		this.repaint();

		if(this.position <= 8) {
			this.setVisible(true);
		} else {			      // show only first 8 athletes
			  this.setVisible(false);
		}
		int transition1 = (this.getWidth()-56)/3;//-56 is due to the start of the lbldistance
		int transition2 = transition1* 2;
		String actual;
		actual = "nadando";
		if(((transition1<(int)advancedDistance)&&((int)advancedDistance<transition2))&& actual!="ciclismo") {
			this.lblDistance.setIcon(new ImageIcon("img\\ciclismo.png"));
			actual ="ciclismo";
		}		else if((((int)advancedDistance > transition2) && (actual != "capacitacion"))) {
						this.lblDistance.setIcon(new ImageIcon("img\\capacitacion.png"));
						actual = "capacitacion";
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
