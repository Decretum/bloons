package com.hongbao.bloons.factories;

import com.hongbao.bloons.entities.Girl;


public class GirlFactory {

	public static Girl createReimu() {
		// 0.1
		return new Girl(20, 20, 1, 2, 500, 200, "reimu.png", "red_spell_card.png", 100);
	}
	
	public static Girl createYukari() {
		// 0.1667
		return new Girl(30, 50, 5, 1, 600, 300, "yukari.png", "purple_energy.png", 160);
	}
	
	public static Girl createMarisa() {
		// 0.1
		return new Girl(20, 35, 2, 1, 500, 100, "marisa.png", "blue_magic_missile.png", 100);
	}
	
	public static Girl createAlice() {
		// 0.1333
		return new Girl(30, 50, 2, 2, 600, 200, "alice.png", "magic_spike.png", 140);
	}
	
	public static Girl createSakuya() {
		// 0.1
		return new Girl(10, 20, 1, 1, 500, 150, "sakuya.png", "blue_knives.png", 120);
	}
	
	public static Girl createRemilia() {
		// 0.3
		return new Girl(10, 35, 1, 3, 300, 200, "remilia.png", "bat.png", 200);
	}
	
	public static Girl createYoumu() {
		// 0.12
		return new Girl(50, 20, 2, 3, 200, 200, "youmu.png", "sword_slash.png", 120);
	}
	
	public static Girl createYuyuko() {
		// 0.25
		return new Girl(40, 10, 1, 10, 500, 200, "yuyuko.png", "pink_butterfly.png", 160);
	}
	
	
	
}
