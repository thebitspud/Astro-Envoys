package io.thebitspud.astroenvoys.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.tools.JTimerUtil;
import io.thebitspud.astroenvoys.weapons.Weapon;

public class Player extends Entity {
	private Weapon primary, secondary;
	private float moveSpeed, desX, desY;
	private boolean moveIssued, shieldActive;
	private float shield, maxShield;
	private final JTimerUtil shieldRegen;

	public Player(AstroEnvoys app) {
		super(0, 0, 100, EntityID.PLAYER, app);

		shieldRegen = new JTimerUtil(0.5, true, false) {
			@Override
			public void onActivation() {
				if(shield < maxShield) {
					shield += maxShield / 100;
					app.gameScreen.setShieldIndicatorText(getShieldPercent());
				}
			}
		};

		shieldActive = false;
	}

	public void init() {
		maxHealth = 100;
		health = 100;
		maxShield = 25;
		moveSpeed = 1500;

		if(shieldActive) {
			shield = 25;
			shieldRegen.setTimeElapsed(0);
		} else shield = 0;

		primary = app.loadoutScreen.getSelectedPrimary();
		secondary = app.loadoutScreen.getSelectedSecondary();

		primary.init();
		secondary.init();

		setCenter(Gdx.graphics.getWidth() * 0.5f, Gdx.graphics.getHeight() * 0.25f);
		app.gameScreen.setHealthIndicatorText(getHealthPercent());
		app.gameScreen.setShieldIndicatorText(getShieldPercent());
	}

	@Override
	public void tick(float delta) {
		primary.tick(delta);
		secondary.tick(delta);
		shieldRegen.tick(delta);

		if(moveIssued) {
			move();
			moveIssued = false;
		}
	}

	// Triangle on rectangle collisions

	public boolean overlaps(Rectangle r) {
		if (!getBoundingRectangle().overlaps(r)) return false;

		float dx = r.getX() - getX(), dy = r.getY() - getY();

		if (dy < 20) return false;
		if (r.contains(getX() + getWidth() / 2, getY() + getHeight())) return true;

		if (dx + r.getWidth() / 2 < getWidth() / 2)
			return dx + r.getWidth() + (getHeight() - dy) / 2 > (getWidth() / 2) + 15;
		else return dx - (getHeight() - dy) / 2 < (getWidth() / 2) - 15;
	}

	@Override
	public void adjustHealth(int value) {
		if(value < 0) value = adjustShield(value);
		app.gameScreen.setShieldIndicatorText(getShieldPercent());

		health += value;
		app.gameScreen.setHealthIndicatorText(getHealthPercent());

		if (health > maxHealth) health = maxHealth;
		else if (health <= 0) app.gameScreen.game.endGame(false);
	}

	public void unlockShield() {
		shieldActive = true;
		shieldRegen.setActive(true);
	}

	private int adjustShield(int value) {
		if (shield != 0) shieldRegen.setTimeElapsed(-1.5);

		if(shield >= -value) {
			shield += value;
			return 0;
		} else {
			int adjValue = value + (int) shield;
			shield = 0;
			return adjValue;
		}
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

	public int getShieldPercent() {
		return Math.round(shield / maxShield * 100);
	}

	public boolean isShieldActive() {
		return shieldActive;
	}
}