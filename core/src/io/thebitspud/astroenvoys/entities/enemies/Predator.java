package io.thebitspud.astroenvoys.entities.enemies;

import com.badlogic.gdx.Gdx;

import java.util.Random;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.entities.EntityID;
import io.thebitspud.astroenvoys.entities.Player;
import io.thebitspud.astroenvoys.tools.JTimerUtil;

public class Predator extends Enemy {
	private Random r;
	private JTimerUtil attackTimer;

	public Predator(int x, int y, AstroEnvoys app) {
		super(x, y, 0, -60, 200, EntityID.AZ_PREDATOR, app);

		r = new Random();
		cYOffset = -50;

		attackTimer = new JTimerUtil(1.4, true, true) {
			@Override
			public void onActivation() {
				if(getTimerDuration() == 1.2) setTimerDuration(0.3);
				else if(getTimerDuration() == 0.3) setTimerDuration(0.299);
				else setTimerDuration(1.2);
				app.gameScreen.game.addProjectile((int) getX() + 55, (int) getY() + 150,
						r.nextInt(100) - 50, -1500, EntityID.HEAVY_PLASMA_SHOT);
			}
		};

		attackTimer.setTimeElapsed(-r.nextFloat());
	}

	@Override
	protected void tickAI(float delta) {
		attackTimer.tick(delta);

		final Player player = app.gameScreen.game.player;
		final float dx = (player.getX() + player.getWidth() / 2) - (getX() + getWidth() / 2);

		if(yVel != 0 && getY() <= Gdx.graphics.getHeight() * 0.6f) yVel = 0;
		if(Math.abs(dx) < 50) xVel = dx * 5;
		else if(dx > 0 && xVel <= 0) xVel = 250;
		else if(dx < 0 && xVel >= 0) xVel = -250;
	}
}
