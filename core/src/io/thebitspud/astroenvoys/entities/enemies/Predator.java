package io.thebitspud.astroenvoys.entities.enemies;

import java.util.Random;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.entities.EntityID;
import io.thebitspud.astroenvoys.tools.JTimerUtil;

public class Predator extends Enemy {
	private Random r;
	private JTimerUtil attackTimer;

	public Predator(int x, int y, float xVel, float yVel, AstroEnvoys app) {
		super(x, y, xVel, yVel, 150, EntityID.AZ_PREDATOR, app);

		r = new Random();
		attackTimer = new JTimerUtil(1.5, true, true) {
			@Override
			public void onActivation() {
				app.gameScreen.game.addProjectile((int) getX() + 55, (int) getY() + 150,
						r.nextInt(60) - 30, -1500, EntityID.HEAVY_PLASMA_SHOT);
			}
		};

		cYOffset = -50;
	}

	@Override
	public void tick(float delta) {
		translate(xVel * delta, yVel * delta);
		checkBounds();
		attackTimer.tick(delta);
	}
}
