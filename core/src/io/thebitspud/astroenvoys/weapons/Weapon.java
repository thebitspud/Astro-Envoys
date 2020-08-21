package io.thebitspud.astroenvoys.weapons;

import io.thebitspud.astroenvoys.tools.JTimerUtil;

public abstract class Weapon extends JTimerUtil {
	protected Weapon(double duration) {
		super(duration, true, true);
	}
}
