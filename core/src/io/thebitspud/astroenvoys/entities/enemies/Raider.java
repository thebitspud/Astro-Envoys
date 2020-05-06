package io.thebitspud.astroenvoys.entities.enemies;

import java.util.Random;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.entities.EntityID;
import io.thebitspud.astroenvoys.tools.JTimerUtil;

public class Raider extends Enemy {
	private Random r;
	private JTimerUtil attackTimer;

	public Raider(int x, int y, float xVel, float yVel, AstroEnvoys app) {
		super(x, y, xVel, yVel, 75, EntityID.AZ_RAIDER, app);
		r = new Random();
		attackTimer = new JTimerUtil(2.0, true, true) {
			@Override
			public void onActivation() {
				app.gameScreen.game.addProjectile((int) getX() + 48, (int) getY() + 20,
						r.nextInt(200) - 100, -1000, EntityID.PLASMA_SHOT);
			}
		};

		cYOffset = -20;
	}

	@Override
	protected void tickAI(float delta) {
		attackTimer.tick(delta);
	}
}
