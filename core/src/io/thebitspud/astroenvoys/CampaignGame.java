package io.thebitspud.astroenvoys;

import java.util.ArrayList;

import io.thebitspud.astroenvoys.entities.EntityID;
import io.thebitspud.astroenvoys.entities.Player;
import io.thebitspud.astroenvoys.entities.enemies.Asteroid;
import io.thebitspud.astroenvoys.entities.enemies.Enemy;
import io.thebitspud.astroenvoys.entities.enemies.Hunter;
import io.thebitspud.astroenvoys.entities.enemies.Predator;
import io.thebitspud.astroenvoys.entities.enemies.Raider;
import io.thebitspud.astroenvoys.entities.enemies.Reaper;
import io.thebitspud.astroenvoys.entities.projectiles.Projectile;
import io.thebitspud.astroenvoys.levels.Level;

public class CampaignGame {
	private AstroEnvoys app;

	public Player player;
	public ArrayList<Enemy> enemies;
	private ArrayList<Projectile> projectiles;

	public Level level;

	public CampaignGame(AstroEnvoys app) {
		this.app = app;

		enemies = new ArrayList<>();
		projectiles = new ArrayList<>();

		player = new Player(app);
	}

	public void init() {
		level = app.levelSelectScreen.getSelectedLevel();

		player.init();
		projectiles.clear();
		enemies.clear();
		level.init();
	}

	public void tick(float delta) {
		level.tick(delta);

		for (int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			if (e.isDead()) enemies.remove(e);
		}

		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = projectiles.get(i);
			if (p.isDead()) projectiles.remove(p);
		}

		player.tick(delta);
		for (Enemy e : enemies) e.tick(delta);
		for (Projectile p : projectiles) {
			p.tick(delta);
			p.checkForCollision(player);
			for (Enemy e : enemies) p.checkForCollision(e);
		}
	}

	public void spawnEnemy(int x, int y, EntityID id) {
		switch (id) {
			case AZ_RAIDER: enemies.add(new Raider(x, y, app));
				break;
			case AZ_HUNTER: enemies.add(new Hunter(x, y, app));
				break;
			case AZ_PREDATOR: enemies.add(new Predator(x, y, app));
				break;
			case AZ_REAPER: enemies.add(new Reaper(x, y, app));
				break;
			case ASTEROID: enemies.add(new Asteroid(x, y, app));
				break;
		}
	}

	// No enemies on screen (excludes asteroids)

	public boolean allEnemiesCleared() {
		for(Enemy e : enemies) {
			if (e.getID() != EntityID.ASTEROID) return false;
		}

		return true;
	}

	public void endGame(boolean victory) {
		if(victory) app.setScreen(app.winScreen);
		else app.setScreen(app.lossScreen);
	}

	public void addProjectile(int x, int y, float xVel, float yVel, EntityID id) {
		switch (id) {
			case ENERGY_SHOT:
				projectiles.add(new Projectile(x, y, xVel, yVel, 7, true, id, app));
				break;
			case PLASMA_SHOT:
				projectiles.add(new Projectile(x, y, xVel, yVel, 5, false, id, app));
				break;
			case HEAVY_ENERGY_SHOT:
				projectiles.add(new Projectile(x, y, xVel, yVel, 15, true, id, app));
				break;
			case HEAVY_PLASMA_SHOT:
				projectiles.add(new Projectile(x, y, xVel, yVel, 12, false, id, app));
				break;
		}
	}

	public void render() {
		for (Projectile p : projectiles) p.draw(app.batch);
		for (Enemy e : enemies) e.draw(app.batch);
		player.draw(app.batch);
	}
}
