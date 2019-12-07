package com.hongbao.bloons;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class BloonsTowerDefence extends ApplicationAdapter implements InputProcessor {
	
	SpriteBatch batch;
	Music titleBGM;
	BitmapFont font;
	BitmapFont volume;
	Texture img;
	String message;
	int textX;
	int textY;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		volume = new BitmapFont();
		img = new Texture("badlogic.jpg");
		message = "";
		textX = 200;
		textY = 200;
		
		Gdx.input.setInputProcessor(this);
		
		titleBGM = Gdx.audio.newMusic(Gdx.files.internal("music/title.mp3"));
		titleBGM.setVolume(0.5f);
		titleBGM.play();
	}
	
	@Override
	public void render() {
		Gdx.gl.glClearColor(0.5f, 0.5f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//batch.draw(img, 0, 0);
		font.draw(batch, message, textX, textY);
		volume.draw(batch, "Volume: " + ((int)((titleBGM.getVolume() + 0.005f) * 100)), 10, 15);
		batch.end();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) || Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT)) {
			if (keycode == Input.Keys.ENTER) {
				message = "";
				return true;
			}
		}
		if (keycode != Input.Keys.CONTROL_LEFT && keycode != Input.Keys.CONTROL_RIGHT) {
			if (keycode == Input.Keys.SPACE) {
				message += " ";
			} else if (keycode == Input.Keys.BACKSPACE) {
				if (message.length() != 0) {
					message = message.substring(0, message.length() - 1);
				}
			} else {
				message += Input.Keys.toString(keycode);
			}
		}
		return true;
	}
	
	@Override
	public boolean keyUp(int keycode) {
		return false;
	}
	
	@Override
	public boolean keyTyped(char character) {
		return false;
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		textX = screenX;
		textY = Gdx.graphics.getHeight() - screenY;
		return true;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		textX = screenX;
		textY = Gdx.graphics.getHeight() - screenY;
		return true;
	}
	
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}
	
	@Override
	public boolean scrolled(int amount) {
		float newVolume = titleBGM.getVolume() + -0.1f * amount;
		if (newVolume < 0) {
			newVolume = 0;
		}
		if (newVolume > 1) {
			newVolume = 1;
		}
		titleBGM.setVolume(newVolume);
		
		return true;
	}
	
}
