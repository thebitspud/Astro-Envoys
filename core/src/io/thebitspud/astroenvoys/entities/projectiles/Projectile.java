package io.thebitspud.astroenvoys.entities.projectiles;

import com.badlogic.gdx.Gdx;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.entities.Entity;
import io.thebitspud.astroenvoys.entities.EntityID;
import io.thebitspud.astroenvoys.entities.Player;
import io.thebitspud.astroenvoys.entities.enemies.Enemy;

public class Projectile extends Entity {
	private final float xVel, yVel;
	private final boolean hitPlayers, hitEnemies;

	public Projectile(int x, int y, float xVel, float yVel, int damage, boolean isPlayerProjectile, EntityID id, AstroEnvoys app) {
		super(x, y, damage, id, app);

		this.xVel = xVel;
		this.yVel = yVel;

		if (isPlayerProjectile) {
			hitEnemies = true;
			hitPlayers = false;
		} else {
			hitEnemies = false;
			hitPlayers = true;
		}

		final double degrees = (Math.atan2(-xVel, yVel) * 180.0 / Math.PI);

		setRotation((float) degrees);
	}

	@Override
	public void tick(float delta) {
		translate(xVel * delta, yVel * delta);
		checkBounds();
	}

	private void checkBounds() {
		if (getX() > Gdx.graphics.getWidth()) kill();
		if (getX() + getWidth() < 0) kill();
		if (getY() > Gdx.graphics.getHeight()) kill();
		if (getY() + getHeight() < 0) kill();
	}

	public void checkForCollision(Enemy e) {
		if(!hitEnemies && !(e.getID() == EntityID.ASTEROID)) return;
		if(yVel < 0) {
			if(e.circleContains(getX() + getWidth() / 2, getY())) onHit(e);
		} else if(e.circleContains(getX() + getWidth() / 2, getY() + getHeight())) onHit(e);
	}

	public void checkForCollision(Player p) {
		if(!hitPlayers) return;
		if (p.overlaps(getBoundingRectangle())) onHit(p);
	}

	private void onHit(Entity e) {
		e.adjustHealth(-health);
		adjustHealth(-health);
	}
}