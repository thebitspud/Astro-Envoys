package io.thebitspud.astroenvoys;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

import io.thebitspud.astroenvoys.entities.EntityID;
import io.thebitspud.astroenvoys.entities.Player;
import io.thebitspud.astroenvoys.entities.enemies.Asteroid;
import io.thebitspud.astroenvoys.entities.enemies.Enemy;
import io.thebitspud.astroenvoys.entities.projectiles.EnergyShot;
import io.thebitspud.astroenvoys.entities.projectiles.PlasmaBolt;
import io.thebitspud.astroenvoys.entities.projectiles.Projectile;
import io.thebitspud.astroenvoys.levels.Level;

public class CampaignGame {
	private AstroEnvoys app;

	private Player player;
	private ArrayList<Enemy> enemies;
	private ArrayList<Projectile> projectiles;

	private Level level;

	public CampaignGame(AstroEnvoys app) {
		this.app = app;

		player = new Player(Gdx.graphics.getWidth() / 2 - 90, Gdx.graphics.getHeight() / 5, app);
		enemies = new ArrayList<>();
		projectiles = new ArrayList<>();

		level = new Level();

		init();
	}

	public void init() {
		projectiles.clear();
		enemies.clear();
		level.reset();

		for (int i = 0; i < 9; i++)
			enemies.add(new Asteroid(i * 120, Gdx.graphics.getHeight() + 100, 0, -100, app));
		for (int i = 0; i < 12; i++)
			addProjectile(i * 100, Gdx.graphics.getHeight() + 100, 0, -500, EntityID.ENERGY_SHOT);
	}

	public void tick(float delta) {
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

	public void addProjectile(int x, int y, float xVel, float yVel, EntityID id) {
		switch (id) {
			case PLASMA_BOLT:
				projectiles.add(new PlasmaBolt(x, y, xVel, yVel, app));
			case ENERGY_SHOT:
			default:
				projectiles.add(new EnergyShot(x, y, xVel, yVel, app));
				break;
		}
	}

	public void render() {
		for (Enemy e : enemies) e.draw(app.batch);
		for (Projectile p : projectiles) p.draw(app.batch);
		player.draw(app.batch);
	}
}
