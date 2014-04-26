package gui;

import gameobjects.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame extends JFrame {

	private Screen screen;
	
	private boolean key_up;
	private boolean key_down;
	private boolean key_left;
	private boolean key_right;
	
	private final Player player;
	
	public Frame(Player player) {
		super("Mein Spiel");
		screen = new Screen();
		screen.setBounds(0, 0, 800, 600);
		add(screen);	
		addKeyListener(new KeyHandler());
		this.player = player;
	}
	
	public void repaintScreen() {
		screen.repaint();
	}

	public boolean getUp() {
		return key_up;
	}

	public boolean getDown() {
		return key_down;
	}

	public boolean getLeft() {
		return key_left;
	}

	public boolean getRight() {
		return key_right;
	}

	private class Screen extends JLabel {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.RED);
			g.fillRect(player.getBounding().x, player.getBounding().y, player.getBounding().width, player.getBounding().height);
		}
	}
	
	private class KeyHandler implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_W) {
				key_up = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				key_down = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_A) {
				key_left = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_D) {
				key_right = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				System.exit(0);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_W) {
				key_up = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				key_down = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_A) {
				key_left = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_D) {
				key_right = false;
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {			
		}
		
	}
}
