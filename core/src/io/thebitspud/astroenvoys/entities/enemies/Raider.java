package io.thebitspud.astroenvoys.entities.enemies;

import java.util.Random;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.entities.EntityID;
import io.thebitspud.astroenvoys.tools.JTimerUtil;

public class Raider extends Enemy {
	private Random r;
	private JTimerUtil attackTimer;

	public Raider(int x, int y, AstroEnvoys app) {
		super(x, y, 0, -60, 75, EntityID.AZ_RAIDER, app);

		r = new Random();
		xVel = r.nextInt(100) - 50;
		if(r.nextBoolean()) xVel = -xVel;

		attackTimer = new JTimerUtil(2.0, true, true) {
			@Override
			public void onActivation() {
				app.gameScreen.game.addProjectile((int) getX() + 48, (int) getY() + 20,
						r.nextInt(200) - (100 - xVel), -1000, EntityID.PLASMA_SHOT);
			}
		};

		attackTimer.setTimeElapsed(-r.nextFloat());

		cYOffset = -20;
	}

	@Override
	protected void tickAI(float delta) {
		attackTimer.tick(delta);
	}
}
