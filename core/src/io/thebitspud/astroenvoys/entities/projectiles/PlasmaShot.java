package io.thebitspud.astroenvoys.entities.projectiles;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.entities.EntityID;

public class PlasmaShot extends Projectile {
	public PlasmaShot(int x, int y, float xVel, float yVel, AstroEnvoys app) {
		super(x, y, 5, xVel, yVel, true, false, EntityID.PLASMA_SHOT, app);
	}
}
