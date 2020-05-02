package io.thebitspud.astroenvoys.levels;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.Random;

import io.thebitspud.astroenvoys.CampaignGame;
import io.thebitspud.astroenvoys.entities.EntityID;
import io.thebitspud.astroenvoys.tools.JTimerUtil;

public class Level {
	private CampaignGame game;
	private ArrayList<JTimerUtil> timers;
	private JTimerUtil levelTime;
	private Random r;

	public Level(CampaignGame game) {
		this.game = game;

		r = new Random();
		timers =  new ArrayList<>();
		levelTime = new JTimerUtil(true) {
			@Override
			public void onActivation() {}
		};
	}

	public void init() {
		timers.clear();

		timers.add(new JTimerUtil(3.0, true, true) {
			@Override
			public void onActivation() {
				final int x = r.nextInt(Gdx.graphics.getWidth() - 100),
				y = Gdx.graphics.getHeight() + 100;
				game.spawnEnemy(x, y,0, -100, EntityID.ASTEROID);
				if(getTimerDuration() > 1.0) setTimerDuration(getTimerDuration() * 0.98f);
			}
		});

		timers.add(new JTimerUtil(10.0, true, true) {
			@Override
			public void onActivation() {
				final int x = r.nextInt(Gdx.graphics.getWidth() - 120),
				y = Gdx.graphics.getHeight() + 200;
				game.spawnEnemy(x, y, 0, -100, EntityID.AZ_RAIDER);
				if(getTimerDuration() > 2.5) setTimerDuration(getTimerDuration() * 0.96f);
			}
		});
	}

	public void tick(float delta) {
		for(JTimerUtil timer : timers) timer.tick(delta);
	}
}
