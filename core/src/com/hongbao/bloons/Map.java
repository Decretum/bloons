package com.hongbao.bloons;

import com.badlogic.gdx.scenes.scene2d.Stage;
import javafx.util.Pair;


public class Map {
	
	public static final String BACKGROUND_MAPS_FOLDER = "img/maps/";
	public static final int TILE_LENGTH = 50;
	public static final int TILE_HEIGHT = 50;
	
	private String backgroundImage;
	private BloonManager bloonManager;
	private Pair<Float, Float>[][] directions;
	
	public Map(String backgroundImage, Stage stage) {
		this.backgroundImage = backgroundImage;
		this.bloonManager = new BloonManager(stage);
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
	
	public Pair<Float, Float> getDirection(float balloonX, float balloonY) {
		// Each "direction tile" is 50x50 px, maybe some minor tweaking later
		// There is an extra tile on the left and right of the screen so we have a smol x offset for that
		int xTile = (int)(balloonX + 50) / TILE_LENGTH;
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
	
}
