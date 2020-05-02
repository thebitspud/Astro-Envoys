package io.thebitspud.astroenvoys.entities.projectiles;

import com.badlogic.gdx.Gdx;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.entities.Entity;
import io.thebitspud.astroenvoys.entities.EntityID;
import io.thebitspud.astroenvoys.entities.Player;
import io.thebitspud.astroenvoys.entities.enemies.Enemy;

public class Projectile extends Entity {
	private float xVel, yVel;
	private boolean canHitPlayers, canHitEnemies;

	Projectile(int x, int y, int damage, float xVel, float yVel,
	           boolean canHitPlayers, boolean canHitEnemies, EntityID id, AstroEnvoys app) {
		super(x, y, damage, id, app);

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
		if (xVel > 0) {
			if (getX() > Gdx.graphics.getWidth()) kill();
		} else if (getX() + getWidth() < 0) kill();

		if (yVel > 0) {
			if (getY() + getHeight() > Gdx.graphics.getHeight()) kill();
		} else if (getY() < 0) kill();
	}

	public void checkForCollision(Enemy e) {
		if(!canHitEnemies) return;
		if (e.getBoundingRectangle().overlaps(this.getBoundingRectangle())) onHit(e);
	}

	public void checkForCollision(Player p) {
		if(!canHitPlayers) return;
		if (p.overlaps(getBoundingRectangle())) onHit(p);
	}

	private void onHit(Entity e) {
		e.adjustHealth(-health);
		adjustHealth(-health);
	}
}
