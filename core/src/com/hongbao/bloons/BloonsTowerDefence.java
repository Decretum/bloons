package com.hongbao.bloons;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.hongbao.bloons.actors.Bloon;


public class BloonsTowerDefence implements ApplicationListener {
	
	Music titleBGM;

	private Stage stage;
	
	@Override
	public void create() {
		Gdx.graphics.setWindowedMode(1800, 900);
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		Bloon bloon = new Bloon("red", Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
		stage.addActor(bloon);
		
		titleBGM = Gdx.audio.newMusic(Gdx.files.internal("music/title.mp3"));
		titleBGM.setVolume(0.5f);
		titleBGM.play();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		stage.dispose();
	}
	
}
