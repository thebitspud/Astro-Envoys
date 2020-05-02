package io.thebitspud.astroenvoys.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.tools.JTimerUtil;

public class Player extends Entity {
	private JTimerUtil attackTimer;
	private Random r;

	public Player(AstroEnvoys app) {
		super(0, 0, 250, EntityID.PLAYER, app);

		r = new Random();
	}

	public void init() {
		maxHealth = 100;
		health = 100;
		setCenter(Gdx.graphics.getWidth() * 0.5f, Gdx.graphics.getHeight() * 0.25f);

		attackTimer = new JTimerUtil(0.25, true, true) {
			@Override
			public void onActivation() {
				app.gameScreen.game.addProjectile((int) getX() + 72, (int) getY() + 50,
						r.nextInt(100) - 50, 1500, EntityID.PLASMA_BOLT);
			}
		};
	}

	@Override
	public void tick(float delta) {
		attackTimer.tick(delta);
	}

	// Triangle on rectangle collisions

	public boolean overlaps(Rectangle r) {
		if (!getBoundingRectangle().overlaps(r)) return false;

		float dx = r.x - getX();
		float dy = r.y - getY();

		if (dy < 20) return true;
		if (r.contains(getX() + getWidth() / 2, getY() + getHeight())) return true;

		if (dx + r.getWidth() / 2 < getWidth() / 2)
			return dx + r.getWidth() + (getHeight() - dy) / 2 > (getWidth() / 2) + 15;
		else return dx - (getHeight() - dy) / 2 < (getWidth() / 2) - 15;
	}

	@Override
	public void adjustHealth(int value) {
		health += value;

		if (health > maxHealth) health = maxHealth;
		else if (health <= 0) app.gameScreen.game.endGame(false);
	}
}
