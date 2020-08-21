package io.thebitspud.astroenvoys.levels;

import com.badlogic.gdx.Gdx;

import io.thebitspud.astroenvoys.CampaignGame;
import io.thebitspud.astroenvoys.entities.EntityID;
import io.thebitspud.astroenvoys.tools.JTimerUtil;

public class Level_Endless extends Level {
	public Level_Endless(CampaignGame game) {
		super(game);
	}

	@Override
	public String id() {
		return "Endless";
	}

	@Override
	public String title() {
		return "High Score: 0";
	}

	@Override
	public String desc() {
		return "Test your skills and your resolve by surviving for as long as you can in this " +
				"limitless onslaught.";
	}

	@Override
	protected void addEvents() {
		final int y = Gdx.graphics.getHeight();
		final int scrWidth = Gdx.graphics.getWidth(); // screen width

		for(int i = 0; i < 4; i++) game.spawnEnemy(scrWidth / 8 * (i * 2 + 1), y + 50, EntityID.ASTEROID);

		timers.add(new JTimerUtil(3, true, true) {
			@Override
			public void onActivation() {
				game.spawnEnemy(r.nextInt(scrWidth - 100), y, EntityID.ASTEROID);
				if(getTimerDuration() > 1.0) setTimerDuration(getTimerDuration() * 0.99);
			}
		});

		timers.add(new JTimerUtil(2, true, true) {
			@Override
			public void onActivation() {
				game.spawnEnemy(r.nextInt(scrWidth - 120), y, EntityID.AZ_RAIDER);

				if(getTimerDuration() == 2) setTimerDuration(6);
				else if(getTimerDuration() > 2.5) setTimerDuration(getTimerDuration() * 0.98);
			}
		});

		timers.add(new JTimerUtil(20, true, true) {
			@Override
			public void onActivation() {
				int x = r.nextInt(scrWidth * 3/5) + scrWidth / 5 - 75;
				game.spawnEnemy(x, y, EntityID.AZ_HUNTER);
				if(getTimerDuration() > 6.0) setTimerDuration(getTimerDuration() * 0.95);
			}
		});

		timers.add(new JTimerUtil(50, true, true) {
			@Override
			public void onActivation() {
				game.spawnEnemy(r.nextInt(scrWidth - 150), y, EntityID.AZ_PREDATOR);
				if(getTimerDuration() > 10.0) setTimerDuration(getTimerDuration() * 0.9);
			}
		});

		timers.add(new JTimerUtil(200, true, true) {
			@Override
			public void onActivation() {
				game.spawnEnemy(scrWidth / 2 - 90, y, EntityID.AZ_REAPER);
				if(getTimerDuration() > 25.0) setTimerDuration(getTimerDuration() * 0.8);
			}
		});
	}
}