package io.thebitspud.astroenvoys.weapons;

import io.thebitspud.astroenvoys.CampaignGame;
import io.thebitspud.astroenvoys.tools.JTimerUtil;

import java.util.Random;

public abstract class Weapon extends JTimerUtil {
	protected CampaignGame game;
	protected Random r;
	final double baseDuration;
	boolean secondary;
	protected Weapon(double duration, boolean secondary, CampaignGame game) {
		super(duration, true, true);

		this.game = game;
		this.secondary = secondary;

		baseDuration = duration;
		r = new Random();
	}

	public void init() {
		setTimeElapsed(0);
		setTimerDuration(baseDuration);
	}

	public abstract String id();
	public abstract String title();
	public abstract String desc();

	public boolean isSecondary() {
		return secondary;
	}
}