package io.thebitspud.astroenvoys.tools;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class JTextButton extends TextButton {
	public enum Size {
		SMALL,
		MEDIUM,
		LARGE
	}

	public JTextButton(int x, int y, Size s, String text, Skin skin) {
		super(text, skin);

		setPosition(x, y);
		setDimensions(s);
	}

	public void setDimensions(Size s) {
		switch (s) {
			case SMALL:
				setSize(150, 40);
				break;
			case MEDIUM:
				setSize(200, 60);
				break;
			case LARGE:
				setSize(250, 80);
				break;
			default:
				setDimensions(Size.MEDIUM);
		}
	}
}
