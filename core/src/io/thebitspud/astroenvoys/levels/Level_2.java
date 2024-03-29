package io.thebitspud.astroenvoys.levels;

import com.badlogic.gdx.Gdx;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.entities.EntityID;
import io.thebitspud.astroenvoys.tools.JTimerUtil;

public class Level_2 extends Level {
	public Level_2(AstroEnvoys app) {
		super(app);
	}

	@Override
	public String id() {
		return "Level 02";
	}

	@Override
	public String title() {
		return "02: Investigation";
	}

	@Override
	public String desc() {
		return "Your delivery has confirmed rumours that the Azikan Swarm has established a presence in the " +
				"outskirts of the system. Pilot, we need you to determine where their ships are coming from.";
	}

	@Override
	protected void onClear() {
		game.player.unlockShield();
		app.levelSelectScreen.addLevel(new Level_3(app));
	}

	@Override
	protected void addEvents() {
		final int y = Gdx.graphics.getHeight();
		final int scrWidth = Gdx.graphics.getWidth();

		for(int i = 0; i < 4; i++) game.spawnEnemy(scrWidth / 8 * (i * 2 + 1), y + 50, EntityID.ASTEROID);

		timers.add(new JTimerUtil(125, true, true) {
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

		timers.add(new JTimerUtil(5, true, true) {
			@Override
			public void onActivation() {
				for(int i = 0; i < 2; i++) game.spawnEnemy(scrWidth / 4 * (i * 2 + 1), y, EntityID.ASTEROID);
			}
		});

		timers.add(new JTimerUtil(2, true, true) {
			private int activations = 0;

			@Override
			public void onActivation() {
				if(activations == 0) setTimerDuration(6);

				if(levelTime.getTimeElapsed() >= 120) {
					setActive(false);
					summon();
				}

				if(getTimerDuration() > 4) setTimerDuration(getTimerDuration() * 0.985);

				activations++;
				summon();
				if(activations % 5 == 3 || activations % 5 == 4) setTimeElapsed(getTimerDuration() * 0.5);
			}

			private void summon() {
				game.spawnEnemy(r.nextInt(scrWidth - 120), y, EntityID.AZ_RAIDER);
			}
		});

		timers.add(new JTimerUtil(30, true, true) {
			private int activations = 0;

			@Override
			public void onActivation() {
				setTimerDuration(getTimerDuration() * 0.9);

				game.spawnEnemy(r.nextInt(scrWidth * 3/5) + scrWidth / 5 - 75, y, EntityID.AZ_HUNTER);
				activations++;

				if(activations == 5) setActive(false);
			}
		});
	}
}