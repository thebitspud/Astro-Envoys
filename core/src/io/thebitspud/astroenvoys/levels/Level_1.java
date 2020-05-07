package io.thebitspud.astroenvoys.levels;

import com.badlogic.gdx.Gdx;

import io.thebitspud.astroenvoys.CampaignGame;
import io.thebitspud.astroenvoys.entities.EntityID;
import io.thebitspud.astroenvoys.tools.JTimerUtil;

public class Level_1 extends Level {
	public Level_1(CampaignGame game) {
		super(game);
	}

	@Override
	public String id() {
		return "Level 01";
	}

	@Override
	public String title() {
		return "01: Delivery";
	}

	@Override
	public String desc() {
		return "Pilot, we have a parcel that needs to be sent to the Sokar system. The sector " +
				"has had pirate problems recently, so we will reward you generously if you deliver our package.";
	}

	@Override
	protected void addEvents() {
		final int y = Gdx.graphics.getHeight();
		final int scrWidth = Gdx.graphics.getWidth(); // screen width

		timers.add(new JTimerUtil(1.0, true, true) {
			@Override
			public void onActivation() {
				if(game.enemies.isEmpty()) game.endGame(true);
			}
		});
		timers.get(timers.size() - 1).setTimeElapsed(-300.0);

		timers.add(new JTimerUtil(5.0, true, true) {
			@Override
			public void onActivation() {
				game.spawnEnemy(r.nextInt(scrWidth - 100), y, EntityID.ASTEROID);
			}
		});
	}
}