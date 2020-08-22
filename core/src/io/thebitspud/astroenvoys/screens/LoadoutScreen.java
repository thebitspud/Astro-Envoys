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
import io.thebitspud.astroenvoys.CampaignGame;
import io.thebitspud.astroenvoys.tools.JInputListener;
import io.thebitspud.astroenvoys.weapons.*;

import java.util.ArrayList;

public class LoadoutScreen implements Screen {
	private AstroEnvoys app;
	private Stage stage;
	private Camera camera;
	private ArrayList<Weapon> primaries, secondaries;
	private int cPrimaryIndex, cSecondaryIndex; // Current primary/secondary weapon index

	public LoadoutScreen(AstroEnvoys app) {
		this.app = app;
		initWeapons();
	}

	private void initWeapons() {
		final CampaignGame game = app.gameScreen.game;

		primaries = new ArrayList<>();
		secondaries = new ArrayList<>();

		primaries.add(new PlasmaA(game));
		primaries.add(new HeavyA(game));
		primaries.add(new PlasmaB(game));

		secondaries.add(new NoWeapon(game));
		secondaries.add(new ScatterA(game));
		secondaries.add(new ScatterB(game));

		cPrimaryIndex = 0;
		cSecondaryIndex = 0;
	}

	@Override
	public void show() {
		stage = new Stage(new ScreenViewport(camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())));
		Gdx.input.setInputProcessor(stage);

		Label title = new Label("Loadout", app.assets.smallTitleStyle);
		title.setPosition((Gdx.graphics.getWidth() - title.getPrefWidth()) / 2, Gdx.graphics.getHeight() * 0.86f);
		title.setAlignment(Align.center);

		initPrimarySelector();
		initSecondarySelector();

		stage.addActor(title);
		app.addBackButton(stage, app.getLastScreen());
	}

	private void initPrimarySelector() {
		Label primaryText = new Label("Primary", app.assets.subTitleStyle);
		primaryText.setPosition((Gdx.graphics.getWidth() - primaryText.getPrefWidth()) / 2,
				Gdx.graphics.getHeight() * 0.77f);
		primaryText.setAlignment(Align.center);

		Label weaponTitle = new Label(primaries.get(cPrimaryIndex).id(), app.assets.subTitleStyle);
		weaponTitle.setPosition((Gdx.graphics.getWidth() - weaponTitle.getPrefWidth()) / 2,
				Gdx.graphics.getHeight() * 0.67f);
		weaponTitle.setAlignment(Align.center);

		ImageButton prevButton = new ImageButton(app.assets.buttons[10][0], app.assets.buttons[10][1]);
		prevButton.addListener(new JInputListener() {
			@Override
			public void onClick() {
				cPrimaryIndex--;
				if(cPrimaryIndex < 0) cPrimaryIndex = primaries.size() - 1;
				weaponTitle.setText(primaries.get(cPrimaryIndex).id());
			}
		});
		prevButton.setPosition(Gdx.graphics.getWidth() * 0.05f, Gdx.graphics.getHeight() * 0.65f);

		ImageButton nextButton = new ImageButton(app.assets.buttons[11][0], app.assets.buttons[11][1]);
		nextButton.addListener(new JInputListener() {
			@Override
			public void onClick() {
				cPrimaryIndex++;
				if(cPrimaryIndex >= primaries.size()) cPrimaryIndex = 0;
				weaponTitle.setText(primaries.get(cPrimaryIndex).id());
			}
		});
		nextButton.setPosition((Gdx.graphics.getWidth() * 0.95f) - 180, Gdx.graphics.getHeight() * 0.65f);

		stage.addActor(primaryText);
		stage.addActor(prevButton);
		stage.addActor(nextButton);
		stage.addActor(weaponTitle);
	}

	private void initSecondarySelector() {
		Label secondaryText = new Label("Secondary", app.assets.subTitleStyle);
		secondaryText.setPosition((Gdx.graphics.getWidth() - secondaryText.getPrefWidth()) / 2,
				Gdx.graphics.getHeight() * 0.47f);
		secondaryText.setAlignment(Align.center);

		Label weaponTitle = new Label(secondaries.get(cSecondaryIndex).id(), app.assets.subTitleStyle);
		weaponTitle.setPosition((Gdx.graphics.getWidth() - weaponTitle.getPrefWidth()) / 2,
				Gdx.graphics.getHeight() * 0.37f);
		weaponTitle.setAlignment(Align.center);

		ImageButton prevButton = new ImageButton(app.assets.buttons[10][0], app.assets.buttons[10][1]);
		prevButton.addListener(new JInputListener() {
			@Override
			public void onClick() {
				cSecondaryIndex--;
				if(cSecondaryIndex < 0) cSecondaryIndex = secondaries.size() - 1;
				weaponTitle.setText(secondaries.get(cSecondaryIndex).id());
			}
		});
		prevButton.setPosition(Gdx.graphics.getWidth() * 0.05f, Gdx.graphics.getHeight() * 0.35f);

		ImageButton nextButton = new ImageButton(app.assets.buttons[11][0], app.assets.buttons[11][1]);
		nextButton.addListener(new JInputListener() {
			@Override
			public void onClick() {
				cSecondaryIndex++;
				if(cSecondaryIndex >= secondaries.size()) cSecondaryIndex = 0;
				weaponTitle.setText(secondaries.get(cSecondaryIndex).id());
			}
		});
		nextButton.setPosition((Gdx.graphics.getWidth() * 0.95f) - 180, Gdx.graphics.getHeight() * 0.35f);

		stage.addActor(secondaryText);
		stage.addActor(weaponTitle);
		stage.addActor(prevButton);
		stage.addActor(nextButton);
	}

	public Weapon getSelectedPrimary() {
		return primaries.get(cPrimaryIndex);
	}

	public Weapon getSelectedSecondary() {
		return secondaries.get(cSecondaryIndex);
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
