package io.thebitspud.astroenvoys.entities.projectiles;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.entities.EntityID;

public class HeavyEnergyShot extends Projectile {
	public HeavyEnergyShot(int x, int y, float xVel, float yVel, AstroEnvoys app) {
		super(x, y, 12, xVel, yVel, true, false, EntityID.HEAVY_ENERGY_SHOT, app);
	}
}
