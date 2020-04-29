package io.thebitspud.astroenvoys.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetLibrary extends AssetManager {
	public TextureRegion missing;

	public AssetLibrary() {

	}

	public void loadAll() {
		this.load("missing.png", Texture.class);
		this.load("skin/quantum-horizon-ui.json", Skin.class);

		loadFonts();
	}

	public void assignTextures() {
		missing = new TextureRegion(this.get("missing.png", Texture.class));
	}

	public Skin qHorizon;

	public void assignSkins() {
		qHorizon = this.get("skin/quantum-horizon-ui.json", Skin.class);
	}

	public Label.LabelStyle titleFont;

	private void loadFonts() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("good_times.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 64;
		parameter.shadowColor = Color.GRAY;
		parameter.shadowOffsetY = 4;
		titleFont = new Label.LabelStyle(generator.generateFont(parameter), new Color(0.7f, 0, 0, 1));
		generator.dispose();
	}
}