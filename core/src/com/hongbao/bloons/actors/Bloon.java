package com.hongbao.bloons.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import java.util.Arrays;
import java.util.List;

public class Bloon extends Actor {

	private static final List<String> colors = Arrays.asList(
			"red",
			"blue",
			"green",
			"yellow",
			"pink"
	);

	Texture texture;
	String color;
	int index = 0;
	float centerX;
	float centerY;

	public Bloon(final String color, float x, float y) { // todo other attributes
		this.color = color;
		centerX = x;
		centerY = y;

		texture = new Texture(Gdx.files.internal("img/" + color + "_bloon.png"));

		setX(centerX - texture.getWidth() / 2f);
		setY(centerY - texture.getHeight() / 2f);

		setTouchable(Touchable.enabled);
		setBounds(getX(), getY(), texture.getWidth(), texture.getHeight());
		addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if (button == Input.Buttons.LEFT) {
					Bloon target = (Bloon) event.getTarget();

					target.index++;
					if (target.index == colors.size()) {
						target.index = 0;
					}

					target.color = colors.get(target.index);
					target.texture = new Texture(Gdx.files.internal("img/" + target.color + "_bloon.png"));
					setX(centerX - texture.getWidth() / 2f);
					setY(centerY - texture.getHeight() / 2f);
					setBounds(getX(), getY(), texture.getWidth(), texture.getHeight());
					return true;
				}
				return false;
			}
		});
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(texture, getX(), getY());
	}
}
