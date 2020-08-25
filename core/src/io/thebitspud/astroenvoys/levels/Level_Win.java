package io.thebitspud.astroenvoys.levels;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.CampaignGame;

public class Level_Win extends Level {
	public Level_Win(AstroEnvoys app) {
		super(app);
	}

	@Override
	public String id() {
		return "Win";
	}

	@Override
	public String title() {
		return "Automatic Win";
	}

	@Override
	public String desc() {
		return "Dev level for internal testing";
	}

	@Override
	protected void onClear() {

	}

	@Override
	protected void addEvents() {
		game.endGame(true);
	}
}
