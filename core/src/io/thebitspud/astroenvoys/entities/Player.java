package io.thebitspud.astroenvoys.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.weapons.PlasmaB;
import io.thebitspud.astroenvoys.weapons.ScatterB;
import io.thebitspud.astroenvoys.weapons.Weapon;

public class Player extends Entity {
	private Weapon primary, secondary;
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

		primary = app.loadoutScreen.getSelectedPrimary();
		secondary = app.loadoutScreen.getSelectedSecondary();

		primary.init();
		secondary.init();
	}

	@Override
	public void tick(float delta) {
		primary.tick(delta);
		secondary.tick(delta);

		if(moveIssued) {
			move();
			moveIssued = false;
		}
	}

	// Triangle on rectangle collisions

	public boolean overlaps(Rectangle r) {
		if (!getBoundingRectangle().overlaps(r)) return false;

		float dx = r.getX() - getX(), dy = r.getY() - getY();

		if (dy < 20) return true;
		if (r.contains(getX() + getWidth() / 2, getY() + getHeight())) return true;

		if (dx + r.getWidth() / 2 < getWidth() / 2)
			return dx + r.getWidth() + (getHeight() - dy) / 2 > (getWidth() / 2) + 15;
		else return dx - (getHeight() - dy) / 2 < (getWidth() / 2) - 15;
	}

	@Override
	public void adjustHealth(int value) {
		health += value;
		app.gameScreen.setHealthIndicatorText(getHealthPercent());

		if (health > maxHealth) health = maxHealth;
		else if (health <= 0) app.gameScreen.game.endGame(false);
	}

	public void setDestination(float x, float y) {
		this.moveIssued = true;
		this.desX = x;
		this.desY = y;

		if(desX > Gdx.graphics.getWidth() - getWidth() / 3) desX = Gdx.graphics.getWidth() - getWidth() / 3;
		else if (desX < 0 + getWidth() / 3) desX = getWidth() / 3;

		if(desY > Gdx.graphics.getHeight()) desY = Gdx.graphics.getHeight();
		if(desY < getHeight() / 3) desY = getHeight() / 3;
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

	public int getHealthPercent() {
		return Math.round((float) health / maxHealth * 100);
	}
}
