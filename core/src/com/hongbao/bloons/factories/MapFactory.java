package com.hongbao.bloons.factories;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.hongbao.bloons.Map;
import javafx.util.Pair;


public class MapFactory {
	
	public static Map createBasicMap(Stage stage) {
		Map map = new Map("basic_map.png", stage);
		Pair<Float, Float>[][] directions = initializeEmptyDirections();
		
		for (int x = 0; x < 32; x++) {
			directions[x][8] = new Pair<>(1f, 0f);
		}
		
		map.setDirections(directions);
		return map;
	}
	
	public static Map createMapWithTurn(Stage stage) {
		Map map = new Map("map_with_turn.png", stage);
		Pair<Float, Float>[][] directions = initializeEmptyDirections();
		directions[0][8] = new Pair<>(1f, 0f);
		directions[1][8] = new Pair<>(1f, 0f);
		directions[2][8] = new Pair<>(1f, 0f);
		directions[3][8] = new Pair<>(1f, 0f);
		directions[4][8] = new Pair<>(1f, 1f);
		directions[4][9] = new Pair<>(0f, 1f);
		directions[4][10] = new Pair<>(0f, 1f);
		directions[4][11] = new Pair<>(1f, 1f);
		
		for (int x = 5; x < 32; x++) {
			directions[x][11] = new Pair<>(1f, 0f);
		}
		
		map.setDirections(directions);
		return map;
	}
	
	
	private static Pair<Float, Float>[][] initializeEmptyDirections() {
		Pair<Float, Float>[][] directions = new Pair[32][18];
		for (int x = 0; x < directions.length - 1; x++) {
			for (int y = 0; y < directions[x].length; y++) {
				directions[x][y] = new Pair<>(0f, 0f);
			}
		}
		
		
		return directions;
	}
}
