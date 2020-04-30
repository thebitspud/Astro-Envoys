package io.thebitspud.astroenvoys.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class AssetLibrary extends AssetManager {
	public TextureRegion missing, shuttle;
	public TextureRegionDrawable[][] buttons;
	public Label.LabelStyle titleStyle, smallTitleStyle, subTitleStyle;

	public AssetLibrary() {
		buttons = new TextureRegionDrawable[12][2];
	}

	public void loadAll() {
		loadFonts();

		this.load("buttons.png", Texture.class);
		this.load("missing.png", Texture.class);
		this.load("shuttle.png", Texture.class);

		finishLoading();
		assign();
	}

	private void assign() {
		missing = new TextureRegion(this.get("missing.png", Texture.class));
		shuttle = new TextureRegion(this.get("shuttle.png", Texture.class));

		for(int i = 0; i < 6; i++) {
			buttons[i] = getButton(0,i * 180,800);
			buttons[6 + i] = getButton(1600, i * 180, 180);
		}
	}

	private TextureRegionDrawable[] getButton(int x, int y, int width) {
		TextureRegion iconUp = new TextureRegion(this.get("buttons.png", Texture.class), x, y, width, 180);
		TextureRegion iconDown = new TextureRegion(this.get("buttons.png", Texture.class),x + width, y, width, 180);

		TextureRegionDrawable[] button = new TextureRegionDrawable[2];
		button[0] = new TextureRegionDrawable(iconUp);
		button[1] = new TextureRegionDrawable(iconDown);

		return button;
	}

	private void loadFonts() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("good_times.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.incremental = true;

		parameter.size = Gdx.graphics.getWidth() / 6;
		titleStyle = new Label.LabelStyle(generator.generateFont(parameter), new Color(1,0.2f,0.2f,1));

		parameter.size = Gdx.graphics.getWidth() / 7;
		smallTitleStyle = new Label.LabelStyle(generator.generateFont(parameter), new Color(1,0.2f,0.2f,1));

		parameter.size = Gdx.graphics.getWidth() / 12;
		subTitleStyle = new Label.LabelStyle(generator.generateFont(parameter), Color.WHITE);
		generator.dispose();
	}
}