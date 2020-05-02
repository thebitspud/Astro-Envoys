package io.thebitspud.astroenvoys.entities.projectiles;

import com.badlogic.gdx.Gdx;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.entities.Entity;
import io.thebitspud.astroenvoys.entities.EntityID;

public class Projectile extends Entity {
	protected int pierce;
	private float xVel, yVel;
	private boolean canHitPlayers, canHitEnemies;

	public Projectile(int x, int y, int width, int height, int damage, float xVel, float yVel,
	                  boolean canHitPlayers, boolean canHitEnemies, EntityID id, AstroEnvoys app) {
		super(x, y, width, height, damage, id, app);

		this.xVel = xVel;
		this.yVel = yVel;
		this.canHitPlayers = canHitPlayers;
		this.canHitEnemies = canHitEnemies;
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

	public void checkForCollision(Entity e) {
		if ((canHitPlayers && e.getID().type() == EntityID.Type.PLAYER)
				|| (canHitEnemies && e.getID().type() == EntityID.Type.ENEMY)) {
			if (e.getBoundingRectangle().overlaps(this.getBoundingRectangle())) onHit(e);
		}
	}

	private void onHit(Entity e) {
		e.adjustHealth(-health);

		pierce -= 1;
		if (pierce < 0) adjustHealth(-health);
	}
}
