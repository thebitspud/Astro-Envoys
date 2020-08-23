package io.thebitspud.astroenvoys.weapons;

import io.thebitspud.astroenvoys.CampaignGame;
import io.thebitspud.astroenvoys.entities.EntityID;

public class ScatterB extends Weapon {
	public ScatterB(CampaignGame game) {
		super(1.2, true, game);
	}

	@Override
	public String id() {
		return "Scatter B";
	}

	@Override
	public String title() {
		return "Plasma Scatter Model B";
	}

	@Override
	public String desc() {
		return "A compact, easily concealable plasma launcher that has recently gained infamy for its use " +
				"by pirate and raider parties.";
	}

	@Override
	public void onActivation() {
		final int x = (int) game.player.getX() + 77,
				y = (int) game.player.getY() + 50;

		game.addProjectile(x, y, r.nextInt(80) + 335, 1460, EntityID.ENERGY_SHOT);
		game.addProjectile(x, y, r.nextInt(60) + 220, 1485, EntityID.ENERGY_SHOT);
		game.addProjectile(x, y, r.nextInt(50) + 100, 1495, EntityID.ENERGY_SHOT);
		game.addProjectile(x, y, r.nextInt(50) - 150, 1495, EntityID.ENERGY_SHOT);
		game.addProjectile(x, y, r.nextInt(60) - 280, 1485, EntityID.ENERGY_SHOT);
		game.addProjectile(x, y, r.nextInt(80) - 415, 1460, EntityID.ENERGY_SHOT);
	}
}