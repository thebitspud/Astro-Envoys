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
	public Label.LabelStyle titleStyle, smallTitleStyle;

	public AssetLibrary() {
		buttons = new TextureRegionDrawable[5][2];
	}

	public void loadAll() {
		loadFonts();

		this.load("buttons.png", Texture.class);
		this.load("missing.png", Texture.class);
		this.load("shuttle.png", Texture.class);

		finishLoading();
		assign();
	}

	public void assign() {
		missing = new TextureRegion(this.get("missing.png", Texture.class));
		shuttle = new TextureRegion(this.get("shuttle.png", Texture.class));

		buttons[0][0] = getButton(0,0,800);
		buttons[0][1] = getButton(0,180,800);
		buttons[1][0] = getButton(0,360,180);
		buttons[1][1] = getButton(0,540,180);
		buttons[2][0] = getButton(180,360,180);
		buttons[2][1] = getButton(180,540,180);
		buttons[3][0] = getButton(360,360,180);
		buttons[3][1] = getButton(360,540,180);
		buttons[4][0] = getButton(540,360,180);
		buttons[4][1] = getButton(540,540,180);
	}

	private TextureRegionDrawable getButton(int x, int y, int width) {
		TextureRegion icon = new TextureRegion(this.get("buttons.png", Texture.class),x,y,width, 180);
		return new TextureRegionDrawable(icon);
	}

	private void loadFonts() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("good_times.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.incremental = true;
		parameter.size = Gdx.graphics.getWidth() / 6;
		titleStyle = new Label.LabelStyle(generator.generateFont(parameter), new Color(1,0.2f,0.2f,1));
		parameter.size = Gdx.graphics.getWidth() / 7;
		smallTitleStyle = new Label.LabelStyle(generator.generateFont(parameter), new Color(1,0.2f,0.2f,1));
		generator.dispose();
	}
}