package com.hongbao.bloons;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.hongbao.bloons.actors.GirlActor;
import com.hongbao.bloons.actors.RenderableImageButton;
import com.hongbao.bloons.actors.RenderableLabel;
import com.hongbao.bloons.comparators.SortByZIndex;
import com.hongbao.bloons.entities.Girl;
import com.hongbao.bloons.factories.GirlFactory;
import com.hongbao.bloons.factories.MapFactory;
import com.hongbao.bloons.helpers.ZIndex;


public class BloonsTouhouDefense implements ApplicationListener {
	
	public boolean paused;

	private Stage stage;
	private Player player;
	private Map map;
	private MusicPlayer musicPlayer;
	
	@Override
	public void create() {
		Gdx.graphics.setWindowedMode(1800, 900);
		paused = false;
		stage = new Stage();
		player = new Player();
		musicPlayer = new MusicPlayer();
		
		final RunnableAction bloonCreationAction = new RunnableAction();
		bloonCreationAction.setRunnable(() -> map.getBloonManager().createBloons());
		stage.addAction(Actions.repeat(RepeatAction.FOREVER, bloonCreationAction));
		
		Gdx.input.setInputProcessor(stage);
		
		createMap();
		createMenu();
		musicPlayer.playTitleMusic();
	}

	private void createMenu() {
		Skin skin = new Skin(Gdx.files.internal("uiskins/uiskin.json"));

		Drawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("img/ui/header.png"))));
		ImageButton title = new ImageButton(drawable);
		title.setPosition(1500, 0);
		stage.addActor(new RenderableImageButton(title, ZIndex.MENU_Z_INDEX));

		Label moneyLabel = new Label(String.valueOf(player.getMoney()), skin);
		moneyLabel.setPosition(1680, 765);
		moneyLabel.setFontScale(1.5f,1.5f);
		final RunnableAction moneyLabelAction = new RunnableAction();
		moneyLabelAction.setRunnable(() -> ((Label)moneyLabelAction.getActor()).setText(String.valueOf(player.getMoney())));
		moneyLabel.addAction(Actions.repeat(RepeatAction.FOREVER, moneyLabelAction));
		stage.addActor(new RenderableLabel(moneyLabel, ZIndex.MENU_ITEM_Z_INDEX));
		
		Label healthLabel = new Label(String.valueOf(player.getHealth()), skin);
		healthLabel.setPosition(1540, 765);
		healthLabel.setFontScale(1.5f,1.5f);
		final RunnableAction healthLabelAction = new RunnableAction();
		healthLabelAction.setRunnable(() -> {
			((Label)healthLabelAction.getActor()).setText(String.valueOf(player.getHealth()));
			
			if (player.getHealth() == 0) {
				pause();
			}
		});
		healthLabel.addAction(Actions.repeat(RepeatAction.FOREVER, healthLabelAction));
		stage.addActor(new RenderableLabel(healthLabel, ZIndex.MENU_ITEM_Z_INDEX));
	}
	
	public void createMap() {
		map = MapFactory.createMapWithTurn(stage);
		
		Drawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(map.getBackgroundImageFilePath()))));
		ImageButton backgroundMap = new ImageButton(drawable);
		backgroundMap.setPosition(0, 0);
		stage.addActor(backgroundMap);
	}
	
	public Map getMap() {
		return map;
	}
	
	public Player getPlayer() {
		return player;
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render() {
		if (!paused) {
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			stage.act(Gdx.graphics.getDeltaTime());
			
			Girl girl = null;
			
			if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
				girl = GirlFactory.createReimu();
			}
			
			if (Gdx.input.isButtonJustPressed(Input.Buttons.MIDDLE)) {
				girl = GirlFactory.createMarisa();
			}
			
			if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
				girl = GirlFactory.createSakuya();
			}
			
			if (Gdx.input.isButtonJustPressed(Input.Buttons.BACK)) {
				girl = GirlFactory.createYukari();
			}
			
			if (Gdx.input.isButtonJustPressed(Input.Buttons.FORWARD)) {
				girl = GirlFactory.createYuyuko();
			}
			
			if (girl != null) {
				if (player.canPurchaseGirl(girl)) {
					GirlActor girlActor = new GirlActor(girl, Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
					if (map.canPlaceGirl(girlActor)) {
						map.placeGirl(girlActor);
						player.purchaseGirl(girl);
					}
				}
			}
			
			stage.getActors().sort(new SortByZIndex());
		}
		stage.draw();
	}

	@Override
	public void pause() {
		paused = true;
		musicPlayer.pause();
	}

	@Override
	public void resume() {
		paused = false;
		musicPlayer.resume();
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
