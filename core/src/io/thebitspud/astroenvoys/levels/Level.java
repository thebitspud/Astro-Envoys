package io.thebitspud.astroenvoys.levels;

import java.util.ArrayList;
import java.util.Random;

import io.thebitspud.astroenvoys.CampaignGame;
import io.thebitspud.astroenvoys.tools.JTimerUtil;

public abstract class Level {
	protected CampaignGame game;
	protected ArrayList<JTimerUtil> timers;
	protected Random r;

	public Level(CampaignGame game) {
		this.game = game;

		r = new Random();
		timers =  new ArrayList<>();
	}

	public void init() {
		timers.clear();

		addEvents();
	}

	public abstract String id();
	public abstract String title();
	public abstract String desc();
	protected abstract void addEvents();

	public void tick(float delta) {
		for(JTimerUtil timer : timers) timer.tick(delta);
	}

	public void addTimer(JTimerUtil timer) {
		timers.add(timer);
	}

	public void removeTimer(JTimerUtil timer) {
		timers.remove(timer);
	}
}
