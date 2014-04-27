package gui;

import gameobjects.Bullet;
import gameobjects.Enemy;
import gameobjects.Player;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.List;

import javax.swing.JFrame;

import core.Keyboard;

public class Frame extends JFrame {
	
	private BufferStrategy strat;
	
	private final Player player;
	private final Background bg;
	
	private List<Bullet> bullets;
	private List<Enemy> enemies;
	
	public Frame(Player player, Background bg, List<Bullet> bullets, List<Enemy> enemies) {
		super("Mein Spiel");	
		addKeyListener(new Keyboard());
		this.player = player;
		this.bg = bg;
		this.bullets = bullets;
		this.enemies = enemies;
	}
	
	public void makeStrat() {
		createBufferStrategy(2);
		strat = getBufferStrategy();
	}
	
	public void repaintScreen() {
		Graphics g = strat.getDrawGraphics();
		draw(g);
		g.dispose();
		strat.show();
	}
	
	private void draw(Graphics g) {
		
		g.drawImage(bg.getLook(), bg.getX(), 0, null);
		g.drawImage(bg.getLook(), bg.getX() + bg.getLook().getWidth(), 0, null);
		
		for (int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			g.drawImage(e.getLook(), e.getBounding().x, e.getBounding().y, null);
		}
		
		for (int i = 0; i < bullets.size(); i++) {
			Bullet b = bullets.get(i);
			g.drawImage(b.getLook(), b.getBounding().x, b.getBounding().y, null);
		}
		g.drawImage(player.getLook(), player.getBounding().x, player.getBounding().y, null);
	}
}
