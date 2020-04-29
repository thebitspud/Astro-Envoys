package io.thebitspud.astroenvoys.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.tools.JInputListener;
import io.thebitspud.astroenvoys.tools.JTextButton;

public class WinScreen implements Screen {
	private AstroEnvoys app;
	private Stage stage;

	public WinScreen(AstroEnvoys app) {
		this.app = app;
	}

	@Override
	public void show() {
		stage = new Stage(new ScreenViewport(new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())));
		Gdx.input.setInputProcessor(stage);

		final int midX = Gdx.graphics.getWidth() / 2, midY = Gdx.graphics.getHeight() / 2;

		Label title = new Label("Level Cleared!", app.assets.titleFont);
		title.setPosition(midX - (title.getPrefWidth() / 2), midY);
		title.setAlignment(Align.center);

		JTextButton playButton = new JTextButton(midX, midY - 100, JTextButton.Size.LARGE, "Menu", app.assets.qHorizon);
		playButton.addListener(new JInputListener() {
			@Override
			public void onClick() {
				app.setScreen(app.menuScreen);
			}
		});

		stage.addActor(title);
		stage.addActor(playButton);
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
