package com.hongbao.bloons.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.hongbao.bloons.BloonsTowerDefence;
import com.hongbao.bloons.entities.Bloon;
import com.hongbao.bloons.entities.Bullet;
import com.hongbao.bloons.helpers.ZIndex;
import javafx.util.Pair;


public class BulletActor extends RenderableActor {
	
	Bullet bullet;
	Texture texture;

	public BulletActor(Bullet bullet, float x, float y) {
		this.bullet = bullet;
		texture = new Texture(Gdx.files.internal(bullet.getImageFileName()));

		setZIndex(ZIndex.BULLET_Z_INDEX);
		setBounds(x - texture.getWidth() / 2f, y - texture.getHeight() / 2f, texture.getWidth(), texture.getHeight());
	}

	public Bullet getBullet() {
		return bullet;
	}

	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}

	public float getCenterX() {
		return getX() + texture.getWidth() / 2f;
	}

	public float getCenterY() {
		return getY() + texture.getHeight() / 2f;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(texture, getX(), getY(), texture.getWidth(), texture.getHeight());
	}
	
	@Override
	public void act(float delta) {
		setY(getY() + -1 * bullet.getSpeed() / 5);
		
		if (getY() < 0 || getY() > 900 || getX() < 0 || getX() > 1500) {
			remove();
		}
		
		// check collisions
	}
}
