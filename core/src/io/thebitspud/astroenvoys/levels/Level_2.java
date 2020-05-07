package io.thebitspud.astroenvoys.levels;

import io.thebitspud.astroenvoys.CampaignGame;

public class Level_2 extends Level {
	public Level_2(CampaignGame game) {
		super(game);
	}

	@Override
	public String id() {
		return "Level 02";
	}

	@Override
	public String title() {
		return "02: Investigation";
	}

	@Override
	public String desc() {
		return "Our battle confirmed rumours that the Azikan Swarm has established " +
			"a presence in the outskirts of the system. Pilot, we need you to " +
			"pinpoint where the bugs are coming from.";
	}

	@Override
	protected void addEvents() {

	}
}
