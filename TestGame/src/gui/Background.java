package gui;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Background {

	private double posx;
	private double speed;
	private BufferedImage look;
	
	public Background(double speed) {
		this.speed = speed;
		
		try {
			look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("rsc/weltraum.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update(double timeSinceLastFrame) {
		posx-= speed*timeSinceLastFrame;
		if(posx < (-look.getWidth() + 800)) {
			posx = 0;
		}
	}
	
	public int getX() {
		return (int) posx;
	}
	
	public BufferedImage getLook() {
		return look;
	}
	
}
