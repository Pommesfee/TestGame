package core;

//import java.awt.DisplayMode;
//import java.awt.GraphicsDevice;
//import java.awt.GraphicsEnvironment;

import gameobjects.Bullet;
import gameobjects.Enemy;
import gameobjects.Player;
import gui.Background;
import gui.Frame;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

public class Main {
	
	private static List<Bullet> bullets = new LinkedList<Bullet>();
	private static List<Enemy> enemies = new LinkedList<Enemy>();
	
	private static Player player = new Player(400, 300, 300, 800, 600, bullets, enemies);
	private static Background bg = new Background(50);
	
	private static Random r = new Random();
	
	public static void main(String[] args) {
		
		spawnEnemy();
		
		Frame frame = new Frame(player, bg, bullets, enemies);
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		frame.makeStrat();
		
//		DisplayMode displayMode = new DisplayMode(800, 600, 16, 75);
//		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
//		GraphicsDevice device = environment.getDefaultScreenDevice();
//		
//		device.setFullScreenWindow(frame);
//		device.setDisplayMode(displayMode);
		
		final double enemySpawnTime = 1;
		double timeSinceLastEnemySpawn = 0;
		
		long lastFrame = System.currentTimeMillis();
		while (true) {
			if (Keyboard.isKeyPressed(KeyEvent.VK_ESCAPE)) {
				System.exit(0);
			}
			long thisFrame = System.currentTimeMillis();
			double timeSinceLastFrame = (double) (thisFrame - lastFrame) / 1000d;
			lastFrame = thisFrame;
			
			player.update(timeSinceLastFrame);
			
			for (int i = 0; i < bullets.size(); i++) {
				Bullet b = bullets.get(i);
				b.update(timeSinceLastFrame);
			}
			
			timeSinceLastEnemySpawn += timeSinceLastFrame;
			if (timeSinceLastEnemySpawn >= enemySpawnTime) {
				timeSinceLastEnemySpawn -= enemySpawnTime;
				spawnEnemy();
			}
			
			for (int i = 0; i < enemies.size(); i++) {
				Enemy e = enemies.get(i);
				e.update(timeSinceLastFrame);
			}
			
			bg.update(timeSinceLastFrame);
			
			frame.repaintScreen();			
		}
	}
	
	public static void spawnEnemy() {
		enemies.add(new Enemy(800, r.nextInt(600) - Enemy.getHeight(), bullets));
	}
	
}
