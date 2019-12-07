package com.hongbao.bloons;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.hongbao.bloons.actors.BloonActor;
import com.hongbao.bloons.entities.Bloon;
import com.hongbao.bloons.factories.BloonFactory;


public class BloonsTowerDefence implements ApplicationListener {
	
	Music titleBGM;

	private Stage stage;
	
	@Override
	public void create() {
		Gdx.graphics.setWindowedMode(1800, 900);
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		for (int x = 0; x < 1800; x += 50) {
			for (int y = 0; y < 900; y += 50) {
				stage.addActor(new BloonActor(BloonFactory.createRandomBloon(), x, y));
			}
		}

//		BloonActor bloonActor1 = new BloonActor(BloonFactory.createPinkBloon(), Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
//		BloonActor bloonActor2 = new BloonActor(BloonFactory.createPinkBloon(), Gdx.graphics.getWidth() / 2f + 300, Gdx.graphics.getHeight() / 2f);
//		BloonActor bloonActor3 = new BloonActor(BloonFactory.createPinkBloon(), Gdx.graphics.getWidth() / 2f - 300, Gdx.graphics.getHeight() / 2f);
//		BloonActor bloonActor4 = new BloonActor(BloonFactory.createPinkBloon(), Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f + 300);
//		BloonActor bloonActor5 = new BloonActor(BloonFactory.createPinkBloon(), Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - 300);
//		BloonActor bloonActor6 = new BloonActor(BloonFactory.createPinkBloon(), Gdx.graphics.getWidth() / 2f + 300, Gdx.graphics.getHeight() / 2f + 300);
//		BloonActor bloonActor7 = new BloonActor(BloonFactory.createPinkBloon(), Gdx.graphics.getWidth() / 2f + 300, Gdx.graphics.getHeight() / 2f - 300);
//		BloonActor bloonActor8 = new BloonActor(BloonFactory.createPinkBloon(), Gdx.graphics.getWidth() / 2f - 300, Gdx.graphics.getHeight() / 2f + 300);
//		BloonActor bloonActor9 = new BloonActor(BloonFactory.createPinkBloon(), Gdx.graphics.getWidth() / 2f - 300, Gdx.graphics.getHeight() / 2f - 300);

		titleBGM = Gdx.audio.newMusic(Gdx.files.internal("music/title.mp3"));
		titleBGM.setVolume(0.5f);
		titleBGM.setLooping(true);
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
