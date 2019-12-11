package com.hongbao.bloons;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
	
	public static final boolean INFINITE_HEALTH = false; // for testing, mostly
	public static final boolean INFINITE_MONEY = false;
	
	public boolean paused;

	private Stage stage;
	private Player player;
	private Map map;
	private MusicPlayer musicPlayer;
	private ShapeRenderer shapeRenderer;
	
	
	@Override
	public void create() {
		Gdx.graphics.setWindowedMode(1800, 900);
		paused = false;
		stage = new Stage();
		player = new Player();
		musicPlayer = new MusicPlayer();
		shapeRenderer = new ShapeRenderer();
		
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

		ImageButton title = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("img/ui/header.png")))));
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
		
		ImageButton purchaseReimu = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("img/ui/reimu_box.png")))));
		purchaseReimu.setPosition(1504, 676);
		stage.addActor(new RenderableImageButton(purchaseReimu, ZIndex.MENU_Z_INDEX));
		
		Label reimuCost = new Label(String.valueOf(GirlFactory.createReimu().getCost()), skin);
		reimuCost.setPosition(1680, 700);
		reimuCost.setFontScale(1.5f,1.5f);
		reimuCost.addAction(Actions.repeat(RepeatAction.FOREVER, createNewCostLabelAction()));
		stage.addActor(new RenderableLabel(reimuCost, ZIndex.MENU_ITEM_Z_INDEX));
		
		ImageButton purchaseYukari = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("img/ui/yukari_box.png")))));
		purchaseYukari.setPosition(1504, 604);
		stage.addActor(new RenderableImageButton(purchaseYukari, ZIndex.MENU_Z_INDEX));
		
		Label yukariCost = new Label(String.valueOf(GirlFactory.createYukari().getCost()), skin);
		yukariCost.setPosition(1680, 628);
		yukariCost.setFontScale(1.5f,1.5f);
		yukariCost.addAction(Actions.repeat(RepeatAction.FOREVER, createNewCostLabelAction()));
		stage.addActor(new RenderableLabel(yukariCost, ZIndex.MENU_ITEM_Z_INDEX));
		
		ImageButton purchaseMarisa = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("img/ui/marisa_box.png")))));
		purchaseMarisa.setPosition(1504, 532);
		stage.addActor(new RenderableImageButton(purchaseMarisa, ZIndex.MENU_Z_INDEX));
		
		Label marisaCost = new Label(String.valueOf(GirlFactory.createMarisa().getCost()), skin);
		marisaCost.setPosition(1680, 556);
		marisaCost.setFontScale(1.5f,1.5f);
		marisaCost.addAction(Actions.repeat(RepeatAction.FOREVER, createNewCostLabelAction()));
		stage.addActor(new RenderableLabel(marisaCost, ZIndex.MENU_ITEM_Z_INDEX));
		
		ImageButton purchaseAlice = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("img/ui/alice_box.png")))));
		purchaseAlice.setPosition(1504, 460);
		stage.addActor(new RenderableImageButton(purchaseAlice, ZIndex.MENU_Z_INDEX));
		
		Label aliceCost = new Label(String.valueOf(GirlFactory.createAlice().getCost()), skin);
		aliceCost.setPosition(1680, 484);
		aliceCost.setFontScale(1.5f,1.5f);
		aliceCost.addAction(Actions.repeat(RepeatAction.FOREVER, createNewCostLabelAction()));
		stage.addActor(new RenderableLabel(aliceCost, ZIndex.MENU_ITEM_Z_INDEX));
		
		ImageButton purchaseSakuya = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("img/ui/sakuya_box.png")))));
		purchaseSakuya.setPosition(1504, 388);
		stage.addActor(new RenderableImageButton(purchaseSakuya, ZIndex.MENU_Z_INDEX));
		
		Label sakuyaCost = new Label(String.valueOf(GirlFactory.createSakuya().getCost()), skin);
		sakuyaCost.setPosition(1680, 412);
		sakuyaCost.setFontScale(1.5f,1.5f);
		sakuyaCost.addAction(Actions.repeat(RepeatAction.FOREVER, createNewCostLabelAction()));
		stage.addActor(new RenderableLabel(sakuyaCost, ZIndex.MENU_ITEM_Z_INDEX));
		
		ImageButton purchaseRemilia = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("img/ui/remilia_box.png")))));
		purchaseRemilia.setPosition(1504, 316);
		stage.addActor(new RenderableImageButton(purchaseRemilia, ZIndex.MENU_Z_INDEX));
		
		Label remiliaCost = new Label(String.valueOf(GirlFactory.createRemilia().getCost()), skin);
		remiliaCost.setPosition(1680, 340);
		remiliaCost.setFontScale(1.5f,1.5f);
		remiliaCost.addAction(Actions.repeat(RepeatAction.FOREVER, createNewCostLabelAction()));
		stage.addActor(new RenderableLabel(remiliaCost, ZIndex.MENU_ITEM_Z_INDEX));
		
		ImageButton purchaseYoumu = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("img/ui/youmu_box.png")))));
		purchaseYoumu.setPosition(1504, 244);
		stage.addActor(new RenderableImageButton(purchaseYoumu, ZIndex.MENU_Z_INDEX));
		
		Label youmuCost = new Label(String.valueOf(GirlFactory.createYoumu().getCost()), skin);
		youmuCost.setPosition(1680, 268);
		youmuCost.setFontScale(1.5f,1.5f);
		youmuCost.addAction(Actions.repeat(RepeatAction.FOREVER, createNewCostLabelAction()));
		stage.addActor(new RenderableLabel(youmuCost, ZIndex.MENU_ITEM_Z_INDEX));
		
		ImageButton purchaseYuyuko = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("img/ui/yuyuko_box.png")))));
		purchaseYuyuko.setPosition(1504, 172);
		stage.addActor(new RenderableImageButton(purchaseYuyuko, ZIndex.MENU_Z_INDEX));
		
		Label yuyukoCost = new Label(String.valueOf(GirlFactory.createYuyuko().getCost()), skin);
		yuyukoCost.setPosition(1680, 196);
		yuyukoCost.setFontScale(1.5f,1.5f);
		yuyukoCost.addAction(Actions.repeat(RepeatAction.FOREVER, createNewCostLabelAction()));
		stage.addActor(new RenderableLabel(yuyukoCost, ZIndex.MENU_ITEM_Z_INDEX));
	}
	
	private RunnableAction createNewCostLabelAction() {
		// Apparently they don't like sharing
		RunnableAction costLabelAction = new RunnableAction();
		costLabelAction.setRunnable(() -> {
			Label label = (Label)costLabelAction.getActor();
			int cost = Integer.parseInt(label.getText().toString());
			
			if (cost <= player.getMoney()) {
				label.setColor(Color.WHITE);
			} else {
				label.setColor(Color.RED);
			}
		});
		return costLabelAction;
	}
	
	public void createMap() {
		stage.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (event.getStage() != null) {
					map.setSelectedGirl(null);
				}
			}
		});
		
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
			
			if (Gdx.input.isButtonJustPressed(Input.Buttons.MIDDLE)) {
				girl = GirlFactory.createRemilia();
			}
			
			if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
				girl = GirlFactory.createAlice();
			}
			
			if (Gdx.input.isButtonJustPressed(Input.Buttons.BACK)) {
				girl = GirlFactory.createYoumu();
			}
			
			if (Gdx.input.isButtonJustPressed(Input.Buttons.FORWARD)) {
				girl = GirlFactory.createReimu();
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
		
		if (map.getSelectedGirl() != null) {
			// Draw the range of collision, the range of sight, and range of... well, range
			shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
			shapeRenderer.circle(map.getSelectedGirl().getCenterX(), map.getSelectedGirl().getCenterY(), map.getSelectedGirl().getCollisionRadius());
			shapeRenderer.circle(map.getSelectedGirl().getCenterX(), map.getSelectedGirl().getCenterY(), map.getSelectedGirl().getGirl().getVisualRange());
			shapeRenderer.circle(map.getSelectedGirl().getCenterX(), map.getSelectedGirl().getCenterY(), map.getSelectedGirl().getGirl().getRange());
			shapeRenderer.end();
		}
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
