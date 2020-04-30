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

public class MenuScreen implements Screen {
	private AstroEnvoys app;
	private Stage stage;
	private OrthographicCamera camera;

	public MenuScreen(AstroEnvoys app) {
		this.app = app;
	}

	@Override
	public void show() {
		app.setLastScreen(this);
		stage = new Stage(new ScreenViewport(camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())));
		Gdx.input.setInputProcessor(stage);

		final int midX = Gdx.graphics.getWidth() / 2;

		Label title = new Label("Astro\nEnvoys", app.assets.titleStyle);
		title.setPosition(midX - (title.getPrefWidth() / 2), Gdx.graphics.getHeight() * 0.75f);
		title.setAlignment(Align.center);

		ImageButton playButton = new ImageButton(app.assets.buttons[0][0], app.assets.buttons[0][1]);
		playButton.addListener(new JInputListener() {
			@Override
			public void onClick() {
				app.setScreen(app.levelSelectScreen);
			}
		});
		playButton.setPosition(midX - 400, Gdx.graphics.getHeight() * 0.4f);

		ImageButton aboutButton = new ImageButton(app.assets.buttons[3][0], app.assets.buttons[3][1]);
		aboutButton.addListener(new JInputListener() {
			@Override
			public void onClick() {
				app.setScreen(app.lossScreen);
			}
		});
		aboutButton.setPosition(Gdx.graphics.getWidth() * 0.05f, Gdx.graphics.getWidth() * 0.05f);

		stage.addActor(title);
		stage.addActor(playButton);
		stage.addActor(aboutButton);
		app.addLoadoutButton(stage);
		app.addSettingsButton(stage);
	}

	@Override
	public void render(float delta) {
		camera.update();
		app.batch.setProjectionMatrix(camera.combined);
		app.renderStage(stage);

		app.batch.begin();
		app.batch.draw(app.assets.shuttle, (float) Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() * 0.55f);

		app.batch.end();
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
