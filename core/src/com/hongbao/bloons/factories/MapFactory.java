package com.hongbao.bloons.factories;

import com.hongbao.bloons.Map;
import javafx.util.Pair;


public class MapFactory {
	
	public static Map createBasicMap() {
		Map map = new Map("basic_map.png");
		Pair<Float, Float>[][] directions = initializeEmptyDirections();
		
		for (int x = 0; x < 32; x++) {
			directions[x][8] = new Pair<>(1f, 0f);
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
