package game;

import processing.core.PApplet;
import processing.core.PConstants;

public class Ship {
	public int x, y, radius;
	private PApplet canvas;
	
	public Ship(PApplet c) {
		x = Game.screenWidth / 2;
		y = Game.screenHeight - 20;
		radius = 10;
		canvas = c;
	}
	
	public void show() {
		canvas.rectMode(PConstants.CENTER);
		canvas.fill(255);
		canvas.rect(x, y, radius * 2, radius * 2);
	}
	
	public void move(int dir) {
		x += dir * 5;
	}
}
