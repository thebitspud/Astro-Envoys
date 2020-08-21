package io.thebitspud.astroenvoys.levels;

import io.thebitspud.astroenvoys.CampaignGame;

public class Level_Win extends Level {
	public Level_Win(CampaignGame game) {
		super(game);
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
		return "Developer level for internal testing";
	}

	@Override
	protected void addEvents() {
		game.endGame(true);
	}
}
