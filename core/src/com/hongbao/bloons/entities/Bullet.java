package com.hongbao.bloons.entities;

import java.util.HashSet;
import java.util.Set;


public class Bullet {
	
	public static final String IMAGE_FOLDER = "img/projectiles/";
	
	private float speed;
	private int damage;
	private int pierce;
	private float maxRange; // because of how actions are evaluated, this is an approximation. But that's fine.
	private float distanceTraveled;
	private String imageFileName;
	private Set<Bloon> damagedBloons;
	
	public Bullet() {
		speed = 20f;
		damage = 1;
		pierce = 10;
		maxRange = 500;
		distanceTraveled = 0;
		imageFileName = IMAGE_FOLDER + "red_spell_card.png";
		damagedBloons = new HashSet<>(pierce);
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
	
	public boolean hasDamagedBloon(Bloon bloon) {
		return damagedBloons.contains(bloon);
	}
	
	public void damageBloon(Bloon bloon) {
		damagedBloons.add(bloon);
	}
	
}
