package com.hongbao.bloons;

import com.hongbao.bloons.entities.Girl;


public class Player {
	
	private int money;
	private int health;
	
	public Player() {
		money = 200;
		health = 200;
	}
	
	public Player(int money, int health) {
		this.money = money;
		this.health = health;
	}
	
	public int getMoney() {
		return money;
	}
	
	public void earnMoney(int money) {
		this.money += money;
	}
	
	public boolean spendMoney(int money) {
		if (money > this.money) {
			return false;
		} else {
			this.money -= money;
			return true;
		}
	}
	
	public int getHealth() {
		return health;
	}
	
	public void decreaseHealth(int health) {
		this.health -= health;
		if (this.health < 0) {
			this.health = 0;
		}
	}
	
	public boolean purchaseGirl(Girl girl) {
		// really regretting the class name choice now
		return spendMoney(girl.getCost());
	}
	
}
