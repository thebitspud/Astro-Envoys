package io.thebitspud.astroenvoys.weapons;

import io.thebitspud.astroenvoys.CampaignGame;
import io.thebitspud.astroenvoys.entities.EntityID;

public class PlasmaB extends Weapon {
	public PlasmaB(CampaignGame game) {
		super(0.3, false, game);
	}

	@Override
	public String id() {
		return "Plasma B";
	}

	@Override
	public String title() {
		return "Plasma Launcher Model B";
	}

	@Override
	public String desc() {
		return "An popular and highly-regarded energy weapon that fires deadly 3-round bursts " +
				"of high-velocity plasma";
	}

	private boolean leftFirst = false;

	@Override
	public void onActivation() {
		final int x = (int) game.player.getX() + 77,
				y = (int) game.player.getY() + 50;

		if(getTimerDuration() == 0.05) {
			if(leftFirst) game.addProjectile(x, y, r.nextInt(50) - 75, 1485, EntityID.ENERGY_SHOT);
			else game.addProjectile(x, y, r.nextInt(50) + 25, 1485, EntityID.ENERGY_SHOT);
			setTimerDuration(0.051);
		} else if(getTimerDuration() == 0.051) {
			if(leftFirst) game.addProjectile(x, y, r.nextInt(50) + 25, 1485, EntityID.ENERGY_SHOT);
			else game.addProjectile(x, y, r.nextInt(50) - 75, 1485, EntityID.ENERGY_SHOT);
			setTimerDuration(0.3);
		} else {
			game.addProjectile(x, y, r.nextInt(50) - 25, 1500, EntityID.ENERGY_SHOT);
			setTimerDuration(0.05);
			leftFirst = !leftFirst;
		}
	}
}