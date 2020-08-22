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
	private AstroEnvoys app;
	private Stage stage;
	private Label levelID, levelTitle, levelDesc;
	private ArrayList<Level> levels;
	private int cLevelIndex; // Current level index

	public LevelSelectScreen(AstroEnvoys app) {
		this.app = app;

		levels = new ArrayList<>();

		levels.add(new Level_Endless(app.gameScreen.game));
		levels.add(new Level_1(app.gameScreen.game));
		levels.add(new Level_2(app.gameScreen.game));
		levels.add(new Level_3(app.gameScreen.game));
		levels.add(new Level_4(app.gameScreen.game));
		levels.add(new Level_Win(app.gameScreen.game));
		levels.add(new Level_Loss(app.gameScreen.game));

		cLevelIndex = 1;
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

		levelID = new Label(" ", app.assets.subTitleStyle);
		levelID.setPosition(midX - (levelID.getPrefWidth() / 2), Gdx.graphics.getHeight() * 0.322f);
		levelID.setAlignment(Align.center);

		levelTitle = new Label(" ", app.assets.subTitleStyle);
		levelTitle.setPosition(midX - (levelTitle.getPrefWidth() / 2), Gdx.graphics.getHeight() * 0.72f);
		levelTitle.setAlignment(Align.center);

		levelDesc = new Label(" ", app.assets.textStyle);
		levelDesc.setPosition(Gdx.graphics.getWidth() * 0.1f, Gdx.graphics.getHeight() * 0.67f);
		levelDesc.setWrap(true);
		levelDesc.setWidth(Gdx.graphics.getWidth() * 0.8f);
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
		levelID.setText(levels.get(cLevelIndex).id());
		levelTitle.setText(levels.get(cLevelIndex).title());
		levelDesc.setText(levels.get(cLevelIndex).desc());
	}

	public Level getSelectedLevel() {
		return levels.get(cLevelIndex);
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
