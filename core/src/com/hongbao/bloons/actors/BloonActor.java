package com.hongbao.bloons.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.hongbao.bloons.entities.Bloon;


public class BloonActor extends Actor {

	public static final float SCALE = 0.5f;

	Bloon bloon;
	Texture texture;

	public BloonActor(Bloon bloon, float x, float y) {
		this.bloon = bloon;
		texture = new Texture(Gdx.files.internal(bloon.getImageFileName()));

		setTouchable(Touchable.enabled);
		setBounds(x - texture.getWidth() * SCALE / 2f, y - texture.getHeight() * SCALE / 2f, texture.getWidth() * SCALE, texture.getHeight() * SCALE);
		addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if (button == Input.Buttons.LEFT) {
					BloonActor target = (BloonActor) event.getTarget();
					target.pop();
					return true;
				}
				return false;
			}
		});
	}

	public float getCenterX() {
		return getX() + texture.getWidth() * SCALE / 2f;
	}

	public float getCenterY() {
		return getY() + texture.getHeight() * SCALE / 2f;
	}

	public void pop() {
		bloon.pop();
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
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(texture, getX(), getY(), texture.getWidth() * SCALE, texture.getHeight() * SCALE);
	}
}
