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
import javafx.util.Pair;


public class BloonActor extends RenderableActor {

	public static final float SCALE = 0.5f;
	public static final int BLOON_Z_INDEX = 0;

	Bloon bloon;
	Texture texture;

	public BloonActor(Bloon bloon, float x, float y) {
		this.bloon = bloon;
		texture = new Texture(Gdx.files.internal(bloon.getImageFileName()));

		setZIndex(BLOON_Z_INDEX);
		setTouchable(Touchable.enabled);
		setBounds(x - texture.getWidth() * SCALE / 2f, y - texture.getHeight() * SCALE / 2f, texture.getWidth() * SCALE, texture.getHeight() * SCALE);
		addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if (button == Input.Buttons.LEFT) {
					BloonActor target = (BloonActor) event.getTarget();
					int moneyEarned = target.pop();
					((BloonsTowerDefence)Gdx.app.getApplicationListener()).money += moneyEarned;
					return true;
				}
				return false;
			}
		});
	}

	public Bloon getBloon() {
		return bloon;
	}

	public void setBloon(Bloon bloon) {
		this.bloon = bloon;
	}

	public float getCenterX() {
		return getX() + texture.getWidth() * SCALE / 2f;
	}

	public float getCenterY() {
		return getY() + texture.getHeight() * SCALE / 2f;
	}

	public int pop() {
		int popCount = bloon.pop();
		if (bloon.getHealth() > 0) {
			float centerX = getCenterX();
			float centerY = getCenterY();

			texture = new Texture(Gdx.files.internal(bloon.getImageFileName()));
			setX(centerX - texture.getWidth() * SCALE / 2f);
			setY(centerY - texture.getHeight() * SCALE / 2f);
			setBounds(getX(), getY(), texture.getWidth() * SCALE, texture.getHeight() * SCALE);
		} else {
			remove();
		}
		return popCount;
	}
	
	public void release() {
		((BloonsTowerDefence)Gdx.app.getApplicationListener()).health -= bloon.getHealth();
		remove();
	}
	
	public void move(Pair<Float, Float> direction) {
		// this is generally fine if the bloon doesn't pop at the same time. Which it shouldn't, I think.
		setX(getX() + direction.getKey() * bloon.getSpeed() / 5);
		setY(getY() + direction.getValue() * bloon.getSpeed() / 5);
		
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
		BloonsTowerDefence app = (BloonsTowerDefence)Gdx.app.getApplicationListener();
		Pair<Float, Float> direction = app.getMap().getDirection(getCenterX(), getCenterY());
		move(direction);
	}
}
