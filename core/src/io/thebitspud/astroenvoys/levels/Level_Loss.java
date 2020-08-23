package io.thebitspud.astroenvoys.levels;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.CampaignGame;

public class Level_Loss extends Level {
	public Level_Loss(AstroEnvoys app) {
		super(app);
	}

	@Override
	public String id() {
		return "Loss";
	}

	@Override
	public String title() {
		return "Automatic Loss";
	}

	@Override
	public String desc() {
		return "Developer level for internal testing";
	}

	@Override
	protected void onClear() {

	}

	@Override
	protected void addEvents() {
		game.endGame(false);
	}
}
