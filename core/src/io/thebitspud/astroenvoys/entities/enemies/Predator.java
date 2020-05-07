package io.thebitspud.astroenvoys.entities.enemies;

import java.util.Random;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.entities.EntityID;
import io.thebitspud.astroenvoys.entities.Player;
import io.thebitspud.astroenvoys.tools.JTimerUtil;

public class Predator extends Enemy {
	private Random r;
	private JTimerUtil attackTimer;

	public Predator(int x, int y, AstroEnvoys app) {
		super(x, y, 0, -60, 150, EntityID.AZ_PREDATOR, app);

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
	protected void tickAI(float delta) {
		attackTimer.tick(delta);

		final Player player = app.gameScreen.game.player;
		final float dx = (player.getX() + player.getWidth() / 2) - (getX() + getWidth() / 2);

		if(Math.abs(dx) < 75) xVel = 0;
		else if(dx > 0 && xVel <= 0) xVel = 100;
		else if(dx < 0 && xVel >= 0) xVel = -100;
	}
}
