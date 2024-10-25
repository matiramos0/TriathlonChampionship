package Model.View;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;

import Controller.Championship;
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
	private Map <Integer, Provisioning> provisioningCycling;
	private Map <Integer, Provisioning> provisioningPedestrianism;
	private Modality modality;
	
	public AthletePanel(int startingPosition, Map <Integer, Provisioning> provisioningCycling, Map <Integer, Provisioning>provisioningPedestrianism,Modality modality) {
		setLayout(null);
		setBounds(281, 73 + 70*startingPosition, 1056, 70);

		this.provisioningCycling = provisioningCycling;
		this.provisioningPedestrianism = provisioningPedestrianism;
		this.modality = modality;
		
		spinnerSpeed = new JSpinner();
		spinnerSpeed.setBounds(10, 27, 40, 20);
		spinnerSpeed.setModel( new SpinnerNumberModel(1, 0, 5, 1));
		add(spinnerSpeed);
		
		lblAthlete = new JLabel();
		lblAthlete.setBounds(10, 4, 150, 20);
		add(lblAthlete);
		
		lblEnergy = new JLabel("Energy:");
		lblEnergy.setBounds(10, 45, 150, 20);
		add(lblEnergy);
		
		lblDistance = new JLabel("");
		lblDistance.setHorizontalAlignment(SwingConstants.LEFT);
		lblDistance.setBounds(56, 21, 32, 32);
		lblDistance.setIcon(new ImageIcon("img\\nadando.png"));
		add(lblDistance);
		
	}

	
	@Override
	public void paint(Graphics g) {
		int transition1 = (this.getWidth()-56)/3 +56; //-56 porque ahi arranca el lblDistance (Recorre la carrera)
		int transition2 = (this.getWidth()-56)/3 *2 +56;
		super.paint(g);
		g.setColor((Color.BLACK));
		g.drawLine(56, 37, this.getWidth(), 37);//Race line
		g.setColor(Color.RED);
		g.drawLine(transition1, 0, transition1, 75);
		g.drawLine(transition2, 0, transition2, 75);
		g.setColor(Color.GREEN);
		
		for (int i = 1; i <= this.provisioningCycling.size(); i++) {
			Integer mapKey = Integer.valueOf(i); 
		//Pinta lineas de aprovisionamiento en relacion a la linea de transicion (en pixels)
			float relation = (modality.getCycling().getDistance() / this.provisioningCycling.get(mapKey).getDistance());
			g.drawLine((int) (transition1 + ((this.getWidth()-56)/3) / relation), 0,
					   (int) (transition1 + ((this.getWidth()-56)/3) / relation), 75);
	
		}
		
		for (int i = 1; i <= this.provisioningPedestrianism.size(); i++) {
			Integer mapKey = Integer.valueOf(i); 
		//Pinta lineas de aprovisionamiento en relacion a la linea de transicion (en pixels)
			float relation = (modality.getPedestrianism().getDistance() / this.provisioningPedestrianism.get(mapKey).getDistance());
			g.drawLine((int) (transition2 + ((this.getWidth()-56)/3) / relation), 0, 
					   (int) (transition2 + ((this.getWidth()-56)/3) / relation), 75);
		
		}
	}
	
	public synchronized void advance(float advancedDistance) {
		int panelDist = (this.getWidth() - 56) / 3; //-56 porque ahi arranca el lblDistance (Recorre la carrera)
		int lblDimensionDistance = 56;

		float t1 = modality.getFirstTransition();
		float t2 = modality.getSecondTransition();
		
	//Divide la distancia en pixeles de cada disciplina con la distancia real y obtiene la relacion para representar las distancias correctamente
		float swimmingEqual = panelDist / modality.getSwimming().getDistance();
		float cyclingEqual = panelDist / modality.getCycling().getDistance();
		float pedestrianismEqual = panelDist / modality.getPedestrianism().getDistance();

		if (advancedDistance < t1)
			lblDimensionDistance += (int) (advancedDistance * swimmingEqual);
		else if (advancedDistance < t2)
			lblDimensionDistance += 333 + (int) ((advancedDistance - t1) * cyclingEqual);
		else if (advancedDistance < modality.getTotalDistance())
			lblDimensionDistance += 666 + (int) ((advancedDistance - t2) * pedestrianismEqual);

		if (advancedDistance < modality.getTotalDistance())
			this.lblDistance.setLocation(lblDimensionDistance, 21);
		else {
			this.lblDistance.setIcon(new ImageIcon("img\\bandera-de-meta.png"));
			this.lblDistance.setLocation(this.getWidth() - 32, 21);
		}

		int transitionLine1 = panelDist + 56;
		int transitionLine2 = panelDist * 2 + 56;

		String actual = "nadando"; //Swimming Icono(Iniciada en constructor)
		//Cuando pasa una transicion y solo la primer vez que lo hace cambia el Icono
		if (((transitionLine1 < lblDimensionDistance) && (lblDimensionDistance < transitionLine2)) && actual != "ciclismo") {
			this.lblDistance.setIcon(new ImageIcon("img\\ciclismo.png"));
			actual = "ciclismo";
		} else if ((lblDimensionDistance > transitionLine2) && (actual != "capacitacion")) {
			this.lblDistance.setIcon(new ImageIcon("img\\capacitacion.png"));
			actual = "capacitacion";
		}
	}
	
	public synchronized void refreshPositions(int position, Boolean isOut) {
		this.setBounds(281, 73 + 70 * position, 1056, 70);
		if (position <= 8) {
			if (isOut.equals(true))
				this.lblDistance.setIcon(new ImageIcon("img\\cerrar.png"));
			this.setVisible(true);
			this.repaint();
		} else {
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
