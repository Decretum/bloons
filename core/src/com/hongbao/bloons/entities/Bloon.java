package com.hongbao.bloons.entities;

import java.util.HashMap;
import java.util.Map;

import static com.hongbao.bloons.entities.Bloon.Color.*;

public class Bloon {

	public enum Color {

		RED("red"),
		BLUE("blue"),
		GREEN("green"),
		YELLOW("yellow"),
		PINK("pink");

		private String value;

		public String getValue() {
			return value;
		}

		private Color(String value) {
			this.value = value;
		}
	}

	public static final String IMAGE_FOLDER = "img/";
	public static final String CAMO_BLOON_DENOTATION = "_camo";
	public static final String REGROWTH_BLOON_DENOTATION = "_regrowth";
	public static final String IMAGE_FILE_EXTENSION = ".png";

	public static final Map<Integer, Color> HEALTH_TO_COLOR = new HashMap<Integer, Color>() {
		{
			put(1, RED);
			put(2, BLUE);
			put(3, GREEN);
			put(4, YELLOW);
			put(5, PINK);
		}
	};


	private Color color;
	private String imageFileName;
	private int health;
	private boolean camo;
	private boolean regen;

	public Bloon(Color color, int health, boolean camo, boolean regen) {
		this.color = color;
		this.health = health;
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

	public int pop() {
		return pop(1);
	}

	public int pop(int damage) {
		int oldHealth = health;
		health -= damage;
		if (health > 0) {
			color = HEALTH_TO_COLOR.get(health);
			imageFileName = createImageFileName(color.getValue(), camo, regen);
		} else {
			health = 0;
		}
		return oldHealth - health;
	}

	public static String createImageFileName(String color, boolean camo, boolean regen) {
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
