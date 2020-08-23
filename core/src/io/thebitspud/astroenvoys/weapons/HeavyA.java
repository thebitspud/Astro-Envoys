package io.thebitspud.astroenvoys.weapons;

import io.thebitspud.astroenvoys.CampaignGame;
import io.thebitspud.astroenvoys.entities.EntityID;

public class HeavyA extends Weapon {
	public HeavyA(CampaignGame game) {
		super(0.4, false, game);
	}

	@Override
	public String id() {
		return "Heavy A";
	}

	@Override
	public String title() {
		return "Heavy Plasma Cannon Model A";
	}

	@Override
	public String desc() {
		return "A slow-firing but powerful plasma cannon that can deal heavy damage to any ship " +
				"caught with its shields down.";
	}

	@Override
	public void onActivation() {
		final int x = (int) game.player.getX() + 70,
				y = (int) game.player.getY() + 50,
				xVel = r.nextInt(100) - 50;

		game.addProjectile(x, y, xVel, 2000, EntityID.HEAVY_ENERGY_SHOT);
	}
}