package io.thebitspud.astroenvoys.entities.projectiles;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.entities.Entity;
import io.thebitspud.astroenvoys.entities.EntityID;

public class Projectile extends Entity {
	private float xVel, yVel;
	protected int pierce;
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
		x += (xVel * delta);
		y += (yVel * delta);
	}

	public void checkForCollision(Entity e) {
		if((canHitPlayers && e.getID().type() == EntityID.Type.PLAYER)
		|| (canHitEnemies && e.getID().type() == EntityID.Type.ENEMY)) {
			if (e.overlaps(this)) onHit(e);
		}
	}

	private void onHit(Entity e) {
		e.adjustHealth(-health);

		pierce -= 1;
		if(pierce < 0) adjustHealth(-health);
	}
}
