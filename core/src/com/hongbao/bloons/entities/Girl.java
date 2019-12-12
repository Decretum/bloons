package com.hongbao.bloons.entities;

public class Girl {
	
	public static final String IMAGE_FOLDER = "img/characters/";

	private String name;
	private int attackDelay;
	private int cooldown;
	private float bulletSpeed;
	private int damage;
	private int pierce;
	private float range;
	private float visualRange;
	private String imageFileName;
	private String bulletFileName;
	private int cost;
	private int upgradeCost;

	public Girl() {
		name = "Remiu";
		attackDelay = 20;
		cooldown = 20;
		bulletSpeed = 20;
		damage = 1;
		pierce = 2;
		range = 500;
		visualRange = 200;
		imageFileName = IMAGE_FOLDER + "reimu.png";
		bulletFileName = "red_spell_card.png";
		cost = 100;
		upgradeCost = 100;
	}
	
	public Girl(String name, int attackDelay, float bulletSpeed, int damage, int pierce, float range, float visualRange, String imageFileName, String bulletFileName, int cost, int upgradeCost) {
		this.name = name;
		this.attackDelay = attackDelay;
		this.cooldown = attackDelay;
		this.bulletSpeed = bulletSpeed;
		this.damage = damage;
		this.pierce = pierce;
		this.range = range;
		this.visualRange = visualRange;
		this.imageFileName = IMAGE_FOLDER + imageFileName;
		this.bulletFileName = bulletFileName;
		this.cost = cost;
		this.upgradeCost = upgradeCost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAttackDelay() {
		return attackDelay;
	}
	
	public void setAttackDelay(int attackDelay) {
		this.attackDelay = attackDelay;
	}
	
	public int getCooldown() {
		return cooldown;
	}
	
	public void decrementCooldown() {
		cooldown--;
	}
	
	public void resetCooldown() {
		cooldown = attackDelay;
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
	
	public float getRange() {
		return range;
	}
	
	public void setRange(float range) {
		this.range = range;
	}
	
	public float getVisualRange() {
		return visualRange;
	}
	
	public void setVisualRange(float visualRange) {
		this.visualRange = visualRange;
	}
	
	public String getImageFileName() {
		return imageFileName;
	}
	
	public void setImageFileName(String imageFileName) {
		this.imageFileName = IMAGE_FOLDER + imageFileName;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getUpgradeCost() {
		return upgradeCost;
	}

	public void setUpgradeCost(int upgradeCost) {
		this.upgradeCost = upgradeCost;
	}

	public int getSellPrice() {
		return cost / 2;
	}

	public Bullet createBullet() {
		return new Bullet(bulletSpeed, damage, pierce, range, bulletFileName);
	}
	
}
