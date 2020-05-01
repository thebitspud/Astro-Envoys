package io.thebitspud.astroenvoys.entities.enemies;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.entities.Entity;
import io.thebitspud.astroenvoys.entities.EntityID;
import io.thebitspud.astroenvoys.entities.Player;

public abstract class Enemy extends Entity {
	private float xVel, yVel;
	private int damage;

	public Enemy(int x, int y, int width, int height, float xVel, float yVel, int health, EntityID id, AstroEnvoys app) {
		super(x, y, width, height, health, id, app);

		this.xVel = xVel;
		this.yVel = yVel;

		damage = health / 4;
	}

	public void checkForCollision(Player p) {
		if (overlaps(p)) {
			p.adjustHealth(-damage);
			setActive(false);
		}
	}

	@Override
	public void tick(float delta) {
		x += (xVel * delta);
		y += (yVel * delta);
	}
}