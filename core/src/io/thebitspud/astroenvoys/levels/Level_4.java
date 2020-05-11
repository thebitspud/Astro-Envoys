package io.thebitspud.astroenvoys.levels;

import com.badlogic.gdx.Gdx;

import io.thebitspud.astroenvoys.CampaignGame;
import io.thebitspud.astroenvoys.entities.EntityID;
import io.thebitspud.astroenvoys.tools.JTimerUtil;

public class Level_4 extends Level {
	public Level_4(CampaignGame game) {
		super(game);
	}

	@Override
	public String id() {
		return "Level 04";
	}

	@Override
	public String title() {
		return "04: Vengeance";
	}

	@Override
	public String desc() {
		return "Our scanners have detected a large, unidentified ship headed your way. Records " +
				"indicate that we've never encountered an Azikan vessel of this class before. " +
				"Be careful, pilot.";
	}

	@Override
	protected void addEvents() {
		final int y = Gdx.graphics.getHeight();
		final int scrWidth = Gdx.graphics.getWidth();

		for(int i = 0; i < 4; i++) game.spawnEnemy(scrWidth / 8 * (i * 2 + 1), y + 100, EntityID.ASTEROID);

		timers.add(new JTimerUtil(10, true, true) {
			@Override
			public void onActivation() {
				for(int i = 0; i < 3; i++) game.spawnEnemy(scrWidth / 6 * (i * 2 + 1), y, EntityID.ASTEROID);
			}
		});

		timers.add(new JTimerUtil(5, true, true) {
			private boolean checked = false;
			private int activations = 0;

			@Override
			public void onActivation() {
				if (checked) {
					game.endGame(true);
					return;
				}

				if (game.allEnemiesCleared()) {
					activations++;
					setTimerDuration(5);
				} else {
					setTimerDuration(0.25);
					return;
				}

				switch (activations) {
					case 1: game.spawnEnemy(scrWidth - 220, y + 100, EntityID.AZ_RAIDER);
						game.spawnEnemy(r.nextInt(scrWidth / 5) + scrWidth * 2/5 - 60, y + 200, EntityID.AZ_RAIDER);
						game.spawnEnemy(100, y + 100, EntityID.AZ_RAIDER);
						break;
					case 2:
						game.spawnEnemy(r.nextInt(scrWidth / 5) + scrWidth * 2/5 - 75, y + 50, EntityID.AZ_HUNTER);
						game.spawnEnemy(r.nextInt(scrWidth / 5) + scrWidth / 5 - 60, y + 100, EntityID.AZ_RAIDER);
						game.spawnEnemy(r.nextInt(scrWidth / 5) + scrWidth * 3/5 - 60, y + 100, EntityID.AZ_RAIDER);
						game.spawnEnemy(r.nextInt(scrWidth / 5) + scrWidth / 5 - 60, y + 300, EntityID.AZ_RAIDER);
						game.spawnEnemy(r.nextInt(scrWidth / 5) + scrWidth * 3/5 - 60, y + 300, EntityID.AZ_RAIDER);
						break;
					case 3:
						game.spawnEnemy(scrWidth / 2 - 75, y + 150, EntityID.AZ_PREDATOR);
						game.spawnEnemy(r.nextInt(scrWidth / 5) + scrWidth * 2/5 - 75, y + 100, EntityID.AZ_HUNTER);
						game.spawnEnemy(r.nextInt(scrWidth / 5) + scrWidth / 5 - 60, y + 100, EntityID.AZ_RAIDER);
						game.spawnEnemy(r.nextInt(scrWidth / 5) + scrWidth * 3/5 - 60, y + 100, EntityID.AZ_RAIDER);
						game.spawnEnemy(r.nextInt(scrWidth / 5) + scrWidth / 5 - 60, y + 300, EntityID.AZ_RAIDER);
						game.spawnEnemy(r.nextInt(scrWidth / 5) + scrWidth * 3/5 - 60, y + 300, EntityID.AZ_RAIDER);
						break;
					case 4:
						game.spawnEnemy(scrWidth / 2 - 75, y + 100, EntityID.AZ_PREDATOR);
						game.spawnEnemy(r.nextInt(scrWidth / 5) + scrWidth / 5 - 60, y + 100, EntityID.AZ_RAIDER);
						game.spawnEnemy(r.nextInt(scrWidth / 5) + scrWidth * 3/5 - 60, y + 100, EntityID.AZ_RAIDER);
						game.spawnEnemy(r.nextInt(scrWidth / 5) + scrWidth / 5 - 75, y + 100, EntityID.AZ_HUNTER);
						game.spawnEnemy(r.nextInt(scrWidth / 5) + scrWidth * 3/5 - 75, y + 100, EntityID.AZ_HUNTER);
						game.spawnEnemy(r.nextInt(scrWidth / 5) + scrWidth / 5 - 60, y + 300, EntityID.AZ_RAIDER);
						game.spawnEnemy(r.nextInt(scrWidth / 5) + scrWidth * 3/5 - 60, y + 300, EntityID.AZ_RAIDER);
						break;
					case 5: game.spawnEnemy(Gdx.graphics.getWidth() / 2 - 90, y + 50, EntityID.AZ_REAPER);
						break;
					case 6:
						checked = true;
						setTimerDuration(1);
						break;
				}
			}
		});
	}
}
