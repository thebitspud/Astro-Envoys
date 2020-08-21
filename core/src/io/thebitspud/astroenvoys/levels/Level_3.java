package io.thebitspud.astroenvoys.levels;

import com.badlogic.gdx.Gdx;

import io.thebitspud.astroenvoys.CampaignGame;
import io.thebitspud.astroenvoys.entities.EntityID;
import io.thebitspud.astroenvoys.tools.JTimerUtil;

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
		final int y = Gdx.graphics.getHeight();
		final int scrWidth = Gdx.graphics.getWidth();

		for(int i = 0; i < 4; i++) game.spawnEnemy(scrWidth / 8 * (i * 2 + 1), y + 100, EntityID.ASTEROID);

		timers.add(new JTimerUtil(7, true, true) {
			@Override
			public void onActivation() {
				for(int i = 0; i < 3; i++) game.spawnEnemy(scrWidth / 6 * (i * 2 + 1), y, EntityID.ASTEROID);
			}
		});

		timers.add(new JTimerUtil(140, true, true) {
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
			private int activations = 3;

			@Override
			public void onActivation() {
				setTimerDuration(7);
				if(levelTime.getTimeElapsed() >= 140) setActive(false);

				activations++;
				game.spawnEnemy(r.nextInt(scrWidth - 100), y, EntityID.AZ_RAIDER);
				if(activations % 4 == 0 || activations % 7 == 0) setTimeElapsed(getTimerDuration() * 0.85);
			}
		});

		timers.add(new JTimerUtil(35, true, true) {
			private int activations = 0;

			@Override
			public void onActivation() {
				if(getTimerDuration() >= 20) setTimerDuration(getTimerDuration() - 5);

				game.spawnEnemy(r.nextInt(scrWidth / 5) + scrWidth * 2/5 - 75, y, EntityID.AZ_HUNTER);
				activations++;

				if(activations == 6) {
					game.spawnEnemy(scrWidth / 2 - 75, y + 100, EntityID.AZ_PREDATOR);
					setActive(false);
				}
			}
		});

		timers.add(new JTimerUtil(60, true, true) {
			@Override
			public void onActivation() {
				if(getTimerDuration() == 60) setTimerDuration(50);
				else setActive(false);

				game.spawnEnemy(scrWidth / 2 - 75, y + 100, EntityID.AZ_PREDATOR);
			}
		});
	}
}
