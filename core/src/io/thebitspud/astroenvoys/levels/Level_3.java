package io.thebitspud.astroenvoys.levels;

import com.badlogic.gdx.Gdx;

import io.thebitspud.astroenvoys.CampaignGame;
import io.thebitspud.astroenvoys.entities.EntityID;

public class Level_3 extends Level {
	public Level_3(CampaignGame game) {
		super(game);
	}

	@Override
	public String id() {
		return "Level 03";
	}

	@Override
	public String title() {
		return "03: Incursion";
	}

	@Override
	public String desc() {
		return "Since your last mission, we have located multiple new Azikan stations, including an " +
			"isolated shipyard still under construction. We are requesting your aid in " +
			"taking it out before they finish building it.";
	}

	@Override
	protected void addEvents() {
		game.spawnEnemy(Gdx.graphics.getWidth() / 2 - 90, Gdx.graphics.getHeight(), EntityID.AZ_REAPER);
	}
}
