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
	private Random r;

	public Level(CampaignGame game) {
		this.game = game;

		r = new Random();
		timers =  new ArrayList<>();
	}

	public void init() {
		timers.clear();

		final int y = Gdx.graphics.getHeight();
		final int sWidth = Gdx.graphics.getWidth(); // screen width

		timers.add(new JTimerUtil(3.0, true, true) {
			@Override
			public void onActivation() {
				final int x = r.nextInt(sWidth - 100);
				game.spawnEnemy(x, y, EntityID.ASTEROID);
				if(getTimerDuration() > 1.0) setTimerDuration(getTimerDuration() * 0.98f);
			}
		});

		timers.add(new JTimerUtil(10.0, true, true) {
			@Override
			public void onActivation() {
				int x = r.nextInt(sWidth - 120);
				game.spawnEnemy(x, y, EntityID.AZ_RAIDER);
				if(getTimerDuration() > 2.5) setTimerDuration(getTimerDuration() * 0.95f);
			}
		});

		timers.add(new JTimerUtil(40.0, true, true) {
			@Override
			public void onActivation() {
				int x = r.nextInt(sWidth * 3/5) + sWidth / 5 - 75;
				game.spawnEnemy(x, y, EntityID.AZ_HUNTER);
				if(getTimerDuration() > 6.0) setTimerDuration(getTimerDuration() * 0.90f);
			}
		});

		timers.add(new JTimerUtil(100.0, true, true) {
			@Override
			public void onActivation() {
				int x = r.nextInt(sWidth - 150);
				game.spawnEnemy(x, y, EntityID.AZ_PREDATOR);
				if(getTimerDuration() > 10.0) setTimerDuration(getTimerDuration() * 0.90f);
			}
		});

		timers.add(new JTimerUtil(200.0, true, true) {
			@Override
			public void onActivation() {
				int x = r.nextInt(sWidth - 250);
				game.spawnEnemy(x, y, EntityID.AZ_REAPER);
				if(getTimerDuration() > 18.0) setTimerDuration(getTimerDuration() * 0.84f);
			}
		});
	}

	public void tick(float delta) {
		for(JTimerUtil timer : timers) timer.tick(delta);
	}
}
