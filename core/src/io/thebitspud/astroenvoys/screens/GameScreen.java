package io.thebitspud.astroenvoys.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

import io.thebitspud.astroenvoys.AstroEnvoys;

public class GameScreen implements Screen {
	private AstroEnvoys app;
	private Stage stage;

	public GameScreen(AstroEnvoys app) {
		this.app = app;
	}


	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		app.renderStage(stage);
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		stage.dispose();
	}
}
