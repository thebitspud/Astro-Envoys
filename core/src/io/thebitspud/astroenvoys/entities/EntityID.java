package io.thebitspud.astroenvoys.entities;

public enum EntityID {
	PLAYER(Type.PLAYER, 0),
	ASTEROID(Type.ENEMY, 0),
	AZ_RAIDER(Type.ENEMY, 1),
	PLASMA_BOLT(Type.PROJECTILE, 0),
	ENERGY_SHOT(Type.PROJECTILE, 1);

	public enum Type {
		PLAYER,
		ENEMY,
		PROJECTILE
	}

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
}
