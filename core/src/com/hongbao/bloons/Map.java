package com.hongbao.bloons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.hongbao.bloons.actors.GirlActor;
import com.hongbao.bloons.actors.RenderableActor;
import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;


public class Map {
	
	public static final String BACKGROUND_MAPS_FOLDER = "img/maps/";
	public static final int TILE_LENGTH = 50;
	public static final int TILE_HEIGHT = 50;
	
	private String backgroundImage;
	private BloonManager bloonManager;
	private Pair<Float, Float>[][] directions;
	private Set<GirlActor> onStageGirls;
	private GirlActor selectedGirl;
	private Stage stage;
	
	public Map(String backgroundImage, Stage stage) {
		this.backgroundImage = backgroundImage;
		this.bloonManager = new BloonManager(stage);
		onStageGirls = new HashSet<>();
		selectedGirl = null;
		this.stage = stage;
	}
	
	public void setDirections(Pair<Float, Float>[][] directions) {
		this.directions = directions;
	}
	
	public void setBackgroundImage(String backgroundImage) {
		this.backgroundImage = backgroundImage;
	}
	
	public String getBackgroundImageFilePath() {
		return BACKGROUND_MAPS_FOLDER + backgroundImage;
	}
	
	public GirlActor getSelectedGirl() {
		return selectedGirl;
	}
	
	public void setSelectedGirl(GirlActor girlActor) {
		selectedGirl = girlActor;
	}
	
	public Pair<Float, Float> getDirection(float balloonX, float balloonY) {
		// Each "direction tile" is 50x50 px, maybe some minor tweaking later
		// There is an extra tile on the left and right of the screen so we have a smol x offset for that
		int xTile = (int)(balloonX + TILE_LENGTH) / TILE_LENGTH;
		int yTile = (int)balloonY / TILE_HEIGHT;
		if (xTile < directions.length && yTile < directions[xTile].length) {
			return directions[xTile][yTile];
		} else {
			return new Pair<>(0f, 0f);
		}
	}
	
	public BloonManager getBloonManager() {
		return bloonManager;
	}
	
	public boolean canPlaceGirl(GirlActor girlActor) {
		// You can't place a girl down if it violates any of the following rules:
		// It's out of bounds
		// It's colliding with the bloon path
		// It's colliding with another girl
		
		float x = girlActor.getCenterX() + TILE_LENGTH; // x is always offset by one tile because we have that extra tile on the left
		float y = girlActor.getCenterY();
		float r = girlActor.getCollisionRadius();
		
		if (y < 0 || y > 900 || x < 0 || x > 1500) {
			return false;
		}
		
		for (int i = 0; i < directions.length; i++) {
			for (int j = 0; j < directions[i].length; j++) {
				if (directions[i][j] != null && (directions[i][j].getKey() != 0 || directions[i][j].getValue() != 0)) {
					if (Math.abs(x - getCenterXOfTile(i)) < r + (TILE_LENGTH / 2f) && Math.abs(y - getCenterYOfTile(j)) < r + (TILE_HEIGHT / 2f)) {
						return false;
					}
				}
			}
		}
		
		for (GirlActor stageActor : onStageGirls) {
			if (distanceBetweenActors(girlActor, stageActor) < r + stageActor.getCollisionRadius()) {
				return false;
			}
		}
		
		return true;
	}
	
	public void placeGirl(GirlActor girlActor) {
		onStageGirls.add(girlActor);
		stage.addActor(girlActor);
		selectedGirl = girlActor;
		girlActor.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				selectedGirl = girlActor;
				event.setStage(null); // a somewhat hacky way of communicating to the stage that this event has already handled.
			}
		});
	}
	
	private static float getCenterXOfTile(int tile) {
		return tile * TILE_LENGTH + (TILE_LENGTH / 2f);
	}
	
	private static float getCenterYOfTile(int tile) {
		return tile * TILE_HEIGHT + (TILE_HEIGHT / 2f);
	}
	
	public static float distanceBetweenActors(RenderableActor actor1, RenderableActor actor2) {
		return (float)Math.sqrt(
		 Math.pow(actor1.getCenterX() - actor2.getCenterX(), 2) + Math.pow(actor1.getCenterY() - actor2.getCenterY(), 2));
	}
	
}
