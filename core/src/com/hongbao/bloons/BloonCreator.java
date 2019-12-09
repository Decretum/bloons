package com.hongbao.bloons;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.hongbao.bloons.actors.BloonActor;
import com.hongbao.bloons.factories.BloonFactory;


public class BloonCreator {
	
	private Stage stage;
	private long lastActionTime;
	
	public BloonCreator(Stage stage) {
		this.stage = stage;
		lastActionTime = 0;
	}
	
	public void createBloon() {
		long time = System.currentTimeMillis();
		if (time > lastActionTime + 1000) {
			stage.addActor(new BloonActor(BloonFactory.createRandomBloon(), -25, 425));
			lastActionTime = time;
		}
	}
	
}
