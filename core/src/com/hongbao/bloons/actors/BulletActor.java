package com.hongbao.bloons.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.hongbao.bloons.BloonsTowerDefence;
import com.hongbao.bloons.entities.Bullet;
import com.hongbao.bloons.helpers.ZIndex;


public class BulletActor extends RenderableActor {
	
	private Bullet bullet;
	private float dx; // This should be a unit vector
	private float dy;
	private float rotationAngle;
	private float collisionRadius;
	
	public BulletActor(Bullet bullet, float x, float y, float dx, float dy) {
		this.bullet = bullet;
		textureRegion = new TextureRegion(new Texture(Gdx.files.internal(bullet.getImageFileName())));
		this.dx = dx;
		this.dy = dy;
		calculateRotationAngle();
		collisionRadius = textureRegion.getTexture().getWidth() / 2f;
		
		setZIndex(ZIndex.BULLET_Z_INDEX);
		setBounds(
		 x - textureRegion.getTexture().getWidth() / 2f,
		 y - textureRegion.getTexture().getHeight() / 2f,
		 textureRegion.getTexture().getWidth(),
		 textureRegion.getTexture().getHeight()
		);
	}
	
	public Bullet getBullet() {
		return bullet;
	}
	
	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}
	
	private void calculateRotationAngle() {
		rotationAngle = (float)(Math.atan2(dx, dy) / Math.PI * 180);
	}
	
	public float getCollisionRadius() {
		return collisionRadius;
	}
	
	public void setCollisionRadius(float collisionRadius) {
		this.collisionRadius = collisionRadius;
	}
	
	public void decrementPierce() {
		bullet.decrementPierce();
		if (bullet.getPierce() == 0) {
			remove();
		}
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
		setX(getX() + dx * bullet.getSpeed() / 5);
		setY(getY() + dy * bullet.getSpeed() / 5);
		
		if (getY() < 0 || getY() > 900 || getX() < 0 || getX() > 1500) {
			remove();
		}
		
		bullet.incrementDistanceTraveled();
		if (bullet.getDistanceTraveled() >= bullet.getMaxRange()) {
			remove();
		}
		
		((BloonsTowerDefence)Gdx.app.getApplicationListener()).getMap().getBloonManager().checkCollision(this);
	}
}
