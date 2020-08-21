package io.thebitspud.astroenvoys.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import io.thebitspud.astroenvoys.entities.EntityID;

public class AssetLibrary extends AssetManager {
	public TextureRegionDrawable[][] buttons;
	public Label.LabelStyle titleStyle, smallTitleStyle, subTitleStyle, textStyle;
	private TextureRegion missing;
	private TextureRegion[] playerShips, enemyShips, projectiles;

	public AssetLibrary() {
		buttons = new TextureRegionDrawable[13][2];
		playerShips = new TextureRegion[4];
		enemyShips = new TextureRegion[16];
		projectiles = new TextureRegion[16];
	}

	public void loadAll() {
		loadFonts();

		this.load("buttons.png", Texture.class);
		this.load("asteroid.png", Texture.class);
		this.load("missing.png", Texture.class);
		this.load("player.png", Texture.class);
		this.load("az_raider.png", Texture.class);
		this.load("az_hunter.png", Texture.class);
		this.load("az_predator.png", Texture.class);
		this.load("az_reaper.png", Texture.class);
		this.load("projectiles.png", Texture.class);

		finishLoading();
		assign();
	}

	private void assign() {
		missing = new TextureRegion(this.get("missing.png", Texture.class));

		for (int i = 0; i < 4; i++)
			playerShips[i] = new TextureRegion(this.get("player.png", Texture.class), i * 180, 0, 180, 180);

		enemyShips[0] = new TextureRegion(this.get("asteroid.png", Texture.class));
		enemyShips[1] = new TextureRegion(this.get("az_raider.png", Texture.class));
		enemyShips[2] = new TextureRegion(this.get("az_hunter.png", Texture.class));
		enemyShips[3] = new TextureRegion(this.get("az_predator.png", Texture.class));
		enemyShips[4] = new TextureRegion(this.get("az_reaper.png", Texture.class));

		projectiles[0] = new TextureRegion(this.get("projectiles.png", Texture.class), 0, 0, 25, 50);
		projectiles[1] = new TextureRegion(this.get("projectiles.png", Texture.class), 25, 0, 25, 50);
		projectiles[2] = new TextureRegion(this.get("projectiles.png", Texture.class), 50, 0, 40, 60);
		projectiles[3] = new TextureRegion(this.get("projectiles.png", Texture.class), 90, 0, 40, 60);

		for (int i = 0; i < 6; i++) {
			buttons[i] = getButton(0, i * 180, 800);
			buttons[6 + i] = getButton(1600, i * 180, 180);
		}

		buttons[12] = getButton(1600, 1080, 180);
	}

	private TextureRegionDrawable[] getButton(int x, int y, int width) {
		TextureRegion iconUp = new TextureRegion(this.get("buttons.png", Texture.class), x, y, width, 180);
		TextureRegion iconDown = new TextureRegion(this.get("buttons.png", Texture.class), x + width, y, width, 180);

		TextureRegionDrawable[] button = new TextureRegionDrawable[2];
		button[0] = new TextureRegionDrawable(iconUp);
		button[1] = new TextureRegionDrawable(iconDown);

		return button;
	}

	public TextureRegion getTexture(EntityID id) {
		switch (id.type()) {
			case ENEMY:
				return enemyShips[id.numID()];
			case PROJECTILE:
				return projectiles[id.numID()];
			case PLAYER:
				return playerShips[id.numID()];
			default:
				return missing;
		}
	}

	private void loadFonts() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("good_times.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.incremental = true;

		parameter.size = Gdx.graphics.getWidth() / 6;
		parameter.spaceX = 3;
		titleStyle = new Label.LabelStyle(generator.generateFont(parameter), new Color(1, 0.2f, 0.2f, 1));

		parameter.size = Gdx.graphics.getWidth() / 7;
		smallTitleStyle = new Label.LabelStyle(generator.generateFont(parameter), new Color(1, 0.2f, 0.2f, 1));

		parameter.size = Gdx.graphics.getWidth() / 15;
		subTitleStyle = new Label.LabelStyle(generator.generateFont(parameter), Color.WHITE);

		parameter.size = Gdx.graphics.getWidth() / 25;
		parameter.spaceY = parameter.size / 3;
		textStyle = new Label.LabelStyle(generator.generateFont(parameter), Color.WHITE);

		generator.dispose();
	}
}