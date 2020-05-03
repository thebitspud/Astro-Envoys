package io.thebitspud.astroenvoys.entities.projectiles;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.entities.EntityID;

public class HeavyPlasmaShot extends Projectile {
	public HeavyPlasmaShot(int x, int y, float xVel, float yVel, AstroEnvoys app) {
		super(x, y, 12, xVel, yVel, true, false, EntityID.HEAVY_PLASMA_SHOT, app);
	}
}
