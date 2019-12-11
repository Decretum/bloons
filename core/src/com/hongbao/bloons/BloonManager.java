package com.hongbao.bloons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.hongbao.bloons.actors.BloonActor;
import com.hongbao.bloons.actors.BulletActor;
import com.hongbao.bloons.actors.GirlActor;
import com.hongbao.bloons.actors.RenderableActor;
import com.hongbao.bloons.entities.Bloon;
import com.hongbao.bloons.factories.BloonFactory;

import java.util.HashSet;
import java.util.Set;


public class BloonManager {
	
	private Stage stage;
	private long gameStartTime;
	// A dedicated collection of onstage bloons is maintained to (probably) speed up collision checking
	// especially when there are a lot of bullets on screen.
	private Set<BloonActor> onstageBloons;
	private Sound popSound;
	private BloonQueue bloonQueue;
	
	public BloonManager(Stage stage) {
		this.stage = stage;
		onstageBloons = new HashSet<>();
		gameStartTime = System.currentTimeMillis();
		popSound = Gdx.audio.newSound(Gdx.files.internal("music/pop.mp3"));
		bloonQueue = BloonFactory.createBloonQueue();
	}
	
	public void createBloons() {
		long time = System.currentTimeMillis();
		Set<Bloon> bloonsToBeCreated = bloonQueue.getBloons(time - gameStartTime);
		
		for (Bloon bloon : bloonsToBeCreated) {
			BloonActor actor = new BloonActor(bloon, -25, 425); // todo make these numbers an attribute in map or something
			stage.addActor(actor);
			onstageBloons.add(actor);
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
						break;
					}
				}
			}
		}
		
		bloonsToBePopped.forEach((bloonActor) -> popBloon(bloonActor, bulletActor.getBullet().getDamage()));
	}
	
	public void popBloon(BloonActor bloonActor, int damage) {
		int moneyEarned = bloonActor.pop(damage);
		((BloonsTouhouDefense)Gdx.app.getApplicationListener()).money += moneyEarned;
		
		if (bloonActor.getBloon().getHealth() <= 0) {
			onstageBloons.remove(bloonActor);
		}
		
		popSound.play(0.5f);
	}
	
	public boolean attackBloonIfInRange(GirlActor girlActor) {
		Set<BloonActor> bloonsInRange = new HashSet<>();
		
		for (BloonActor bloonActor : onstageBloons) {
			float distance = distanceBetweenActors(girlActor, bloonActor);
			
			if (distance < girlActor.getGirl().getVisualRange()) {
				bloonsInRange.add(bloonActor);
			}
		}
		
		if (bloonsInRange.isEmpty()) {
			return false;
		} else {
			BloonActor bloonActor = bloonsInRange.iterator().next();
			
			for (BloonActor actor : bloonsInRange) {
				if (actor.getBloon().getDistanceTravelled() > bloonActor.getBloon().getDistanceTravelled()) {
					bloonActor = actor;
				}
			}
			
			BulletActor bulletActor = girlActor.createBulletActor(bloonActor);
			stage.addActor(bulletActor);
			return true;
		}
	}
	
	public void lookAtBloon(GirlActor girlActor) {
		Set<BloonActor> bloonsInRange = new HashSet<>();
		
		for (BloonActor bloonActor : onstageBloons) {
			float distance = distanceBetweenActors(girlActor, bloonActor);
			
			if (distance < girlActor.getGirl().getVisualRange()) {
				bloonsInRange.add(bloonActor);
			}
		}
		
		if (!bloonsInRange.isEmpty()) {
			BloonActor bloonActor = bloonsInRange.iterator().next();
			
			for (BloonActor actor : bloonsInRange) {
				if (actor.getBloon().getDistanceTravelled() > bloonActor.getBloon().getDistanceTravelled()) {
					bloonActor = actor;
				}
			}
			
			girlActor.lookAtBloon(bloonActor);
		}
	}
	
	private static float distanceBetweenActors(RenderableActor actor1, RenderableActor actor2) {
		return (float)Math.sqrt(
		 Math.pow(actor1.getCenterX() - actor2.getCenterX(), 2) + Math.pow(actor1.getCenterY() - actor2.getCenterY(), 2));
	}
	
}
