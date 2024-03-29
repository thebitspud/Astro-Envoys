package io.thebitspud.astroenvoys.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.entities.Player;

public class InputManager implements InputProcessor {
	private final AstroEnvoys app;
	private final Player player;

	public InputManager(AstroEnvoys app, Player player) {
		this.app = app;
		this.player = player;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		player.setDestination(screenX, Gdx.graphics.getHeight() - screenY);

		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		player.setDestination(screenX, Gdx.graphics.getHeight() - screenY);

		return true;
	}

	// Irrelevant

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
