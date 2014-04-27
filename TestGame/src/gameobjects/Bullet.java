package gameobjects;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

public class Bullet {

	private static BufferedImage look;
	
	static{
		try {
			look = ImageIO.read(Bullet.class.getClassLoader().getResourceAsStream("rsc/schuss.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private double posx;
	private double posy;
	private double speedx = 500;
	private double speedy;
	private Rectangle bounding;
	private List<Bullet> bullets;
	private double timeAlive;
	private final int timeToLive = 10;
	
	public Bullet(double x, double y, double speedx, double speedy, List<Bullet> bullets) {
		this.posx = x;
		this.posy = y;
		this.speedx = speedx;
		this.speedy = speedy;
		bounding = new Rectangle((int)this.posx, (int)this.posy, look.getWidth(), look.getHeight());
		this.bullets = bullets;
	}
	
	public void update(double timeSinceLastFrame) {
		timeAlive += timeSinceLastFrame;
		if (timeAlive >= timeToLive) {
			bullets.remove(this);
		}
		posx += speedx * timeSinceLastFrame;
		posy += speedy * timeSinceLastFrame;
		bounding.x = (int)posx;
		bounding.y = (int)posy;
	}
	
	public Rectangle getBounding() {
		return this.bounding;
	}
	
	public static BufferedImage getLook() {
		return look;
	}
}
