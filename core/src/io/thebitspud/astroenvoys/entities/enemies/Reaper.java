package io.thebitspud.astroenvoys.entities.enemies;

import com.badlogic.gdx.Gdx;

import java.util.Random;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.entities.EntityID;
import io.thebitspud.astroenvoys.entities.Player;
import io.thebitspud.astroenvoys.tools.JTimerUtil;

public class Reaper extends Enemy {
	private Random r;
	private JTimerUtil attackTimer, summonTimer;
	private Player player;
	private int rotYPos, nextSpawnHP;
	private boolean secondStageActive;
	private float yLimit;

	public Reaper(int x, int y, AstroEnvoys app) {
		super(x, y, 0, -80, 420, EntityID.AZ_REAPER, app);

		r = new Random();
		player = app.gameScreen.game.player;
		cYOffset = -75;
		setOrigin(getWidth() / 2, (getHeight() / 2 + cYOffset));
		rotYPos = (int) (getY() + getOriginY());
		yLimit = Gdx.graphics.getHeight() * 0.7f;

		secondStageActive = false;
		nextSpawnHP = health;

		attackTimer = new JTimerUtil(0.25, true, true) {
			@Override
			public void onActivation() {
				final float dx = (player.getX() + player.getWidth() / 2) - (getX() + getWidth() / 2);
				final float dy = (player.getY() + player.getHeight() / 2) - rotYPos - 20;

				double hyp = Math.hypot(dx, dy);
				float scale = (float) (1200 / hyp);
				final float xv = dx * scale, yv = dy * scale;

				final double degrees = (Math.atan2(-xv, yv) * 180.0 / Math.PI);
				setRotation((float) degrees);
				app.gameScreen.game.addProjectile((int) getX() + 78, rotYPos - 20, xv, yv, EntityID.PLASMA_SHOT);
			}
		};

		summonTimer = new JTimerUtil(15, true, true) {
			@Override
			public void onActivation() {
				app.gameScreen.game.spawnEnemy(r.nextInt(Gdx.graphics.getWidth() - 100), y, EntityID.AZ_RAIDER);
			}
		};

		app.gameScreen.game.level.addTimer(summonTimer);
	}

	@Override
	protected void tickAI(float delta) {
		attackTimer.tick(delta);

		if(yVel != 0 && getY() <= yLimit) yVel = 0;

		rotYPos = (int) (getY() + getOriginY());
		final float dx = (player.getX() + player.getWidth() / 2) - (getX() + getWidth() / 2);
		final float dy = (player.getY() + player.getHeight() / 2) - rotYPos;
		final double degrees = (Math.atan2(dx, -dy) * 180.0 / Math.PI);
		setRotation((float) degrees);
	}

	@Override
	public void adjustHealth(int value) {
		health += value;

		if(!secondStageActive && health <= 180) {
			yLimit = Gdx.graphics.getHeight() * 0.5f;
			attackTimer.setTimerDuration(0.225);
			secondStageActive = true;
			yVel = -80;
		}

		if(health <= nextSpawnHP) {
			summonTimer.setTimeElapsed(10 + summonTimer.getTimeElapsed());

			if(secondStageActive) nextSpawnHP -= 40;
			else nextSpawnHP -= 60;
		}

		if (health > maxHealth) health = maxHealth;
		else if (health <= 0) {
			health = 0;
			app.gameScreen.game.level.removeTimer(summonTimer);
			kill();
		}
	}
}
