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

		for(int i = 0; i < 4; i++) game.spawnEnemy(scrWidth / 8 * (i * 2 + 1), y + 100, EntityID.ASTEROID);

		timers.add(new JTimerUtil(120, true, true) {
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
				if(getTimerDuration() == 3) setTimerDuration(6);
				for(int i = 0; i < 3; i++) game.spawnEnemy(scrWidth / 6 * (i * 2 + 1), y, EntityID.ASTEROID);
			}
		});

		timers.add(new JTimerUtil(3, true, true) {
			private int activations = 1;

			@Override
			public void onActivation() {
				if(activations == 1) setTimerDuration(7);

				if(levelTime.getTimeElapsed() >= 120) {
					setActive(false);
					summon();
				}

				if(getTimerDuration() > 5) setTimerDuration(getTimerDuration() * 0.98f);

				activations++;
				summon();
				if(activations % 4 == 0) setTimeElapsed(getTimerDuration() * 0.8);
			}

			private void summon() {
				game.spawnEnemy(r.nextInt(scrWidth - 100), y, EntityID.AZ_RAIDER);
			}
		});

		timers.add(new JTimerUtil(45, true, true) {
			private int activations = 0;

			@Override
			public void onActivation() {
				setTimerDuration(getTimerDuration() - 10);

				game.spawnEnemy(r.nextInt(scrWidth * 3/5) + scrWidth / 5 - 75, y, EntityID.AZ_HUNTER);
				activations++;

				if(activations == 4) setActive(false);
			}
		});
	}
}