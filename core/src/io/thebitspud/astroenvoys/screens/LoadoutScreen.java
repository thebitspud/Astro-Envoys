package io.thebitspud.astroenvoys.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.entities.EntityID;
import io.thebitspud.astroenvoys.tools.JInputListener;

public class LoadoutScreen implements Screen {
	private AstroEnvoys app;
	private Stage stage;
	private Camera camera;

	public LoadoutScreen(AstroEnvoys app) {
		this.app = app;
	}

	@Override
	public void show() {
		stage = new Stage(new ScreenViewport(camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())));
		Gdx.input.setInputProcessor(stage);

		Label title = new Label("Loadout", app.assets.smallTitleStyle);
		title.setPosition((Gdx.graphics.getWidth() - title.getPrefWidth()) / 2, Gdx.graphics.getHeight() * 0.86f);
		title.setAlignment(Align.center);

		Label shipName = new Label("Basic Fighter", app.assets.subTitleStyle);
		shipName.setPosition((Gdx.graphics.getWidth() - shipName.getPrefWidth()) / 2, Gdx.graphics.getHeight() * 0.75f);
		shipName.setAlignment(Align.center);

		ImageButton prevButton = new ImageButton(app.assets.buttons[10][0], app.assets.buttons[10][1]);
		prevButton.addListener(new JInputListener() {
			@Override
			public void onClick() {

			}
		});
		prevButton.setPosition(Gdx.graphics.getWidth() * 0.05f, Gdx.graphics.getHeight() * 0.6f);

		ImageButton nextButton = new ImageButton(app.assets.buttons[11][0], app.assets.buttons[11][1]);
		nextButton.addListener(new JInputListener() {
			@Override
			public void onClick() {

			}
		});
		nextButton.setPosition((Gdx.graphics.getWidth() * 0.95f) - 180, Gdx.graphics.getHeight() * 0.6f);

		stage.addActor(title);
		stage.addActor(shipName);
		stage.addActor(prevButton);
		stage.addActor(nextButton);
		app.addBackButton(stage, app.getLastScreen());
	}

	@Override
	public void render(float delta) {
		camera.update();
		app.batch.setProjectionMatrix(camera.combined);
		app.renderStage(stage);

		app.batch.begin();
		app.batch.draw(app.assets.getTexture(EntityID.PLAYER), (float) Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() * 0.6f);

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
