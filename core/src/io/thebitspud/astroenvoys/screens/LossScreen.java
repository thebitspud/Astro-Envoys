package io.thebitspud.astroenvoys.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.tools.JInputListener;

public class LossScreen implements Screen {
	private AstroEnvoys app;
	private Stage stage;

	public LossScreen(AstroEnvoys app) {
		this.app = app;
	}

	@Override
	public void show() {
		app.setLastScreen(this);
		stage = new Stage(new ScreenViewport(new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())));
		Gdx.input.setInputProcessor(stage);

		final int midX = Gdx.graphics.getWidth() / 2;

		Label title = new Label("Level\nFailed", app.assets.titleStyle);
		title.setPosition(midX - (title.getPrefWidth() / 2), Gdx.graphics.getHeight() * 0.75f);
		title.setAlignment(Align.center);

		ImageButton playButton = new ImageButton(app.assets.buttons[1][0], app.assets.buttons[1][1]);
		playButton.addListener(new JInputListener() {
			@Override
			public void onClick() {
				app.setScreen(app.gameScreen);
				app.gameScreen.game.init();
			}
		});
		playButton.setPosition(midX - 400, Gdx.graphics.getHeight() * 0.6f);

		ImageButton quitButton = new ImageButton(app.assets.buttons[5][0], app.assets.buttons[5][1]);
		quitButton.addListener(new JInputListener() {
			@Override
			public void onClick() {
				app.setScreen(app.levelSelectScreen);
			}
		});
		quitButton.setPosition(midX - 400, Gdx.graphics.getHeight() * 0.45f);

		stage.addActor(title);
		stage.addActor(playButton);
		stage.addActor(quitButton);
		app.addLoadoutButton(stage);
		app.addSettingsButton(stage);
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
