package gameobjects;

import java.awt.Rectangle;

public class Player {

	private Rectangle bounding;
	private double posx;
	private double posy;
	
	private int speed;
	
	private int worldsize_x;
	private int worldsize_y;
	
	public Player(int posx, int posy, int size, int speed, int worldsize_x, int worldsize_y) {
		bounding = new Rectangle(posx, posy, size, size);
		this.posx = posx;
		this.posy = posy;
		this.speed = speed;
		this.worldsize_x = worldsize_x;
		this.worldsize_y = worldsize_y;
	}
	
	public void update(double timeSinceLastframe, boolean up, boolean down, boolean left, boolean right) {
		if(up) {
			posy -= speed * timeSinceLastframe;
		}
		if(down) {
			posy += speed * timeSinceLastframe;
		}
		if(left) {
			posx -= speed * timeSinceLastframe;
		}
		if(right) {
			posx += speed * timeSinceLastframe;
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
		
		bounding.x = (int) this.posx;
		bounding.y = (int) this.posy;
	}
	
	public Rectangle getBounding() {
		return this.bounding;
	}
	
}
