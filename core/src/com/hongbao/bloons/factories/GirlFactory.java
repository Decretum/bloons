package com.hongbao.bloons.factories;

import com.hongbao.bloons.entities.Girl;

import java.util.Arrays;

import static com.hongbao.bloons.entities.Girl.NO_UPGRADES_AVAILABLE;


public class GirlFactory {

	public static Girl createReimu() {
		return new Girl(
		 "Reimu",
		 Arrays.asList(20, 15, 10),
		 Arrays.asList(20f, 20f, 20f),
		 Arrays.asList(1, 1, 2),
		 Arrays.asList(2, 2, 2),
		 Arrays.asList(500f, 500f, 500f),
		 Arrays.asList(200f, 220f, 250f),
		 true,
		 "reimu.png",
		 "red_spell_card.png",
		 100,
		 Arrays.asList(50, 50, NO_UPGRADES_AVAILABLE)
		);
	}
	
	public static Girl createYukari() {
		return new Girl("Yukari",
		 Arrays.asList(30, 25, 5),
		 Arrays.asList(50f, 100f, 150f),
		 Arrays.asList(5, 6, 7),
		 Arrays.asList(1, 1, 1),
		 Arrays.asList(600f, 700f, 1000f),
		 Arrays.asList(300f, 400f, 500f),
		 false,
		 "yukari.png",
		 "purple_energy.png",
		 160,
		 Arrays.asList(80, 80, NO_UPGRADES_AVAILABLE)
		);
	}
	
	public static Girl createMarisa() {
		return new Girl(
		 "Marisa",
		 Arrays.asList(20, 20, 20),
		 Arrays.asList(35f, 50f, 75f),
		 Arrays.asList(2, 5, 10),
		 Arrays.asList(1, 1, 1),
		 Arrays.asList(500f, 500f, 500f),
		 Arrays.asList(100f, 125f, 150f),
		 false,
		 "marisa.png",
		 "blue_magic_missile.png",
		 100,
		 Arrays.asList(50, 50, NO_UPGRADES_AVAILABLE)
		);
	}
	
	public static Girl createAlice() {
		return new Girl(
		 "Alice",
		 Arrays.asList(30, 20, 10),
		 Arrays.asList(50f, 50f, 50f),
		 Arrays.asList(2, 3, 4),
		 Arrays.asList(2, 3, 4),
		 Arrays.asList(600f, 600f, 600f),
		 Arrays.asList(200f, 250f, 300f),
		 false,
		 "alice.png",
		 "magic_spike.png",
		 140,
		 Arrays.asList(70, 70, NO_UPGRADES_AVAILABLE)
		);
	}
	
	public static Girl createSakuya() {
		return new Girl("Sakuya",
		 Arrays.asList(10, 5, 2),
		 Arrays.asList(20f, 20f, 20f),
		 Arrays.asList(1, 1, 1),
		 Arrays.asList(1, 1, 1),
		 Arrays.asList(500f, 500f, 500f),
		 Arrays.asList(150f, 200f, 250f),
		 false,
		 "sakuya.png",
		 "blue_knives.png",
		 120,
		 Arrays.asList(60, 60, NO_UPGRADES_AVAILABLE)
		);
	}
	
	public static Girl createRemilia() {
		return new Girl(
		 "Remilia",
		 Arrays.asList(10, 10, 10),
		 Arrays.asList(35f, 35f, 35f),
		 Arrays.asList(1, 2, 3),
		 Arrays.asList(3, 4, 5),
		 Arrays.asList(300f, 300f, 300f),
		 Arrays.asList(200f, 200f, 200f),
		 false,
		 "remilia.png",
		 "bat.png",
		 200,
		 Arrays.asList(100, 100, NO_UPGRADES_AVAILABLE)
		);
	}
	
	public static Girl createYoumu() {
		return new Girl(
		 "Youmu",
		 Arrays.asList(50, 45, 40),
		 Arrays.asList(20f, 25f, 30f),
		 Arrays.asList(2, 3, 4),
		 Arrays.asList(3, 3, 3),
		 Arrays.asList(200f, 300f, 400f),
		 Arrays.asList(200f, 300f, 400f),
		 false,
		 "youmu.png",
		 "sword_slash.png",
		 120,
		 Arrays.asList(60, 60, NO_UPGRADES_AVAILABLE)
		);
	}
	
	public static Girl createYuyuko() {
		return new Girl(
		 "Yuyuko",
		 Arrays.asList(40, 40, 30),
		 Arrays.asList(10f, 11f, 12f),
		 Arrays.asList(1, 1, 2),
		 Arrays.asList(10, 20, 50),
		 Arrays.asList(500f, 600f, 700f),
		 Arrays.asList(200f, 250f, 300f),
		 true,
		 "yuyuko.png",
		 "pink_butterfly.png",
		 160,
		 Arrays.asList(80, 80, NO_UPGRADES_AVAILABLE)
		);
	}
	
}
