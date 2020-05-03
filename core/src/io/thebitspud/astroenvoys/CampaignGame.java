package io.thebitspud.astroenvoys;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

import io.thebitspud.astroenvoys.entities.EntityID;
import io.thebitspud.astroenvoys.entities.Player;
import io.thebitspud.astroenvoys.entities.enemies.Asteroid;
import io.thebitspud.astroenvoys.entities.enemies.Enemy;
import io.thebitspud.astroenvoys.entities.enemies.Hunter;
import io.thebitspud.astroenvoys.entities.enemies.Predator;
import io.thebitspud.astroenvoys.entities.enemies.Raider;
import io.thebitspud.astroenvoys.entities.projectiles.EnergyShot;
import io.thebitspud.astroenvoys.entities.projectiles.HeavyEnergyShot;
import io.thebitspud.astroenvoys.entities.projectiles.PlasmaBolt;
import io.thebitspud.astroenvoys.entities.projectiles.Projectile;
import io.thebitspud.astroenvoys.levels.Level;

public class CampaignGame {
	private AstroEnvoys app;

	public Player player;
	public ArrayList<Enemy> enemies;
	public ArrayList<Projectile> projectiles;

	private Level level;

	public CampaignGame(AstroEnvoys app) {
		this.app = app;

		enemies = new ArrayList<>();
		projectiles = new ArrayList<>();

		player = new Player(app);
		level = new Level(this);

		init();
	}

	public void init() {
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

	public void spawnEnemy(int x, int y, float xVel, float yVel, EntityID id) {
		switch (id) {
			case AZ_RAIDER: enemies.add(new Raider(x, y, xVel, yVel, app));
				break;
			case AZ_HUNTER: enemies.add(new Hunter(x, y, xVel, yVel, app));
				break;
			case AZ_PREDATOR: enemies.add(new Predator(x, y, xVel, yVel, app));
				break;
			case ASTEROID:
				enemies.add(new Asteroid(x, y, xVel, yVel, app));
				break;
		}
	}

	public void endGame(boolean victory) {
		if(victory) app.setScreen(app.winScreen);
		else app.setScreen(app.lossScreen);
	}

	public void addProjectile(int x, int y, float xVel, float yVel, EntityID id) {
		switch (id) {
			case PLASMA_BOLT:
				projectiles.add(new PlasmaBolt(x, y, xVel, yVel, app));
				break;
			case HEAVY_ENERGY_SHOT:
				projectiles.add(new HeavyEnergyShot(x, y, xVel, yVel, app));
				break;
			case ENERGY_SHOT:
			default:
				projectiles.add(new EnergyShot(x, y, xVel, yVel, app));
				break;
		}
	}

	public void render() {
		for (Projectile p : projectiles) p.draw(app.batch);
		for (Enemy e : enemies) e.draw(app.batch);
		player.draw(app.batch);
	}
}
