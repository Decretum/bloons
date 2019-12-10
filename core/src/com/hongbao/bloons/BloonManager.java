package com.hongbao.bloons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.hongbao.bloons.actors.BloonActor;
import com.hongbao.bloons.actors.BulletActor;
import com.hongbao.bloons.factories.BloonFactory;

import java.util.HashSet;
import java.util.Set;


public class BloonManager {
	
	private Stage stage;
	private long lastActionTime;
	// A dedicated collection of onstage bloons is maintained to (probably) speed up collision checking
	// especially when there are a lot of bullets on screen.
	private Set<BloonActor> onstageBloons;
	
	public BloonManager(Stage stage) {
		this.stage = stage;
		onstageBloons = new HashSet<>();
		lastActionTime = 0;
	}
	
	public void createBloon() {
		long time = System.currentTimeMillis();
		if (time > lastActionTime + 1000) {
			BloonActor actor = new BloonActor(BloonFactory.createRandomBloon(), -25, 425);
			stage.addActor(actor);
			onstageBloons.add(actor);
			lastActionTime = time;
		}
	}
	
	public void checkCollision(BulletActor bulletActor) {
		for (BloonActor bloonActor : onstageBloons) {
			float collisionDistance = bloonActor.getCollisionRadius() + bulletActor.getCollisionRadius();
			float distance = distanceBetweenActors(bulletActor, bloonActor);
			
			if (distance < collisionDistance) {
				popBloon(bloonActor, bulletActor.getBullet().getDamage());
				bulletActor.decrementPierce();
				
				if (bulletActor.getBullet().getPierce() == 0) {
					// don't bother checking collisions if the bullet is used up.
					return;
				}
			}
		}
	}
	
	public void popBloon(BloonActor bloonActor, int damage) {
		int moneyEarned = bloonActor.pop(damage);
		((BloonsTowerDefence)Gdx.app.getApplicationListener()).money += moneyEarned;
		
		if (bloonActor.getBloon().getHealth() <= 0) {
			onstageBloons.remove(bloonActor);
		}
	}
	
	private static float distanceBetweenActors(BulletActor bulletActor, BloonActor bloonActor) {
		return (float)Math.sqrt(
		 Math.pow(bulletActor.getCenterX() - bloonActor.getCenterX(), 2) + Math.pow(bulletActor.getCenterY() - bloonActor.getCenterY(), 2));
	}
	
}