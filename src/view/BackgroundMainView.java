package view;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundMainView extends JPanel{

	private static final long serialVersionUID = 1L;
	private ImageIcon img;
	
	public BackgroundMainView() {
		img = new ImageIcon("img\\Triathlon Wallpaper.png");
	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), null);
		setOpaque(false);
		super.paintChildren(g);
	}
}