package gameobjects;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Enemy {

	private static BufferedImage[] look = new BufferedImage[4];
	private final static double NEEDEDANITIME = 1;
	private double aniTime = 0;
	private double posx;
	private double posy;
	private Rectangle bounding;
	
	static{
		try {
			look[0] = ImageIO.read(Enemy.class.getClassLoader().getResourceAsStream("rsc/Enemy1.png"));
			look[1] = ImageIO.read(Enemy.class.getClassLoader().getResourceAsStream("rsc/Enemy2.png"));
			look[2] = ImageIO.read(Enemy.class.getClassLoader().getResourceAsStream("rsc/Enemy3.png"));
			look[3] = look[1];
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Enemy(double x, double y) {
		this.posx = x;
		this.posy = y;
		bounding = new Rectangle((int) x, (int) y, look[0].getWidth(), look[0].getHeight());
	}
	
	public void update(double timeSinceLastFrame) {
		aniTime += timeSinceLastFrame;
		if (aniTime >= NEEDEDANITIME) {
			aniTime = 0;
		}
	}
	
	public Rectangle getBounding() {
		return this.bounding;
	}
	
	public BufferedImage getLook() {
		if (look.length == 0) {
			return null;
		}
		for (int i = 0; i < look.length; i++) {
			if(aniTime < (NEEDEDANITIME / look.length * (i+1))) {
				return look[i];
			}
		}
		return look[look.length - 1];
	}
}
