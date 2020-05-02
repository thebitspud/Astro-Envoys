package io.thebitspud.astroenvoys.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.CampaignGame;
import io.thebitspud.astroenvoys.tools.JInputListener;

public class GameScreen implements Screen {
	public CampaignGame game;
	private AstroEnvoys app;
	private Stage hud;
	private OrthographicCamera camera;

	public GameScreen(AstroEnvoys app) {
		this.app = app;
		game = new CampaignGame(app);
	}

	@Override
	public void show() {
		hud = new Stage(new ScreenViewport(camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())));
		Gdx.input.setInputProcessor(hud);

		ImageButton pauseButton = new ImageButton(app.assets.buttons[12][0], app.assets.buttons[12][1]);
		pauseButton.addListener(new JInputListener() {
			@Override
			public void onClick() {
				app.setScreen(app.pauseScreen);
			}
		});
		pauseButton.setPosition(Gdx.graphics.getWidth() - 180, Gdx.graphics.getHeight() - 180);

		hud.addActor(pauseButton);

		game.init();
	}

	@Override
	public void render(float delta) {
		camera.update();
		app.batch.setProjectionMatrix(camera.combined);

		Gdx.gl.glClearColor(0, 0f, 0.05f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		hud.act();
		game.tick(delta);

		app.batch.begin();
		game.render();
		app.batch.end();
		hud.draw();
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
		hud.dispose();
	}
}
