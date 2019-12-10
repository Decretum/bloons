package com.hongbao.bloons.entities;

public class Bullet {
	
	public static final String IMAGE_FOLDER = "img/projectiles/";
	
	private float speed;
	private int damage;
	private int pierce; // todo make this somehow remember the balloons it has hit so it doesn't multihit them.
	private float maxRange; // because of how actions are evaluated, this is an approximation. But that's fine.
	private float distanceTraveled;
	private String imageFileName;
	
	public Bullet() {
		speed = 20f;
		damage = 1;
		pierce = 1;
		maxRange = 500;
		distanceTraveled = 0;
		imageFileName = IMAGE_FOLDER + "red_spell_card.png";
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public int getPierce() {
		return pierce;
	}
	
	public void decrementPierce() {
		pierce--;
	}
	
	public float getMaxRange() {
		return maxRange;
	}
	
	public void setMaxRange(float maxRange) {
		this.maxRange = maxRange;
	}
	
	public float getDistanceTraveled() {
		return distanceTraveled;
	}
	
	public void incrementDistanceTraveled() {
		distanceTraveled += speed / 5;
	}
	
	public String getImageFileName() {
		return imageFileName;
	}
	
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
}
