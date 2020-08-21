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
				"has pirate problems, but we will reward you generously if you deliver our package.";
	}

	@Override
	protected void addEvents() {
		final int y = Gdx.graphics.getHeight();
		final int scrWidth = Gdx.graphics.getWidth(); // screen width

		for(int i = 0; i < 3; i++) game.spawnEnemy(scrWidth / 6 * (i * 2 + 1), y + 100, EntityID.ASTEROID);

		timers.add(new JTimerUtil(100, true, true) {
			private boolean checked;
			@Override
			public void onActivation() {
				if (checked) game.endGame(true);
				else setTimerDuration(0.25);

				if(game.allEnemiesCleared()) {
					checked = true;
					setTimerDuration(1.0);
				}
			}
		});

		timers.add(new JTimerUtil(3, true, true) {
			@Override
			public void onActivation() {
				game.spawnEnemy(r.nextInt(scrWidth - 100), y, EntityID.ASTEROID);
			}
		});

		timers.add(new JTimerUtil(12, true, true) {
			private int activations = 0;

			@Override
			public void onActivation() {
				if(levelTime.getTimeElapsed() >= 100) {
					setActive(false);
					summon();
					summon();
				}

				setTimerDuration(getTimerDuration() * 0.95f);

				activations++;
				summon();
				if(activations % 3 == 0) setTimeElapsed(getTimerDuration() * 0.8);
				if(activations % 8 == 0) summon();
			}

			private void summon() {
				game.spawnEnemy(r.nextInt(scrWidth - 100), y, EntityID.AZ_RAIDER);
			}
		});
	}
}