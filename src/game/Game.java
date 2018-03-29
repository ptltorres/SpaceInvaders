package game;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

public class Game extends PApplet {
	
	public static final int screenWidth = 600, screenHeight = 400;
	
	private int numOfEnemies, enemySpeed;
	
	private Ship ship;
	private ArrayList<Enemy> enemies;
	private ArrayList<Bullet> bullets;
	
	private boolean gameOver = false;

	public void setup(){
		size(screenWidth, screenHeight);
		
		numOfEnemies = 6;
		enemySpeed = 3;
		
		ship = new Ship(this);
		enemies = new ArrayList<>();
		bullets = new ArrayList<>();
		
		// Create all the enemies
		for (int i = 0; i < numOfEnemies; i++) {
			enemies.add(new Enemy(this, i * 80 + 80, 60));
		}
	}

	public void draw(){
		background(51);	
		
		// Display all bullets
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).show();
			bullets.get(i).move();
			
			// Check for collision
			for (int j = 0; j < enemies.size(); j++) {
				if (bullets.get(i).hits(enemies.get(j))) {
					// When enemy is removed, increase their speed
					if (enemySpeed > 0)
						enemySpeed+=2;
					else
						enemySpeed-=2;
					
					enemies.remove(j);
					bullets.remove(i);
					break;
				}
			}
		}
		
		// Display all the enemies
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).show();		
			enemies.get(i).move(enemySpeed);
			
			if (enemies.get(i).hitsPlayer(ship)) {
				gameOver = true;
			}
		}
		
		// Check if the reach the edge
		for (Enemy e : enemies) {
			if (e.offScreen() ) {			// if they do...
				for (Enemy en : enemies)	// move them down
					if (!gameOver)
						en.y +=8;
								
				enemySpeed = -enemySpeed;
				break;
			}
		}
			
		// Remove bullets of the screen;
		for (Bullet b : bullets) {
			if (b.offScreen()) {
				bullets.remove(b);
				break;
			}
		}
		
		if (!gameOver) {
			ship.show();
		}	
		else {
			fill(255, 255, 0);
			textSize(32);
			text("Game Over", screenWidth / 2 - 70, screenHeight / 2); 
		}
	}
	
	public void keyPressed() {
		
		// Create a bullet with Space bar
		if (keyCode == ' ') {
			Bullet bullet = new Bullet(this, ship.x, ship.y);
			bullets.add(bullet);
		}
		
		// Move ship left or right
		if (keyCode == RIGHT) {
			ship.move(2);
		} else if (keyCode == LEFT) {
			ship.move(-2);
		}
	}
}