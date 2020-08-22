package io.thebitspud.astroenvoys.weapons;

import io.thebitspud.astroenvoys.CampaignGame;
import io.thebitspud.astroenvoys.entities.EntityID;

public class ScatterA extends Weapon {
	public ScatterA(CampaignGame game) {
		super(0.8, true, game);
	}

	@Override
	public String id() {
		return "Scatter A";
	}

	@Override
	public String title() {
		return "Plasma Scatter Model A";
	}

	@Override
	public String desc() {
		return "Although it features a low power requirement and inexpensive price tag, the Scatter A " +
				"remains outclassed by nearly every other weapon available.";
	}

	@Override
	public void onActivation() {
		final int x = (int) game.player.getX() + 77,
				y = (int) game.player.getY() + 50;

		game.addProjectile(x, y, r.nextInt(50) + 75, 1485, EntityID.ENERGY_SHOT);
		game.addProjectile(x, y, r.nextInt(50) - 125, 1485, EntityID.ENERGY_SHOT);
	}
}