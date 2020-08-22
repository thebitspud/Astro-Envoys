package io.thebitspud.astroenvoys.weapons;

import io.thebitspud.astroenvoys.CampaignGame;
import io.thebitspud.astroenvoys.entities.EntityID;

public class PlasmaA extends Weapon {
	public PlasmaA(CampaignGame game) {
		super(0.2, false, game);
	}

	@Override
	public String id() {
		return "Plasma A";
	}

	@Override
	public String title() {
		return "Plasma Launcher Model A";
	}

	@Override
	public String desc() {
		return "An outdated but reliable plasma weapon. Though it has long been phased out of military use, "
				+ "the Model A remains popular amongst pirate and civilian crews alike.";
	}

	@Override
	public void onActivation() {
		final int x = (int) game.player.getX() + 77,
	            y = (int) game.player.getY() + 50,
				xVel = r.nextInt(100) - 50;

		game.addProjectile(x, y, xVel, 1500, EntityID.ENERGY_SHOT);
	}
}
