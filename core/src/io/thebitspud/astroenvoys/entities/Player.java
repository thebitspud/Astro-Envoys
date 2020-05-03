package io.thebitspud.astroenvoys.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.tools.JTimerUtil;

public class Player extends Entity {
	private JTimerUtil attackTimer;
	private float moveSpeed, desX, desY;
	private Random r;
	private boolean moveIssued;

	public Player(AstroEnvoys app) {
		super(0, 0, 100, EntityID.PLAYER, app);

		r = new Random();
	}

	public void init() {
		maxHealth = 100;
		health = 100;
		moveSpeed = 1500;
		setCenter(Gdx.graphics.getWidth() * 0.5f, Gdx.graphics.getHeight() * 0.25f);

		attackTimer = new JTimerUtil(0.18, true, true) {
			@Override
			public void onActivation() {
				app.gameScreen.game.addProjectile((int) getX() + 77, (int) getY() + 50,
						r.nextInt(100) - 50, 1500, EntityID.PLASMA_BOLT);
			}
		};
	}

	@Override
	public void tick(float delta) {
		attackTimer.tick(delta);
		if(moveIssued) {
			move();
			moveIssued = false;
		}
	}

	// Triangle on rectangle collisions

	public boolean overlaps(Rectangle r) {
		if (!getBoundingRectangle().overlaps(r)) return false;

		float dx = r.x - getX(), dy = r.y - getY();

		if (dy < 20) return true;
		if (r.contains(getX() + getWidth() / 2, getY() + getHeight())) return true;

		if (dx + r.getWidth() / 2 < getWidth() / 2)
			return dx + r.getWidth() + (getHeight() - dy) / 2 > (getWidth() / 2) + 15;
		else return dx - (getHeight() - dy) / 2 < (getWidth() / 2) - 15;
	}

	@Override
	public void adjustHealth(int value) {
		health += value;
		app.gameScreen.setHealthIndicatorText(getHealthText());

		if (health > maxHealth) health = maxHealth;
		else if (health <= 0) app.gameScreen.game.endGame(false);
	}

	public void setDesignation(float x, float y) {
		this.moveIssued = true;
		this.desX = x;
		this.desY = y;
	}

	private void move() {
		float dx = desX - (getX() + getWidth() / 2);
		float dy = desY - (getY() + getHeight() / 2);
		float delta = Gdx.graphics.getDeltaTime();
		double hyp = Math.hypot(dx, dy);

		if(hyp < moveSpeed * delta) setCenter(desX, desY);
		else {
			float scale = (float) (moveSpeed * delta / hyp);
			translate(dx * scale,  dy * scale);
		}
	}

	public String getHealthText() {
		return Math.round((float) health / maxHealth * 100) + "%";
	}
}
