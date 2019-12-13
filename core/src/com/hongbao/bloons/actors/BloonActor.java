package com.hongbao.bloons.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.hongbao.bloons.BloonsTouhouDefense;
import com.hongbao.bloons.entities.Bloon;
import com.hongbao.bloons.helpers.BloonPoppedResult;
import com.hongbao.bloons.helpers.ZIndex;
import javafx.util.Pair;


public class BloonActor extends RenderableActor {

	public static final float SCALE = 0.5f;

	private Bloon bloon;
	private Texture texture;
	private float collisionRadius;

	public BloonActor(Bloon bloon, float x, float y) {
		this.bloon = bloon;
		texture = new Texture(Gdx.files.internal(bloon.getImageFileName()));

		collisionRadius = texture.getWidth() * SCALE / 2f;
		
		setZIndex(ZIndex.BLOON_Z_INDEX);
		setBounds(x - texture.getWidth() * SCALE / 2f, y - texture.getHeight() * SCALE / 2f, texture.getWidth() * SCALE, texture.getHeight() * SCALE);
	}

	public Bloon getBloon() {
		return bloon;
	}

	public void setBloon(Bloon bloon) {
		this.bloon = bloon;
	}
	
	@Override
	public float getCenterX() {
		return getX() + texture.getWidth() * SCALE / 2f;
	}
	
	@Override
	public float getCenterY() {
		return getY() + texture.getHeight() * SCALE / 2f;
	}
	
	public float getCollisionRadius() {
		return collisionRadius;
	}
	
	public void setCollisionRadius(float collisionRadius) {
		this.collisionRadius = collisionRadius;
	}
	
	// Please avoid calling this method directly, instead use the BloonManager damage()
	public void damage(int damage) {
		bloon.damage(damage);
	}
	
	// Please avoid calling this method directly, instead use the BloonManager pop()
	public BloonPoppedResult pop(int damage) {
		BloonPoppedResult bloonPoppedResult = bloon.pop(damage);
		texture.dispose();
		remove();
		return bloonPoppedResult;
	}
	
	public void release() {
		((BloonsTouhouDefense)Gdx.app.getApplicationListener()).getPlayer().decreaseHealth(bloon.getHealth());
		texture.dispose();
		remove();
	}
	
	public void move(Pair<Float, Float> direction) {
		setX(getX() + direction.getKey() * bloon.getSpeed() / 5);
		setY(getY() + direction.getValue() * bloon.getSpeed() / 5);
		
		bloon.incrementDistanceTravelled();
		
		if (getX() > 1500) {
			release();
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(texture, getX(), getY(), texture.getWidth() * SCALE, texture.getHeight() * SCALE);
	}
	
	@Override
	public void act(float delta) {
		BloonsTouhouDefense app = (BloonsTouhouDefense)Gdx.app.getApplicationListener();
		Pair<Float, Float> direction = app.getMap().getDirection(getCenterX(), getCenterY());
		if (direction.getKey() < 0) {
			System.out.println(direction.getKey());
		}
		move(direction);
	}
}
