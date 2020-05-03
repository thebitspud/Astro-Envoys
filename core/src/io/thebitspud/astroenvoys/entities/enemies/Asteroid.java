package io.thebitspud.astroenvoys.entities.enemies;

import java.util.Random;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.entities.EntityID;

public class Asteroid extends Enemy {
	private int aVel;

	public Asteroid(int x, int y, float xVel, float yVel, AstroEnvoys app) {
		super(x, y, xVel, yVel, 50, EntityID.ASTEROID, app);

		Random r = new Random();
		 aVel = r.nextInt(40) + 20;
		 if(r.nextBoolean()) aVel = -aVel;
	}

	@Override
	public void tick(float delta) {
		translate(xVel * delta, yVel * delta);
		rotate(aVel * delta);
		checkBounds();
	}
}