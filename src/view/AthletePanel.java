package view;

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
		setLayout(null);
		setBounds(5, 5+70*position, 1050,70);
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
		
		lblAthlete = new JLabel("Athlete:");
		lblAthlete.setBounds(10, 4, 70, 20);
		add(lblAthlete);
		
		lblEnergy = new JLabel("Energy:");
		lblEnergy.setBounds(10, 45, 70, 20);
		add(lblEnergy);
		
		lblDistance = new JLabel("");
		lblDistance.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDistance.setBounds(50, 21, 32, 32);
		lblDistance.setIcon(new ImageIcon("img\\nadando.png"));
		add(lblDistance);
		
		/*Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			int fila1=37;
			@Override
			public void run() {
				String actual;
				actual = "nadando";
				fila1 +=4;
				lblDistance.setLocation(fila1, 21);
				
				if(((333<fila1)&&(fila1<666))&&(actual!="ciclismo")) {
					lblDistance.setIcon(new ImageIcon("img\\ciclismo.png"));
					actual ="ciclismo";
				}else if(((fila1>666)&&(fila1<999)) && (actual != "capacitacion"))
						lblDistance.setIcon(new ImageIcon("img\\capacitacion.png"));
						else if(fila1==1000)
							  timer.cancel();
				repaint();
				
			}}, 0, 100); */
	}

	

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawLine(55, 37, this.getWidth(), 37);//Race line
		g.setColor(new Color(255,0,0));
		g.drawLine(this.getWidth()/3, 0, this.getWidth()/3, 75);//Transition 1 line
		g.drawLine(this.getWidth()/3*2, 0, this.getWidth()/3*2, 75);//Transition 2 line
		g.setColor(new Color(0,255,0));
		for(int i = 1; i <= this.listPrivisioning.size(); i++) {
			Integer mapKey = Integer.valueOf(i);
			if (listPrivisioning.get(mapKey).getDiscipline() instanceof Cycling ) {
				float relation = (modality.getCycling().getDistance() / this.listPrivisioning.get(mapKey).getDistance());
				g.drawLine((int)(this.getWidth()/3*relation), 0, (int)(this.getWidth()/3*relation), 75);
			} else if(this.listPrivisioning.get(mapKey).getDiscipline() instanceof Pedestrianism) {
				float relation = (modality.getCycling().getDistance() / this.listPrivisioning.get(mapKey).getDistance());
				g.drawLine((int)(this.getWidth()/3 + this.getWidth()/3*relation), 0, (int)(this.getWidth()/3+ this.getWidth()/3*relation), 75);
			}
			//g.drawLine((int) this.listPrivisioning.get(mapKey).getDistance(), 0, (int) this.listPrivisioning.get(mapKey).getDistance(), 75);
		}
	}
	
	public void advance(AthletePanel panel, float advancedDistance) {
		panel.getLblDistance().setLocation((int)advancedDistance, 21);
		panel.repaint();
		String actual;
		actual = "nadando";
		if(((345<(int)advancedDistance)&&((int)advancedDistance<345*2))&& actual!="ciclismo") {
			lblDistance.setIcon(new ImageIcon("img\\ciclismo.png"));
			actual ="ciclismo";
		}		else if((((int)advancedDistance>345*2)&&((int)advancedDistance<345*3)) && (actual != "capacitacion"))
					lblDistance.setIcon(new ImageIcon("img\\capacitacion.png"));
						//else if((int)advancedDistance==1000);
	}
	
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
