package io.thebitspud.astroenvoys;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

import io.thebitspud.astroenvoys.entities.Player;
import io.thebitspud.astroenvoys.entities.enemies.*;
import io.thebitspud.astroenvoys.entities.projectiles.*;
import io.thebitspud.astroenvoys.levels.Level;

public class CampaignGame {
	private AstroEnvoys app;

	private Player player;
	private ArrayList<Enemy> enemies;
	private ArrayList<Projectile> projectiles;

	private Level level;

	public CampaignGame(AstroEnvoys app) {
		this.app = app;

		player = new Player(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 5, app);
		enemies = new ArrayList<>();
		projectiles = new ArrayList<>();

		level = new Level();

		init();
	}

	public void init() {
		projectiles.clear();
		enemies.clear();
		level.reset();

		for (int i = 0; i < 9; i++) enemies.add(new Asteroid(i * 120, Gdx.graphics.getHeight() + 100, 0, -100, app));
		for(int i = 0; i < 60; i++) projectiles.add(new EnergyShot(i * 18, Gdx.graphics.getHeight() + 100, 0, -200, app));
	}

	public void tick(float delta) {
		for (int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			if(!e.isActive()) enemies.remove(e);
		}

		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = projectiles.get(i);
			if(!p.isActive()) projectiles.remove(p);
		}

		player.tick(delta);
		for (Enemy e : enemies) e.tick(delta);

		for (Projectile p : projectiles) {
			p.tick(delta);
			p.checkForCollision(player);
			for (Enemy e : enemies) p.checkForCollision(e);
		}
	}

	public void render() {
		for (Enemy e : enemies) e.render();
		for (Projectile p : projectiles) p.render();
		player.render();
	}
}
