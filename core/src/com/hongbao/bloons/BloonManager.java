package com.hongbao.bloons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.hongbao.bloons.actors.BloonActor;
import com.hongbao.bloons.actors.BulletActor;
import com.hongbao.bloons.actors.GirlActor;
import com.hongbao.bloons.actors.RenderableActor;
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
		if (time > lastActionTime + 200) {
			BloonActor actor = new BloonActor(BloonFactory.createRandomBloon(), -25, 425);
			stage.addActor(actor);
			onstageBloons.add(actor);
			lastActionTime = time;
		}
	}
	
	public void checkCollision(final BulletActor bulletActor) {
		Set<BloonActor> bloonsToBePopped = new HashSet<>(); // to avoid ConcurrentModificationException
		
		for (BloonActor bloonActor : onstageBloons) {
			float collisionDistance = bloonActor.getCollisionRadius() + bulletActor.getCollisionRadius();
			float distance = distanceBetweenActors(bulletActor, bloonActor);
			
			if (distance < collisionDistance) {
				if (!bulletActor.getBullet().hasDamagedBloon(bloonActor.getBloon())) {
					bulletActor.getBullet().damageBloon(bloonActor.getBloon());
					bloonsToBePopped.add(bloonActor);
					bulletActor.decrementPierce();
					
					if (bulletActor.getBullet().getPierce() == 0) {
						// don't bother checking collisions if the bullet is used up.
						return;
					}
				}
			}
		}
		
		bloonsToBePopped.forEach((bloonActor) -> popBloon(bloonActor, bulletActor.getBullet().getDamage()));
	}
	
	public void popBloon(BloonActor bloonActor, int damage) {
		int moneyEarned = bloonActor.pop(damage);
		((BloonsTowerDefence)Gdx.app.getApplicationListener()).money += moneyEarned;
		
		if (bloonActor.getBloon().getHealth() <= 0) {
			onstageBloons.remove(bloonActor);
		}
	}
	
	public boolean attackBloonIfInRange(GirlActor girlActor) {
		for (BloonActor bloonActor : onstageBloons) {
			float distance = distanceBetweenActors(girlActor, bloonActor);
			
			if (distance < girlActor.getGirl().getVisualRange()) {
				
				BulletActor bulletActor = girlActor.createBulletActor(bloonActor);
				stage.addActor(bulletActor);
				return true;
			}
		}
		return false;
	}
	
	public void lookAtBloon(GirlActor girlActor) {
		for (BloonActor bloonActor : onstageBloons) {
			float distance = distanceBetweenActors(girlActor, bloonActor);
			
			if (distance < girlActor.getGirl().getVisualRange()) {
				girlActor.lookAtBloon(bloonActor);
				return;
			}
		}
	}
	
	private static float distanceBetweenActors(RenderableActor actor1, RenderableActor actor2) {
		return (float)Math.sqrt(
		 Math.pow(actor1.getCenterX() - actor2.getCenterX(), 2) + Math.pow(actor1.getCenterY() - actor2.getCenterY(), 2));
	}
	
}
