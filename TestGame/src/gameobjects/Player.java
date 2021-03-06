package gameobjects;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import core.Keyboard;

public class Player {

	private Rectangle bounding;
	private double posx;
	private double posy;
	
	private int speed;
	
	private int worldsize_x;
	private int worldsize_y;
	
	private BufferedImage look;
	private BufferedImage look_dead;
	private List<Bullet> bullets;
	private List<Enemy> enemies;
	private double timeSinceLastShot = 0;
	private final double shotFrequenzy = 0.1;
	private boolean alive;
	
	public Player(int posx, int posy, int speed, int worldsize_x, int worldsize_y, List<Bullet> bullets, List<Enemy> enemies) {
		try {
			look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("rsc/raumschiffchen.png"));
			look_dead = ImageIO.read(getClass().getClassLoader().getResourceAsStream("rsc/raumschiff_kaputt.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		bounding = new Rectangle(posx, posy, look.getWidth(), look.getHeight());
		this.posx = posx;
		this.posy = posy;
		this.speed = speed;
		this.worldsize_x = worldsize_x;
		this.worldsize_y = worldsize_y;
		this.bullets = bullets; 
		this.enemies = enemies;
		alive = true;
	}
	
	public void update(double timeSinceLastframe) {
		
		if (Keyboard.isKeyPressed(KeyEvent.VK_R)) {
			alive = true;
		}
		
		if (!alive) {
			return;
		}
		
		timeSinceLastShot += timeSinceLastframe;
		if(Keyboard.isKeyPressed(KeyEvent.VK_W)) {
			posy -= speed * timeSinceLastframe;
		}
		if(Keyboard.isKeyPressed(KeyEvent.VK_S)) {
			posy += speed * timeSinceLastframe;
		}
		if(Keyboard.isKeyPressed(KeyEvent.VK_A)) {
			posx -= speed * timeSinceLastframe;
		}
		if(Keyboard.isKeyPressed(KeyEvent.VK_D)) {
			posx += speed * timeSinceLastframe;
		}
		
		if (Keyboard.isKeyPressed(KeyEvent.VK_SPACE)) {
			if (timeSinceLastShot > shotFrequenzy) {
				bullets.add(new Bullet((posx + ((this.getBounding().width * 0.5) - (Bullet.getLook().getWidth() * 0.5))), (((posy + this.getBounding().height *0.5 ) - (Bullet.getLook().getHeight() * 0.5))), 500, 0, bullets));
				timeSinceLastShot = 0;
			}
		}
		
		if (posx < 0) {
			posx = 0;
		}
		if (posy < 0) {
			posy = 0;
		}
		if (posx > worldsize_x - bounding.width) {
			posx = worldsize_x - bounding.width;
		}
		if (posy > worldsize_y - bounding.height) {
			posy = worldsize_y - bounding.height;
		}
		
		for (int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			if (e.isalive() && bounding.intersects(e.getBounding())) {
				alive = false;
			}
		}
		
		bounding.x = (int) this.posx;
		bounding.y = (int) this.posy;
	}
	
	public Rectangle getBounding() {
		return this.bounding;
	}
	
	public BufferedImage getLook() {
		if (alive) {
			return look;
		} else return look_dead;
	}
}
