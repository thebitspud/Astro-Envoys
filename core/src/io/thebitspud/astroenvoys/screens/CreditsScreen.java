package io.thebitspud.astroenvoys.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import io.thebitspud.astroenvoys.AstroEnvoys;

public class CreditsScreen implements Screen {
	private final AstroEnvoys app;
	private Stage stage;

	public CreditsScreen(AstroEnvoys app) {
		this.app = app;
	}

	@Override
	public void show() {
		stage = new Stage(new ScreenViewport(new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())));
		Gdx.input.setInputProcessor(stage);

		Label title = new Label("Credits", app.assets.smallTitleStyle);
		title.setPosition((Gdx.graphics.getWidth() - title.getPrefWidth()) / 2, Gdx.graphics.getHeight() * 0.86f);
		title.setAlignment(Align.center);

		Label creditRoll = new Label("Programming:\nThebitspud\n\nGame Design:\nThebitspud\n\nAssets: itch.io," +
				"\nOpenGameArt,\nMillionthVector", app.assets.subTitleStyle);
		creditRoll.setPosition(Gdx.graphics.getWidth() * 0.1f, Gdx.graphics.getHeight() * 0.45f);

		stage.addActor(title);
		stage.addActor(creditRoll);
		app.addBackButton(stage, app.getLastScreen());
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
