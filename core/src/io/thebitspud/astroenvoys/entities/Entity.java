package io.thebitspud.astroenvoys.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import io.thebitspud.astroenvoys.AstroEnvoys;

public abstract class Entity extends Rectangle {
	protected AstroEnvoys app;
	protected TextureRegion texture;
	protected int health, maxHealth;
	private boolean active;
	protected EntityID id;

	public Entity(int x, int y, int width, int height, int health, EntityID id, AstroEnvoys app) {
		super(x, y, width, height);

		this.app = app;
		this.active = true;
		this.maxHealth = health;
		this.health = health;
		this.id = id;
	}

	public abstract void tick(float delta);

	public void render() {
		if (texture == null) texture = app.assets.getTexture(id);
		else app.batch.draw(texture, x, y, width, height);
	}

	public void adjustHealth(int value) {
		health += value;

		if (health > maxHealth) health = maxHealth;
		else if (health <= 0) {
			health = 0;
			active = false;
		}
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public EntityID getID() {
		return id;
	}
}