package Model.View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import Controller.Championship;
import Model.Race.Race;
import Model.View.BackgroundMainView;

import javax.swing.border.EtchedBorder;

public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//private static RaceView raceView;
	//private Championship controller;
	 
	public MainView(Championship controller) {
		//this.controller = controllerr;
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\Triatlon background.png"));
		setTitle("Triathlon Championship");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		
		contentPane = new BackgroundMainView();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("TRIATHLON CHAMPIONSHIP");
		lblNewLabel.setBounds(29, 71, 832, 64);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setFont(new Font("Montserrat Black", Font.BOLD, 50));	
				
		JButton btnClose = new JButton("Exit");
		btnClose.setBounds(338, 419, 167, 86);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				}
		});
		btnClose.setFont(new Font("Montserrat Medium", Font.PLAIN, 20));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(810, 475, 76, 78);
		lblNewLabel_1.setIcon(new ImageIcon("img\\triatlon.png"));
		contentPane.setLayout(null);
		contentPane.add(btnClose);
		contentPane.add(lblNewLabel_1);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Start new championship");
		btnNewButton.setBounds(448, 322, 350, 83);
		btnNewButton.setFont(new Font("Montserrat Medium", Font.PLAIN, 20));
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.listenStartNewChampionship();
				//RaceView frame = new RaceView();
				//frame.setExtendedState(MAXIMIZED_BOTH);
				//frame.setVisible(true);
				//frame.setLocationRelativeTo(null);
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnContinuechampionship = new JButton("Continue saved championship");
		btnContinuechampionship.setHorizontalAlignment(SwingConstants.LEFT);
		btnContinuechampionship.setBounds(29, 322, 350, 83);
		contentPane.add(btnContinuechampionship);
		btnContinuechampionship.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnContinuechampionship.setFont(new Font("Montserrat Medium", Font.PLAIN, 20));
	
	
	}

}
