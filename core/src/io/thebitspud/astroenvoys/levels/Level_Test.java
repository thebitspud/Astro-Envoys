package io.thebitspud.astroenvoys.levels;

import com.badlogic.gdx.Gdx;
import io.thebitspud.astroenvoys.AstroEnvoys;
import io.thebitspud.astroenvoys.entities.EntityID;

public class Level_Test extends Level {
	public Level_Test(AstroEnvoys app) {
		super(app);
	}

	@Override
	public String id() {
		return "Test";
	}

	@Override
	public String title() {
		return "Game Testing";
	}

	@Override
	public String desc() {
		return "Dev level for internal testing";
	}

	@Override
	protected void onClear() {

	}

	@Override
	protected void addEvents() {
		final int y = Gdx.graphics.getHeight();
		final int scrWidth = Gdx.graphics.getWidth(); // screen width

		game.spawnEnemy(r.nextInt(scrWidth - 100), y, EntityID.AZ_RAIDER);
		game.spawnEnemy(r.nextInt(scrWidth - 150), y, EntityID.AZ_SNIPER);
	}
}
