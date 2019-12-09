package com.hongbao.bloons.entities;

public class Bullet {
	
	public static final String IMAGE_FOLDER = "img/projectiles/";
	
	private float speed;
	private int damage;
	private int pierce;
	private String imageFileName;
	
	public Bullet() {
		speed = 20f;
		damage = 1;
		pierce = 1;
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
	
	public void setPierce(int pierce) {
		this.pierce = pierce;
	}
	
	public String getImageFileName() {
		return imageFileName;
	}
	
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
}