package io.thebitspud.astroenvoys.entities;

import com.badlogic.gdx.math.Rectangle;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.tools.InputManager;

public class Player extends Entity {
	private InputManager input;

	public Player(int x, int y, AstroEnvoys app) {
		super(x, y, 200, 200, 250, EntityID.PLAYER, app);

		input = new InputManager(app, this);
	}

	@Override
	public void tick(float delta) {
		getInput(delta);
	}

	private void getInput(float delta) {

	}

	// Triangle on rectangle collisions

	@Override
	public boolean overlaps(Rectangle r) {
		if(!super.overlaps(r)) return false;

		float dx = r.x - x;
		float dy = r.y - y;

		if(dy < 20) return true;
		if(r.contains(x + width / 2, y + height)) return  true;

		if(dx + r.width / 2 < width / 2) return dx + r.width + (height - dy) / 2 > (width / 2) + 20;
		else return dx - (height - dy) / 2 < (width / 2) - 20;
	}
}
