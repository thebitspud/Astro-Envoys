package io.thebitspud.astroenvoys.entities.enemies;

import com.badlogic.gdx.Gdx;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.entities.Entity;
import io.thebitspud.astroenvoys.entities.EntityID;

public abstract class Enemy extends Entity {
	private float xVel, yVel;

	public Enemy(int x, int y, int width, int height, float xVel, float yVel, int health, EntityID id, AstroEnvoys app) {
		super(x, y, width, height, health, id, app);

		this.xVel = xVel;
		this.yVel = yVel;
	}

	@Override
	public void tick(float delta) {
		translate(xVel * delta, yVel * delta);
		checkBounds();
	}

	private void checkBounds() {
		if (xVel > 0) if (getX() > Gdx.graphics.getWidth()) kill();
		else if (getX() + getWidth() < 0) kill();

		if (yVel > 0) if (getY() > Gdx.graphics.getHeight()) kill();
		else if (getY() + health < 0) kill();
	}
}