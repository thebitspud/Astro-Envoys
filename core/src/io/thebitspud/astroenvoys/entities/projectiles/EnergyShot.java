package io.thebitspud.astroenvoys.entities.projectiles;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.entities.EntityID;

public class EnergyShot extends Projectile {
	public EnergyShot(int x, int y, float xVel, float yVel, AstroEnvoys app) {
		super(x, y, 7, xVel, yVel, false, true, EntityID.ENERGY_SHOT, app);
	}
}
