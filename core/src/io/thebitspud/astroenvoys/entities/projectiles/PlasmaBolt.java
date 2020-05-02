package io.thebitspud.astroenvoys.entities.projectiles;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.entities.EntityID;

public class PlasmaBolt extends Projectile {
	public PlasmaBolt(int x, int y, float xVel, float yVel, AstroEnvoys app) {
		super(x, y, 12, xVel, yVel, false, true, EntityID.PLASMA_BOLT, app);
	}
}
