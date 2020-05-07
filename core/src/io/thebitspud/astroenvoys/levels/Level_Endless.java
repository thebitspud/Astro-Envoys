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
		return "High Score: 100000";
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

		timers.add(new JTimerUtil(3.0, true, true) {
			@Override
			public void onActivation() {
				game.spawnEnemy(r.nextInt(scrWidth - 100), y, EntityID.ASTEROID);
				if(getTimerDuration() > 1.0) setTimerDuration(getTimerDuration() * 0.98f);
			}
		});

		timers.add(new JTimerUtil(10.0, true, true) {
			@Override
			public void onActivation() {
				game.spawnEnemy(r.nextInt(scrWidth - 120), y, EntityID.AZ_RAIDER);
				if(getTimerDuration() > 2.5) setTimerDuration(getTimerDuration() * 0.95f);
			}
		});

		timers.add(new JTimerUtil(40.0, true, true) {
			@Override
			public void onActivation() {
				int x = r.nextInt(scrWidth * 3/5) + scrWidth / 5 - 75;
				game.spawnEnemy(x, y, EntityID.AZ_HUNTER);
				if(getTimerDuration() > 6.0) setTimerDuration(getTimerDuration() * 0.90f);
			}
		});

		timers.add(new JTimerUtil(100.0, true, true) {
			@Override
			public void onActivation() {
				game.spawnEnemy(r.nextInt(scrWidth - 150), y, EntityID.AZ_PREDATOR);
				if(getTimerDuration() > 10.0) setTimerDuration(getTimerDuration() * 0.90f);
			}
		});

		timers.add(new JTimerUtil(250.0, true, true) {
			@Override
			public void onActivation() {
				game.spawnEnemy(scrWidth / 2 - 90, y, EntityID.AZ_REAPER);
				if(getTimerDuration() > 18.0) setTimerDuration(getTimerDuration() * 0.84f);
			}
		});
	}
}