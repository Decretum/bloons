package com.hongbao.bloons;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.hongbao.bloons.actors.BloonActor;
import com.hongbao.bloons.entities.Bloon;
import com.hongbao.bloons.factories.BloonFactory;
import com.hongbao.bloons.factories.MapFactory;


public class BloonsTowerDefence implements ApplicationListener {

	private Music titleBGM; // todo probably could be refactored to an object
	public int health;
	public int money;

	private Stage stage;
	private Map map;
	
	@Override
	public void create() {
		Gdx.graphics.setWindowedMode(1800, 900);
		health = 100;
		money = 0;
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		createMap();
		
		createBloon(BloonFactory.createRandomBloon());
		
		createMenu();

		titleBGM = Gdx.audio.newMusic(Gdx.files.internal("music/title.mp3"));
		titleBGM.setVolume(0.5f);
		titleBGM.setLooping(true);
		titleBGM.play();
	}

	private void createMenu() {
		Skin skin = new Skin(Gdx.files.internal("uiskins/uiskin.json"));

		Drawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("img/header.png"))));
		ImageButton title = new ImageButton(drawable);
		title.setPosition(1500, 0);
		stage.addActor(title);

		Label moneyLabel = new Label(String.valueOf(money), skin);
		moneyLabel.setPosition(1680, 760);
		moneyLabel.setFontScale(1.5f,1.5f);
		final RunnableAction moneyLabelAction = new RunnableAction();
		moneyLabelAction.setRunnable(new Runnable() {
			@Override
			public void run() {
				((Label)moneyLabelAction.getActor()).setText(String.valueOf(money));
			}
		});
		moneyLabel.addAction(Actions.repeat(RepeatAction.FOREVER, moneyLabelAction));
		stage.addActor(moneyLabel);
	}
	
	public void createMap() {
		map = MapFactory.createBasicMap();
		
		Drawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(map.getBackgroundImageFilePath()))));
		ImageButton backgroundMap = new ImageButton(drawable);
		backgroundMap.setPosition(0, 0);
		stage.addActor(backgroundMap);
	}
	
	public Map getMap() {
		return map;
	}
	
	//public void createBloons() {
	//	Thread createBloonsThread = new Thread(new BloonCreator());
	//	createBloonsThread.start();
	//}
	
	public void createBloon(Bloon bloon) {
		stage.addActor(new BloonActor(bloon, -25, 425));
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
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
