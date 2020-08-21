package io.thebitspud.astroenvoys.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import io.thebitspud.astroenvoys.AstroEnvoys;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.allowSoftwareMode = true;
		config.width = 600;
		config.height = 800;
		config.resizable = false;
		config.title = "Astro Envoys";

		new LwjglApplication(new AstroEnvoys(), config);
	}
}
