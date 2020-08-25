package io.thebitspud.astroenvoys.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.levels.*;
import io.thebitspud.astroenvoys.tools.JInputListener;

public class LevelSelectScreen implements Screen {
	private final AstroEnvoys app;
	private Stage stage;
	private Label levelID, levelTitle, levelDesc;
	private final ArrayList<Level> levels;
	private int cLevelIndex; // Current level index

	public LevelSelectScreen(AstroEnvoys app) {
		this.app = app;

		levels = new ArrayList<>();

		levels.add(new Level_1(app));
		//levels.add(new Level_Test(app));
		//levels.add(new Level_Win(app));
		//levels.add(new Level_Loss(app));

		cLevelIndex = 0;
	}

	@Override
	public void show() {
		app.setLastScreen(this);
		stage = new Stage(new ScreenViewport(new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())));
		Gdx.input.setInputProcessor(stage);

		initActors();
		updateLevelText();
	}

	private void initActors() {
		final int midX = Gdx.graphics.getWidth() / 2;

		Label title = new Label("Level\nSelect", app.assets.titleStyle);
		title.setPosition(midX - (title.getPrefWidth() / 2), Gdx.graphics.getHeight() * 0.80f);
		title.setAlignment(Align.center);

		levelID = new Label("", app.assets.subTitleStyle);
		levelID.setPosition(midX - (levelID.getPrefWidth() / 2), Gdx.graphics.getHeight() * 0.3f + 90);
		levelID.setAlignment(Align.center);

		levelTitle = new Label(" ", app.assets.subTitleStyle);
		levelTitle.setPosition(midX - (levelTitle.getPrefWidth() / 2), Gdx.graphics.getHeight() * 0.72f);
		levelTitle.setAlignment(Align.center);

		levelDesc = new Label(" ", app.assets.textStyle);
		levelDesc.setPosition(Gdx.graphics.getWidth() * 0.05f, Gdx.graphics.getHeight() * 0.67f);
		levelDesc.setWrap(true);
		levelDesc.setWidth(Gdx.graphics.getWidth() * 0.9f);
		levelDesc.setAlignment(Align.topLeft);

		ImageButton playButton = new ImageButton(app.assets.buttons[0][0], app.assets.buttons[0][1]);
		playButton.addListener(new JInputListener() {
			@Override
			public void onClick() {
				app.setScreen(app.gameScreen);
				app.gameScreen.game.init();
			}
		});
		playButton.setPosition(midX - 400, Gdx.graphics.getHeight() * 0.18f);

		ImageButton prevButton = new ImageButton(app.assets.buttons[10][0], app.assets.buttons[10][1]);
		prevButton.addListener(new JInputListener() {
			@Override
			public void onClick() {
				cLevelIndex--;
				if(cLevelIndex < 0) cLevelIndex = levels.size() - 1;
				updateLevelText();
			}
		});
		prevButton.setPosition(Gdx.graphics.getWidth() * 0.05f, Gdx.graphics.getHeight() * 0.30f);

		ImageButton nextButton = new ImageButton(app.assets.buttons[11][0], app.assets.buttons[11][1]);
		nextButton.addListener(new JInputListener() {
			@Override
			public void onClick() {
				incrLevelIndex();
			}
		});
		nextButton.setPosition((Gdx.graphics.getWidth() * 0.95f) - 180, Gdx.graphics.getHeight() * 0.30f);

		stage.addActor(title);
		stage.addActor(levelID);
		stage.addActor(levelTitle);
		stage.addActor(levelDesc);
		stage.addActor(playButton);
		stage.addActor(prevButton);
		stage.addActor(nextButton);
		app.addBackButton(stage, app.menuScreen);
		app.addLoadoutButton(stage);
		app.addSettingsButton(stage);
	}

	private void updateLevelText() {
		Level level = levels.get(cLevelIndex);

		levelID.setText(level.id());
		levelTitle.setText(level.title());
		levelDesc.setText(level.desc());

		if(level.getClass().equals(Level_Endless.class)) levelID.setColor(1, 1, 1, 1);
		else if(level.isCleared()) levelID.setColor(0.5f, 1, 0.5f, 1);
		else levelID.setColor(1, 0.5f, 0.5f, 1);
	}

	public Level getSelectedLevel() {
		return levels.get(cLevelIndex);
	}

	public void addLevel(Level level) {
		if(cLevelIndex < levels.size() - 1) levels.add(cLevelIndex + 1, level);
		else levels.add(level);
	}

	public void incrLevelIndex() {
		cLevelIndex++;
		if(cLevelIndex >= levels.size()) cLevelIndex = 0;

		updateLevelText();
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
