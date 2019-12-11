package com.hongbao.bloons.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.hongbao.bloons.BloonsTouhouDefense;
import com.hongbao.bloons.entities.Bullet;
import com.hongbao.bloons.entities.Girl;
import com.hongbao.bloons.helpers.ZIndex;


public class GirlActor extends RenderableActor {
	
	// In hindsight, this was not a good choice of class name.
	
	private Girl girl;
	private float rotationAngle;
	
	public GirlActor(Girl girl, float x, float y) {
		this.girl = girl;
		textureRegion = new TextureRegion(new Texture(Gdx.files.internal(girl.getImageFileName())));
		rotationAngle = 0;
		
		setZIndex(ZIndex.GIRL_Z_INDEX);
		setBounds(
		 x - textureRegion.getTexture().getWidth() / 2f,
		 y - textureRegion.getTexture().getHeight() / 2f,
		 textureRegion.getTexture().getWidth(),
		 textureRegion.getTexture().getHeight()
		);
	}
	
	public Girl getGirl() {
		return girl;
	}
	
	public void setGirl(Girl girl) {
		this.girl = girl;
	}
	
	public float getRotationAngle() {
		return rotationAngle;
	}
	
	public void setRotationAngle(float rotationAngle) {
		this.rotationAngle = rotationAngle;
	}
	
	public BulletActor createBulletActor(BloonActor target) {
		float dx = target.getCenterX() - getCenterX();
		float dy = target.getCenterY() - getCenterY();
		
		// make it a unit vector
		float distance = (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
		dx /= distance;
		dy /= distance;
		
		lookAtBloon(target);
		
		Bullet bullet = girl.createBullet();
		return new BulletActor(bullet, getCenterX(), getCenterY(), dx, dy);
	}
	
	public void lookAtBloon(BloonActor target) {
		float dx = target.getCenterX() - getCenterX();
		float dy = target.getCenterY() - getCenterY();
		
		rotationAngle = (float)(Math.atan2(dx, dy) / Math.PI * 180);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(
		 textureRegion,
		 getX(),
		 getY(),
		 getCenterX() - getX(),
		 getCenterY() - getY(),
		 textureRegion.getTexture().getWidth(),
		 textureRegion.getTexture().getHeight(),
		 1f,
		 1f,
		 -rotationAngle
		);
	}
	
	@Override
	public void act(float delta) {
		if (girl.getCooldown() == 0) {
			boolean attacked = ((BloonsTouhouDefense)Gdx.app.getApplicationListener()).getMap().getBloonManager().attackBloonIfInRange(this);
			if (attacked) {
				girl.resetCooldown();
			}
		} else {
			((BloonsTouhouDefense)Gdx.app.getApplicationListener()).getMap().getBloonManager().lookAtBloon(this);
			girl.decrementCooldown();
		}
	}
	
}
