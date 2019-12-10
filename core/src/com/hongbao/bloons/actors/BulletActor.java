package com.hongbao.bloons.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.hongbao.bloons.entities.Bullet;
import com.hongbao.bloons.helpers.ZIndex;


public class BulletActor extends RenderableActor {
	
	private Bullet bullet;
	private TextureRegion texture;
	private float dx; // This should be a unit vector
	private float dy;
	private float rotationAngle;

	public BulletActor(Bullet bullet, float x, float y, float dx, float dy) {
		this.bullet = bullet;
		texture = new TextureRegion(new Texture(Gdx.files.internal(bullet.getImageFileName())));
		this.dx = dx;
		this.dy = dy;
		calculateRotationAngle();
		
		setZIndex(ZIndex.BULLET_Z_INDEX);
		setBounds(x - texture.getTexture().getWidth() / 2f, y - texture.getTexture().getHeight() / 2f, texture.getTexture().getWidth(), texture.getTexture().getHeight());
	}

	public Bullet getBullet() {
		return bullet;
	}

	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}

	public float getCenterX() {
		return getX() + texture.getTexture().getWidth() / 2f;
	}

	public float getCenterY() {
		return getY() + texture.getTexture().getHeight() / 2f;
	}
	
	private void calculateRotationAngle() {
		rotationAngle = (float)(Math.atan2(dx, dy) / Math.PI * 180);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(texture, getX(), getY(), getCenterX() - getX(), getCenterY() - getY(), texture.getTexture().getWidth(), texture.getTexture().getHeight(), 1f, 1f, -rotationAngle);
	}
	
	@Override
	public void act(float delta) {
		setX(getX() + dx * bullet.getSpeed() / 5);
		setY(getY() + dy * bullet.getSpeed() / 5);
		
		if (getY() < 0 || getY() > 900 || getX() < 0 || getX() > 1500) {
			remove();
		}
		
		// check collisions
	}
}
