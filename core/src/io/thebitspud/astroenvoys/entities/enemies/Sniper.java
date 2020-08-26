package io.thebitspud.astroenvoys.entities.enemies;

import java.util.Random;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.entities.EntityID;
import io.thebitspud.astroenvoys.entities.Player;
import io.thebitspud.astroenvoys.tools.JTimerUtil;

public class Sniper extends Enemy {
	private final Random r;
	private final Player player;
	private final JTimerUtil attackTimer;
	private int rotYPos;
	private float dx, dy;

	public Sniper(int x, int y, AstroEnvoys app) {
		super(x, y, 0, 0, 150, EntityID.AZ_SNIPER, app);

		r = new Random();
		player = app.gameScreen.game.player;
		cYOffset = 30;
		setOrigin(getWidth() / 2, (getHeight() / 2 + cYOffset));
		rotYPos = (int) (getY() + getOriginY());

		xVel = r.nextInt(60) + 90;
		if(!r.nextBoolean()) xVel = -xVel;
		yVel = (float) -50;

		attackTimer = new JTimerUtil(1.5, true, true) {
			@Override
			public void onActivation() {
				final int yAdjust = -30;
				double hyp = Math.hypot(dx, dy + yAdjust);
				float scale = (float) (1600 / hyp);
				final float xv = dx * scale, yv = (dy + yAdjust) * scale;

				app.gameScreen.game.addProjectile((int) getX() + 55, rotYPos + yAdjust, xv, yv,
						EntityID.HEAVY_PLASMA_SHOT);
			}
		};

		attackTimer.setTimeElapsed(-r.nextFloat());
	}

	@Override
	protected void tickAI(float delta) {
		attackTimer.tick(delta);

		rotYPos = (int) (getY() + getOriginY());
		dx = (player.getX() + player.getWidth() / 2) - (getX() + getWidth() / 2);
		dy = (player.getY() + player.getHeight() / 2) - rotYPos;
		final double degrees = (Math.atan2(dx, -dy) * 180.0 / Math.PI);
		setRotation((float) degrees);
	}
}
