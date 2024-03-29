package io.thebitspud.astroenvoys.levels;

import java.util.ArrayList;
import java.util.Random;

import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.CampaignGame;
import io.thebitspud.astroenvoys.tools.JTimerUtil;

public abstract class Level {
	protected AstroEnvoys app;
	protected CampaignGame game;
	ArrayList<JTimerUtil> timers;
	JTimerUtil levelTime;
	private boolean levelCleared;
	Random r;

	Level(AstroEnvoys app) {
		this.app = app;
		game = app.gameScreen.game;

		r = new Random();
		timers =  new ArrayList<>();
		levelTime = new JTimerUtil(true) {
			@Override
			public void onActivation() {

			}
		};
	}

	public void init() {
		timers.clear();
		levelTime.setTimeElapsed(0);

		addEvents();
	}

	public void clearLevel() {
		if(levelCleared) return;

		onClear();
		levelCleared = true;
	}

	public abstract String id();
	public abstract String title();
	public abstract String desc();
	protected abstract void onClear();
	protected abstract void addEvents();

	public void tick(float delta) {
		for(JTimerUtil timer : timers) timer.tick(delta);
		levelTime.tick(delta);
	}

	public void addTimer(JTimerUtil timer) {
		timers.add(timer);
	}

	public void removeTimer(JTimerUtil timer) {
		timers.remove(timer);
	}

	public boolean isCleared() {
		return levelCleared;
	}
}