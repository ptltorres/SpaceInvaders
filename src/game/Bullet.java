package game;

import processing.core.*;

public class Bullet {
	
	public int x,y, radius;
	private int vel;
	private PApplet canvas;
	
	public Bullet(PApplet canvas, int x, int y) {
		this.canvas = canvas;
		this.x = x;
		this.y = y;
		this.radius = 8;
		this.vel = 4;
	}
	
	public void show() {
		canvas.noStroke();
		canvas.fill(150,0,200);
		canvas.ellipse(x,y, radius*2, radius*2);
	}
	
	public void move() {
		this.y = this.y - vel;
	}
	
	public boolean hits(Enemy enemy) {
		double distance = PApplet.dist(this.x, this.y, enemy.x, enemy.y);
		
		return distance < this.radius + enemy.radius;
	}
	
	public boolean offScreen() {
		return this.y < 0;
	}
	
}
