package com.hongbao.bloons.entities;

import com.hongbao.bloons.helpers.BloonPoppedResult;

import java.util.HashMap;
import java.util.Map;

import static com.hongbao.bloons.entities.Bloon.Color.BLACK;
import static com.hongbao.bloons.entities.Bloon.Color.BLUE;
import static com.hongbao.bloons.entities.Bloon.Color.CERAMIC;
import static com.hongbao.bloons.entities.Bloon.Color.GREEN;
import static com.hongbao.bloons.entities.Bloon.Color.LEAD;
import static com.hongbao.bloons.entities.Bloon.Color.PINK;
import static com.hongbao.bloons.entities.Bloon.Color.RAINBOW;
import static com.hongbao.bloons.entities.Bloon.Color.RED;
import static com.hongbao.bloons.entities.Bloon.Color.YELLOW;
import static com.hongbao.bloons.entities.Bloon.Color.ZEBRA;


public class Bloon {

	public enum Color {

		RED("red"),
		BLUE("blue"),
		GREEN("green"),
		YELLOW("yellow"),
		PINK("pink"),
		BLACK("black"),
		LEAD("lead"),
		ZEBRA("zebra"),
		RAINBOW("rainbow"),
		CERAMIC("ceramic");

		private String value;

		public String getValue() {
			return value;
		}

		private Color(String value) {
			this.value = value;
		}
	}

	public static final String IMAGE_FOLDER = "img/bloons/";
	public static final String CAMO_BLOON_DENOTATION = "_camo";
	public static final String REGROWTH_BLOON_DENOTATION = "_regrowth";
	public static final String IMAGE_FILE_EXTENSION = ".png";

	public static final Map<Integer, Color> HEALTH_TO_COLOR = new HashMap<Integer, Color>() {
		{ // todo probably make this a method once blimps exist
			put(1, RED);
			put(2, BLUE);
			put(3, GREEN);
			put(4, YELLOW);
			put(5, PINK);
			put(6, BLACK);
			put(7, ZEBRA);
			put(8, RAINBOW);
			put(9, CERAMIC);
			put(10, CERAMIC);
			put(11, CERAMIC);
			put(12, CERAMIC);
			put(13, CERAMIC);
			put(14, CERAMIC);
			put(15, CERAMIC);
			put(16, CERAMIC);
			put(17, CERAMIC);
			put(18, CERAMIC);
		}
	};
	
	public static final Map<Color, Integer> COLOR_TO_SPEED = new HashMap<Color, Integer>() {
		{
			put(RED, 5);
			put(BLUE, 6);
			put(GREEN, 7);
			put(YELLOW, 8);
			put(PINK, 9);
			put(BLACK, 7);
			put(LEAD, 5);
			put(ZEBRA, 7);
			put(RAINBOW, 8);
			put(CERAMIC, 7);
		}
	};


	private Color color;
	private String imageFileName;
	private int health;
	private int speed;
	private int distanceTravelled;
	private boolean camo;
	private boolean regen;

	public Bloon(Color color, int health, boolean camo, boolean regen) {
		this.color = color;
		this.health = health;
		speed = COLOR_TO_SPEED.get(color);
		this.camo = camo;
		this.regen = regen;
		this.imageFileName = createImageFileName(color.getValue(), camo, regen);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getDistanceTravelled() {
		return distanceTravelled;
	}
	
	public void incrementDistanceTravelled() {
		distanceTravelled += speed;
	}
	
	public boolean isCamo() {
		return camo;
	}

	public void setCamo(boolean camo) {
		this.camo = camo;
	}

	public boolean isRegen() {
		return regen;
	}

	public void setRegen(boolean regen) {
		this.regen = regen;
	}
	
	public boolean willPopBloon(int damage) {
		int resultingHealth = health - damage;
		
		if (resultingHealth <= 0) {
			return true;
		}
		
		Color resultingColor = HEALTH_TO_COLOR.get(resultingHealth);
		return resultingColor != color;
	}
	
	public void damage(int damage) {
		this.health -= damage;
	}

	public BloonPoppedResult pop(int damage) {
		return new BloonPoppedResult(this, damage);
	}
	
	private static String createImageFileName(String color, boolean camo, boolean regen) {
		StringBuilder fileNameBuilder = new StringBuilder(IMAGE_FOLDER);
		fileNameBuilder.append(color);
		if (camo) {
			fileNameBuilder.append(CAMO_BLOON_DENOTATION);
		}
		if (regen) {
			fileNameBuilder.append(REGROWTH_BLOON_DENOTATION);
		}
		fileNameBuilder.append("_bloon");
		fileNameBuilder.append(IMAGE_FILE_EXTENSION);

		return fileNameBuilder.toString();
	}
	
}
