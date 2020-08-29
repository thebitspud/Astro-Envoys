package io.thebitspud.astroenvoys.levels;

import com.badlogic.gdx.Gdx;
import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.entities.EntityID;
import io.thebitspud.astroenvoys.tools.JTimerUtil;
import io.thebitspud.astroenvoys.weapons.HeavyA;

public class Level_3 extends Level {
	public Level_3(AstroEnvoys app) {
		super(app);
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
			"isolated shipyard still under construction. Scout out the region and identify any potential " +
			"openings in their defense.";
	}

	@Override
	protected void onClear() {
		app.loadoutScreen.addPrimary(new HeavyA(game));
		app.levelSelectScreen.addLevel(new Level_4(app));
	}

	@Override
	protected void addEvents() {
		final int y = Gdx.graphics.getHeight();
		final int scrWidth = Gdx.graphics.getWidth();

		for(int i = 0; i < 4; i++) game.spawnEnemy(scrWidth / 8 * (i * 2 + 1), y + 50, EntityID.ASTEROID);

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
			private int activations = 0;

			@Override
			public void onActivation() {
				if(activations == 0) setTimerDuration(6);
				if(levelTime.getTimeElapsed() >= 140) setActive(false);

				activations++;
				game.spawnEnemy(r.nextInt(scrWidth - 120), y, EntityID.AZ_RAIDER);
				if(activations % 4 == 1 || activations % 7 == 4) setTimeElapsed(getTimerDuration() * 0.75);
			}
		});

		timers.add(new JTimerUtil(30, true, true) {
			private int activations = 0;

			@Override
			public void onActivation() {
				if(getTimerDuration() > 20) setTimerDuration(getTimerDuration() - 5);

				game.spawnEnemy(r.nextInt(scrWidth / 5) + scrWidth * 2/5 - 75, y, EntityID.AZ_HUNTER);
				activations++;

				if(activations == 6) setActive(false);
			}
		});

		timers.add(new JTimerUtil(42.5, true, true) {
			@Override
			public void onActivation() {
				if(getTimerDuration() == 27.5) setActive(false);
				setTimerDuration(getTimerDuration() - 5);

				game.spawnEnemy(scrWidth / 2 - 75, y, EntityID.AZ_PREDATOR);
			}
		});
	}
}
