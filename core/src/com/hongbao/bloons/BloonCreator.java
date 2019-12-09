package com.hongbao.bloons;

import com.badlogic.gdx.Gdx;
import com.hongbao.bloons.factories.BloonFactory;


public class BloonCreator implements Runnable {
	
	public BloonCreator() {
	}

	public void run() {
		try {
			for (int x = 0; x < 10; x++) {
				BloonsTowerDefence app = (BloonsTowerDefence)Gdx.app.getApplicationListener();
				app.createBloon(BloonFactory.createRandomBloon());
				Thread.sleep(1000L);
			}
		} catch (InterruptedException ie) {
			throw new RuntimeException(ie);
		}
	}

}
