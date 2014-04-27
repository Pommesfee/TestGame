package core;

//import java.awt.DisplayMode;
//import java.awt.GraphicsDevice;
//import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

import gameobjects.Player;
import gui.Background;
import gui.Frame;

public class Main {
	
	public static void main(String[] args) {
		
		Player player = new Player(400, 300, 300, 800, 600);
		Background bg = new Background(500);
		
		Frame frame = new Frame(player, bg);
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
//		DisplayMode displayMode = new DisplayMode(800, 600, 16, 75);
//		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
//		GraphicsDevice device = environment.getDefaultScreenDevice();
//		
//		device.setFullScreenWindow(frame);
//		device.setDisplayMode(displayMode);
		
		long lastFrame = System.currentTimeMillis();
		while (true) {
			long thisFrame = System.currentTimeMillis();
			double timeSinceLastFrame = (double) (thisFrame - lastFrame) / 1000d;
			lastFrame = thisFrame;
			
			player.update(timeSinceLastFrame, frame.getUp(), frame.getDown(), frame.getLeft(), frame.getRight());
			bg.update(timeSinceLastFrame);
			
			frame.repaintScreen();
			
		}
	}
	
}
