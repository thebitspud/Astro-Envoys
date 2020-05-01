package io.thebitspud.astroenvoys.entities.enemies;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.entities.EntityID;

public class Asteroid extends Enemy {
	public Asteroid(int x, int y, float xVel, float yVel, AstroEnvoys app) {
		super(x, y, 100, 100, xVel, yVel, 200, EntityID.ASTEROID, app);
	}
}