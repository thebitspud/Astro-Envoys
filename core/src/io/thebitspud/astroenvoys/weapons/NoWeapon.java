package io.thebitspud.astroenvoys.weapons;

import io.thebitspud.astroenvoys.CampaignGame;

public class NoWeapon extends Weapon {
	public NoWeapon(CampaignGame game) {
		super(1.0, true, game);
	}

	@Override
	public String id() {
		return "None";
	}

	@Override
	public String title() {
		return "No Weapon Selected";
	}

	@Override
	public String desc() {
		return "";
	}

	@Override
	public void onActivation() {

	}
}
