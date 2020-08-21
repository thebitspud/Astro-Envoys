package io.thebitspud.astroenvoys.levels;

import com.badlogic.gdx.Gdx;

import io.thebitspud.astroenvoys.CampaignGame;
import io.thebitspud.astroenvoys.entities.EntityID;
import io.thebitspud.astroenvoys.tools.JTimerUtil;

public class Level_2 extends Level {
	public Level_2(CampaignGame game) {
		super(game);
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
		return "Our battle confirmed rumours that the Azikan Swarm has established " +
			"a presence in the outskirts of the system. Pilot, we need you to " +
			"pinpoint where the bugs are coming from.";

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

		timers.add(new JTimerUtil(4, true, true) {
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

				if(getTimerDuration() > 5) setTimerDuration(getTimerDuration() * 0.99);

				activations++;
				summon();
				if(activations % 5 == 3 || activations % 5 == 4) setTimeElapsed(getTimerDuration() * 0.5);
			}

			private void summon() {
				game.spawnEnemy(r.nextInt(scrWidth - 100), y, EntityID.AZ_RAIDER);
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