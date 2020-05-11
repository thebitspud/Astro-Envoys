package io.thebitspud.astroenvoys.entities.enemies;

import java.util.Random;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.entities.EntityID;
import io.thebitspud.astroenvoys.tools.JTimerUtil;

public class Hunter extends Enemy {
	private Random r;
	private JTimerUtil attackTimer;

	public Hunter(int x, int y, AstroEnvoys app) {
		super(x, y, 0, -40, 130, EntityID.AZ_HUNTER, app);

		r = new Random();

		attackTimer = new JTimerUtil(4.5, true, true) {
			@Override
			public void onActivation() {
				app.gameScreen.game.addProjectile((int) getX() + 13, (int) getY() + 20,
						r.nextInt(100) - 75, -1000, EntityID.PLASMA_SHOT);

				app.gameScreen.game.addProjectile((int) getX() + 113, (int) getY() + 20,
						r.nextInt(100) - 25, -1000, EntityID.PLASMA_SHOT);

				app.gameScreen.game.addProjectile((int) getX(), (int) getY() + 20,
						r.nextInt(100) - 200, -1000, EntityID.PLASMA_SHOT);

				app.gameScreen.game.addProjectile((int) getX() + 125, (int) getY() + 20,
						r.nextInt(100) + 100, -1000, EntityID.PLASMA_SHOT);
			}
		};

		attackTimer.setTimeElapsed(-r.nextFloat() * 4 + 2);

		cYOffset = -50;
	}

	@Override
	protected void tickAI(float delta) {
		attackTimer.tick(delta);
	}
}