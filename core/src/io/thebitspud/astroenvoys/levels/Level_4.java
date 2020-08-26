package io.thebitspud.astroenvoys.levels;

import com.badlogic.gdx.Gdx;
import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.entities.EntityID;
import io.thebitspud.astroenvoys.tools.JTimerUtil;
import io.thebitspud.astroenvoys.weapons.ScatterB;

public class Level_4 extends Level {
	public Level_4(AstroEnvoys app) {
		super(app);
	}

	@Override
	public String id() {
		return "Level 04";
	}

	@Override
	public String title() {
		return "04: Diversion";
	}

	@Override
	public String desc() {
		return "Our sensors have detected some Azikan energy signatures that Coalition forces have never " +
				"encountered before. We will distract their main battle swarm with a small decoy fleet while you " +
				"move in and destroy the shipyard.";
	}

	@Override
	protected void onClear() {
		app.loadoutScreen.addSecondary(new ScatterB(game));
		app.levelSelectScreen.addLevel(new Level_5(app));
	}

	@Override
	protected void addEvents() {
		final int y = Gdx.graphics.getHeight();
		final int scrWidth = Gdx.graphics.getWidth();

		for(int i = 0; i < 3; i++) game.spawnEnemy(scrWidth / 6 * (i * 2 + 1), y + 50, EntityID.ASTEROID);

		timers.add(new JTimerUtil(8, true, true) {
			@Override
			public void onActivation() {
				for(int i = 0; i < 3; i++) game.spawnEnemy(scrWidth / 6 * (i * 2 + 1), y, EntityID.ASTEROID);
			}
		});

		timers.add(new JTimerUtil(150, true, true) {
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
				if(activations == 0) setTimerDuration(7);
				if(levelTime.getTimeElapsed() >= 140) setActive(false);

				activations++;
				game.spawnEnemy(r.nextInt(scrWidth - 120), y, EntityID.AZ_RAIDER);
				if(activations % 5 == 1 || activations % 5 == 2) setTimeElapsed(getTimerDuration() * 0.5);
			}
		});

		timers.add(new JTimerUtil(10, true, true) {
			private int activations = 0;

			@Override
			public void onActivation() {
				if(activations == 0) setTimerDuration(30);
				if(getTimerDuration() > 20) setTimerDuration(getTimerDuration() - 5);

				game.spawnEnemy(r.nextInt(scrWidth / 5) + scrWidth * 2/5 - 75, y, EntityID.AZ_HUNTER);
				activations++;

				if(activations == 7) setActive(false);
			}
		});

		timers.add(new JTimerUtil(45, true, true) {
			@Override
			public void onActivation() {
				if(getTimerDuration() == 30) setActive(false);
				setTimerDuration(getTimerDuration() - 5);

				game.spawnEnemy(scrWidth / 2 - 75, y, EntityID.AZ_PREDATOR);
			}
		});

		timers.add(new JTimerUtil(25, true, true) {
			int activations = 0;
			@Override
			public void onActivation() {
				game.spawnEnemy(r.nextInt(scrWidth - 150), y, EntityID.AZ_SNIPER);
				activations++;
				if(activations == 6) {
					setActive(false);
					game.spawnEnemy(r.nextInt(scrWidth / 5) + scrWidth * 2/5 - 75, y, EntityID.AZ_HUNTER);
				}
			}
		});
	}
}
