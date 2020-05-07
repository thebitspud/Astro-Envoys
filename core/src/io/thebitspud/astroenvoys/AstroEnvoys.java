package io.thebitspud.astroenvoys;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

import io.thebitspud.astroenvoys.screens.CreditsScreen;
import io.thebitspud.astroenvoys.screens.GameScreen;
import io.thebitspud.astroenvoys.screens.LevelSelectScreen;
import io.thebitspud.astroenvoys.screens.LoadoutScreen;
import io.thebitspud.astroenvoys.screens.LossScreen;
import io.thebitspud.astroenvoys.screens.MenuScreen;
import io.thebitspud.astroenvoys.screens.PauseScreen;
import io.thebitspud.astroenvoys.screens.SettingsScreen;
import io.thebitspud.astroenvoys.screens.WinScreen;
import io.thebitspud.astroenvoys.tools.AssetLibrary;
import io.thebitspud.astroenvoys.tools.JInputListener;

public class AstroEnvoys extends Game {
	public SpriteBatch batch;
	public AssetLibrary assets;

	public GameScreen gameScreen;
	public LevelSelectScreen levelSelectScreen;
	public Screen menuScreen, creditsScreen, pauseScreen, winScreen, lossScreen;
	private Screen settingsScreen, loadoutScreen, lastScreen;

	@Override
	public void create() {
		assets = new AssetLibrary();
		batch = new SpriteBatch();

		assets.loadAll();

		menuScreen = new MenuScreen(this);
		gameScreen = new GameScreen(this);
		levelSelectScreen = new LevelSelectScreen(this);
		winScreen = new WinScreen(this);
		lossScreen = new LossScreen(this);
		settingsScreen = new SettingsScreen(this);
		loadoutScreen = new LoadoutScreen(this);
		creditsScreen = new CreditsScreen(this);
		pauseScreen = new PauseScreen(this);

		setScreen(menuScreen);
	}

	public Screen getLastScreen() {
		return lastScreen;
	}

	public void setLastScreen(Screen screen) {
		lastScreen = screen;
	}

	@Override
	public void render() {
		super.render();
	}

	public void renderStage(Stage stage) {
		Gdx.gl.glClearColor(0, 0, 0.05f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		stage.draw();
	}

	public void addSettingsButton(Stage stage) {
		ImageButton settingsButton = new ImageButton(assets.buttons[6][0], assets.buttons[6][1]);
		settingsButton.addListener(new JInputListener() {
			@Override
			public void onClick() {
				setScreen(settingsScreen);
			}
		});
		settingsButton.setPosition((Gdx.graphics.getWidth() * 0.95f) - 180, Gdx.graphics.getWidth() * 0.05f);

		stage.addActor(settingsButton);
	}

	public void addBackButton(Stage stage, Screen nextScreen) {
		ImageButton backButton = new ImageButton(assets.buttons[7][0], assets.buttons[7][1]);
		backButton.addListener(new JInputListener() {
			@Override
			public void onClick() {
				setScreen(nextScreen);
			}
		});
		backButton.setPosition(Gdx.graphics.getWidth() * 0.05f, Gdx.graphics.getWidth() * 0.05f);

		stage.addActor(backButton);
	}

	public void addLoadoutButton(Stage stage) {
		ImageButton loadoutButton = new ImageButton(assets.buttons[9][0], assets.buttons[9][1]);
		loadoutButton.addListener(new JInputListener() {
			@Override
			public void onClick() {
				setScreen(loadoutScreen);
			}
		});
		loadoutButton.setPosition(Gdx.graphics.getWidth() * 0.5f - 90, Gdx.graphics.getWidth() * 0.05f);

		stage.addActor(loadoutButton);
	}

	@Override
	public void dispose() {
		batch.dispose();
		assets.dispose();
	}
}
