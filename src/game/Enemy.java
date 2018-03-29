package game;
import processing.core.*;

public class Enemy {
	
	public int x,y, radius;
	private PApplet canvas;
	
	public Enemy(PApplet canvas ,int x, int y) {
		this.canvas = canvas;
		this.x = x;
		this.y = y;
		this.radius = 30;
	}
	
	public void show() {
		canvas.fill(255,0,200);
		canvas.ellipse(x,y, radius * 2, radius * 2);
	}
	
	public void move(int dir) {
		x += dir;
	}
	
	public boolean offScreen() {
		return this.x - radius <= 0 || this.x + radius >= Game.screenWidth;
	}
	
	public boolean hitsPlayer(Ship ship) {
		double distance = PApplet.dist(this.x,this.y, ship.x,ship.y);
		
		return distance < this.radius + ship.radius;
	}
	
}
