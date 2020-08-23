package io.thebitspud.astroenvoys.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.CampaignGame;
import io.thebitspud.astroenvoys.tools.InputManager;
import io.thebitspud.astroenvoys.tools.JInputListener;

public class GameScreen implements Screen {
	public CampaignGame game;
	private final AstroEnvoys app;
	private Stage hud;
	private Label healthIndicator, shieldIndicator;
	private OrthographicCamera camera;

	public GameScreen(AstroEnvoys app) {
		this.app = app;
		game = new CampaignGame(app);
	}

	@Override
	public void show() {
		InputManager gameInput = new InputManager(app, game.player);
		hud = new Stage(new ScreenViewport(camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())));
		InputMultiplexer multiplexer = new InputMultiplexer(hud, gameInput);
		Gdx.input.setInputProcessor(multiplexer);

		healthIndicator = new Label("", app.assets.subTitleStyle);
		healthIndicator.setPosition(Gdx.graphics.getWidth() * 0.05f, Gdx.graphics.getHeight() * 0.98f - 25);

		shieldIndicator = new Label("", app.assets.subTitleStyle);
		shieldIndicator.setPosition(Gdx.graphics.getWidth() * 0.35f, Gdx.graphics.getHeight() * 0.98f - 25);

		ImageButton pauseButton = new ImageButton(app.assets.buttons[12][0], app.assets.buttons[12][1]);
		pauseButton.addListener(new JInputListener() {
			@Override
			public void onClick() {
				app.setScreen(app.pauseScreen);
			}
		});
		pauseButton.setPosition(Gdx.graphics.getWidth() - 180, Gdx.graphics.getHeight() - 180);

		hud.addActor(healthIndicator);
		hud.addActor(shieldIndicator);
		hud.addActor(pauseButton);

		setHealthIndicatorText(game.player.getHealthPercent());
		setShieldIndicatorText(game.player.getShieldPercent());
	}

	public void setHealthIndicatorText(int percent) {
		healthIndicator.setText(percent + "%");
		healthIndicator.setColor((100 - percent) / 100f, percent / 100f, 0, 0.75f);
	}

	public void setShieldIndicatorText(int percent) {
		shieldIndicator.setText(percent + "%");
		float shieldOpacity = 0.40f + percent / 300f;
		if(percent == 0) shieldOpacity = 0.25f;
		shieldIndicator.setColor(0, 1, 1, shieldOpacity);
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