package io.thebitspud.astroenvoys.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;

import io.thebitspud.astroenvoys.AstroEnvoys;

public abstract class Entity extends Sprite {
	protected AstroEnvoys app;
	protected int health, maxHealth;
	private final EntityID id;
	private boolean active;

	public Entity(int x, int y, int health, EntityID id, AstroEnvoys app) {
		super(app.assets.getTexture(id));

		setPosition(x, y);

		this.app = app;
		this.active = true;
		this.maxHealth = health;
		this.health = health;
		this.id = id;
	}

	public abstract void tick(float delta);

	public void adjustHealth(int value) {
		health += value;

		if (health > maxHealth) health = maxHealth;
		else if (health <= 0) {
			health = 0;
			active = false;
		}
	}

	public EntityID getID() {
		return id;
	}

	public boolean isDead() {
		return !active;
	}

	protected void kill() {
		this.active = false;
	}
}