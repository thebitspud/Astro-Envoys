package io.thebitspud.astroenvoys.entities;

import com.badlogic.gdx.math.Rectangle;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.tools.InputManager;

public class Player extends Entity {
	private InputManager input;

	public Player(int x, int y, AstroEnvoys app) {
		super(x, y, 180, 180, 250, EntityID.PLAYER, app);

		input = new InputManager(app, this);
	}

	@Override
	public void tick(float delta) {
		getInput(delta);
	}

	private void getInput(float delta) {

	}

	// Triangle on rectangle collisions

	public boolean overlaps(Rectangle r) {
		if (getBoundingRectangle().overlaps(r)) return false;

		float dx = r.x - getX();
		float dy = r.y - getY();

		if (dy < 20) return true;
		if (r.contains(getX() + getWidth() / 2, getY() + getHeight())) return true;

		if (dx + r.width / 2 < getWidth() / 2)
			return dx + r.width + (getHeight() - dy) / 2 > (getWidth() / 2) + 15;
		else return dx - (getHeight() - dy) / 2 < (getWidth() / 2) - 15;
	}
}
