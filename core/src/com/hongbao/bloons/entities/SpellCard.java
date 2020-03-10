package com.hongbao.bloons.entities;

import com.hongbao.bloons.factories.GirlFactory;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SpellCard {
	
	public static final String IMAGE_FOLDER = "img/spellcards/";
	
	private Map<Integer, List<Bullet>> bulletsToCreate;
	private String overrideName;
	private String imageFileName;
	private int frame;
	private int lastFrame;
	
	public SpellCard(String overrideName, Map<Integer, List<Bullet>> bulletsToCreate, String imageFileName) {
		this.overrideName = overrideName;
		this.bulletsToCreate = bulletsToCreate;
		this.imageFileName = IMAGE_FOLDER + imageFileName;
		frame = 0;
		
		lastFrame = bulletsToCreate.keySet().stream()
		 .max(Comparator.naturalOrder())
		 .orElse(Integer.MAX_VALUE);
	}
	
	public String getOverrideName() {
		return overrideName;
	}
	
	public String getImageFileName() {
		return imageFileName;
	}
	
	public List<Bullet> getBulletsToCreateAndIncrementFrame() {
		List<Bullet> bullets = bulletsToCreate.get(frame);
		frame++;
		return bullets;
	}
	
	public boolean isExpired() {
		return frame > lastFrame;
	}
	
	public static SpellCard createReimuSpellCard() {
		Map<Integer, List<Bullet>> bulletsToCreate = new HashMap<>();
		for (int x = 0; x < 1000; x += 25) {
			Bullet bullet1 = GirlFactory.createReimu().createBullet();
			bullet1.setMaxRange(5000);
			bullet1.setInitialDXOverride(0);
			bullet1.setInitialDYOverride(1);
			
			Bullet bullet2 = GirlFactory.createReimu().createBullet();
			bullet2.setMaxRange(5000);
			bullet2.setInitialDXOverride((float)(Math.sqrt(3) / 2));
			bullet2.setInitialDYOverride(0.5f);
			
			Bullet bullet3 = GirlFactory.createReimu().createBullet();
			bullet3.setMaxRange(5000);
			bullet3.setInitialDXOverride((float)(-Math.sqrt(3) / 2));
			bullet3.setInitialDYOverride(0.5f);
			
			Bullet bullet4 = GirlFactory.createReimu().createBullet();
			bullet4.setMaxRange(5000);
			bullet4.setInitialDXOverride(0);
			bullet4.setInitialDYOverride(-1);
			
			Bullet bullet5 = GirlFactory.createReimu().createBullet();
			bullet5.setMaxRange(5000);
			bullet5.setInitialDXOverride((float)(Math.sqrt(3) / 2));
			bullet5.setInitialDYOverride(-0.5f);
			
			Bullet bullet6 = GirlFactory.createReimu().createBullet();
			bullet6.setMaxRange(5000);
			bullet6.setInitialDXOverride((float)(-Math.sqrt(3) / 2));
			bullet6.setInitialDYOverride(-0.5f);
			
			bulletsToCreate.put(x, Arrays.asList(bullet1, bullet2, bullet3, bullet4, bullet5, bullet6));
		}
		return new SpellCard("Reimu", bulletsToCreate, "reimu_spell.png");
	}
	

}
