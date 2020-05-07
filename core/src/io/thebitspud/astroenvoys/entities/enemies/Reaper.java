package io.thebitspud.astroenvoys.entities.enemies;

import java.util.Random;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.entities.EntityID;
import io.thebitspud.astroenvoys.entities.Player;
import io.thebitspud.astroenvoys.tools.JTimerUtil;

public class Reaper extends Enemy {
	private JTimerUtil attackTimer;

	public Reaper(int x, int y, AstroEnvoys app) {
		super(x, y, 0, -40, 300, EntityID.AZ_REAPER, app);

		attackTimer = new JTimerUtil(0.33, true, true) {
			@Override
			public void onActivation() {
				final Player player = app.gameScreen.game.player;
				final float dx = (player.getX() + player.getWidth() / 2) - (getX() + getWidth() / 2);
				final float dy = (player.getY() + player.getHeight() / 2) - (getY() + 75);

				double hyp = Math.hypot(dx, dy);
				float scale = (float) (1300 / hyp);
				final float xv = dx * scale, yv = dy * scale;

				app.gameScreen.game.addProjectile((int) getX() + 55, (int) getY() + 75, xv, yv, EntityID.PLASMA_SHOT);
			}
		};

		cYOffset = -75;
	}

	@Override
	protected void tickAI(float delta) {
		attackTimer.tick(delta);
	}
}
