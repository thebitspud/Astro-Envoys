package io.thebitspud.astroenvoys.entities.enemies;

import com.badlogic.gdx.Gdx;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.entities.Entity;
import io.thebitspud.astroenvoys.entities.EntityID;

public abstract class Enemy extends Entity {
	float xVel, yVel;
	int cYOffset; // Circle collision detection offset

	Enemy(int x, int y, float xVel, float yVel, int health, EntityID id, AstroEnvoys app) {
		super(x, y, health, id, app);

		this.xVel = xVel;
		this.yVel = yVel;
	}

	@Override
	public void tick(float delta) {
		translate(xVel * delta, yVel * delta);
		if(getX() <= 0 || getX() + getWidth() > Gdx.graphics.getWidth()) xVel = -xVel;
		checkBounds();

		tickAI(delta);
	}

	protected abstract void tickAI(float delta);

	public boolean circleContains(float x, float y) {
		float dx = x - (getX() + getWidth() / 2);
		float dy = y - (getY() + getHeight() / 2 + cYOffset);
		double hyp = Math.hypot(dx, dy);
		float rad = getWidth() / 2;

		return hyp < rad;
	}

	private void checkBounds() {
		if (xVel > 0) {
			if (getX() > Gdx.graphics.getWidth()) kill();
		} else if (getX() + getWidth() < 0) kill();

		if (yVel > 0) {
			if (getY() > Gdx.graphics.getHeight()) kill();
		} else if (getY() + getHeight() < 0) kill();
	}
}