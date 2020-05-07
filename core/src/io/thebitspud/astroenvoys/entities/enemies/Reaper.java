package io.thebitspud.astroenvoys.entities.enemies;

import java.util.Random;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.entities.EntityID;
import io.thebitspud.astroenvoys.tools.JTimerUtil;

public class Reaper extends Enemy {
	private Random r;
	private JTimerUtil attackTimer;

	public Reaper(int x, int y, AstroEnvoys app) {
		super(x, y, 0, -40, 250, EntityID.AZ_REAPER, app);

		r = new Random();
		attackTimer = new JTimerUtil(1.0, true, true) {
			@Override
			public void onActivation() {
				app.gameScreen.game.addProjectile((int) getX() + 55, (int) getY() + 150,
						r.nextInt(60) - 30, -1500, EntityID.HEAVY_PLASMA_SHOT);
			}
		};

		cYOffset = -75;
	}

	@Override
	protected void tickAI(float delta) {
		attackTimer.tick(delta);
	}
}
