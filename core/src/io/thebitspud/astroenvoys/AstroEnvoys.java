package io.thebitspud.astroenvoys;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import io.thebitspud.astroenvoys.screens.GameScreen;
import io.thebitspud.astroenvoys.screens.LevelSelectScreen;
import io.thebitspud.astroenvoys.screens.LossScreen;
import io.thebitspud.astroenvoys.screens.MenuScreen;
import io.thebitspud.astroenvoys.screens.SettingsScreen;
import io.thebitspud.astroenvoys.screens.WinScreen;
import io.thebitspud.astroenvoys.tools.AssetLibrary;

public class AstroEnvoys extends Game {
	public SpriteBatch batch;
	public AssetLibrary assets;

	public Screen menuScreen, gameScreen, winScreen, lossScreen, levelSelectScreen, settingsScreen;
	
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
	}

	@Override
	public void render () {
		if (assets.update()) {
			assets.assignTextures();
			assets.assignSkins();
		}

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(assets.missing, 0, 0);
		batch.draw(assets.missing, 200, 0);
		batch.draw(assets.missing, 400, 0);
		batch.draw(assets.missing, 600, 0);
		batch.draw(assets.missing, 200, 400);
		batch.draw(assets.missing, 200, 800);
		batch.draw(assets.missing, 200, 1200);
		batch.end();
	}

	public void renderStage(Stage stage) {
		Gdx.gl.glClearColor(0, 0, 0.05f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		stage.draw();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		assets.dispose();
	}
}
