package io.thebitspud.astroenvoys.entities.enemies;

import java.util.Random;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.entities.EntityID;

public class Asteroid extends Enemy {
	private int aVel;

	public Asteroid(int x, int y, AstroEnvoys app) {
		super(x, y, 0, 0, 50, EntityID.ASTEROID, app);

			Random r = new Random();
			xVel = -r.nextInt(40) - 20;
			yVel = -r.nextInt(40) - 20;

			aVel = r.nextInt(40) + 20;
			if(r.nextBoolean()) aVel = -aVel;
	}

	@Override
	protected void tickAI(float delta) {
		rotate(aVel * delta);
	}
}