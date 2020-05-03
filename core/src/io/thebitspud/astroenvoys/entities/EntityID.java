package io.thebitspud.astroenvoys.entities;

public enum EntityID {
	PLAYER(Type.PLAYER, 0),

	ASTEROID(Type.ENEMY, 0),
	AZ_RAIDER(Type.ENEMY, 1),
	AZ_HUNTER(Type.ENEMY, 2),
	AZ_PREDATOR(Type.ENEMY, 3),

	ENERGY_SHOT(Type.PROJECTILE, 0),
	PLASMA_SHOT(Type.PROJECTILE, 1),
	HEAVY_PLASMA_SHOT(Type.PROJECTILE, 2);

	private final Type type;
	private final int numID;
	EntityID(Type type, int numID) {
		this.type = type;
		this.numID = numID;
	}

	public Type type() {
		return type;
	}

	public int numID() {
		return numID;
	}

	public enum Type {
		PLAYER,
		ENEMY,
		PROJECTILE
	}
}
