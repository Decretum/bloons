package com.hongbao.bloons.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.hongbao.bloons.BloonManager;
import com.hongbao.bloons.BloonsTouhouDefense;
import com.hongbao.bloons.entities.Bullet;
import com.hongbao.bloons.entities.SpellCard;

import java.util.List;


public class SpellCardActor extends RenderableActor {

	private SpellCard spellCard;
	
	public SpellCardActor(SpellCard spellCard, float x, float y) {
		this.spellCard = spellCard;
		setBounds(
		 x,
		 y,
		 0,
		 0
		);
	}
	
	public SpellCard getSpellCard() {
		return spellCard;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		// todo do we want to have a renderable? maybe? then we will also need more stuff in the constructor
	}
	
	@Override
	public void act(float delta) {
		List<Bullet> bulletsToCreate = spellCard.getBulletsToCreateAndIncrementFrame();
		
		if (bulletsToCreate != null) {
			BloonManager bloonManager = ((BloonsTouhouDefense)Gdx.app.getApplicationListener()).getMap().getBloonManager();
			
			for (Bullet bullet : bulletsToCreate) {
				BulletActor bulletActor = new BulletActor(
				 bullet,
				 getX(),
				 getY(),
				 bullet.getInitialDXOverride(),
				 bullet.getInitialDYOverride()
				); // todo the spell needs a direction maybe
				bulletActor.setSpellCardOverride(spellCard.getOverrideName());
				bloonManager.addBulletToStage(bulletActor);
			}
		}
	}
	
}
