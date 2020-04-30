package io.thebitspud.astroenvoys;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

import io.thebitspud.astroenvoys.screens.*;
import io.thebitspud.astroenvoys.tools.AssetLibrary;
import io.thebitspud.astroenvoys.tools.JInputListener;

public class AstroEnvoys extends Game {
	public SpriteBatch batch;
	public AssetLibrary assets;

	public Screen menuScreen, gameScreen, winScreen, lossScreen, levelSelectScreen;
	private Screen settingsScreen, loadoutScreen, lastScreen;
	
	@Override
	public void create () {
		assets = new AssetLibrary();
		batch = new SpriteBatch();

		assets.loadAll();

		menuScreen = new MenuScreen(this);
		gameScreen = new GameScreen(this);
		winScreen = new WinScreen(this);
		lossScreen = new LossScreen(this);
		levelSelectScreen = new LevelSelectScreen(this);
		settingsScreen = new SettingsScreen(this);
		loadoutScreen = new LoadoutScreen(this);

		setScreen(menuScreen);
	}

	public void setLastScreen(Screen screen) {
		lastScreen = screen;
	}

	public Screen getLastScreen() {
		return lastScreen;
	}

	@Override
	public void render () {
		super.render();
	}

	public void renderStage(Stage stage) {
		Gdx.gl.glClearColor(0, 0, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		stage.draw();
	}

	public void addSettingsButton(Stage stage) {
		ImageButton settingsButton = new ImageButton(assets.buttons[1][0], assets.buttons[1][1]);
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
		ImageButton backButton = new ImageButton(assets.buttons[2][0], assets.buttons[2][1]);
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
		ImageButton loadoutButton = new ImageButton(assets.buttons[4][0], assets.buttons[4][1]);
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
	public void dispose () {
		batch.dispose();
		assets.dispose();
	}
}
