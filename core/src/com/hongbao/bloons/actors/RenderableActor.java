package com.hongbao.bloons.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;


public abstract class RenderableActor extends Actor {
	
	// This class exists because libgdx doesn't have a decent way to manage z-indexes. Any actor that is drawn
	// in the game (so really, all of them) needs to extend this class so that z indexes can be done properly.

	private int zIndex;
	private Actor actor;
	public TextureRegion textureRegion;
	
	public int getZIndex() {
		return zIndex;
	}
	
	public boolean setZIndex(int zIndex) {
		this.zIndex = zIndex;
		return true;
	}
	
	public Actor getActor() {
		return actor;
	}
	
	public void setActor(Actor actor) {
		this.actor = actor;
	}
	
	public TextureRegion getTextureRegion() {
		return textureRegion;
	}
	
	public void setTextureRegion(TextureRegion textureRegion) {
		this.textureRegion = textureRegion;
	}
	
	public float getCenterX() {
		return getX() + textureRegion.getTexture().getWidth() / 2f;
	}
	
	public float getCenterY() {
		return getY() + textureRegion.getTexture().getHeight() / 2f;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		actor.draw(batch, parentAlpha);
	}
	
	@Override
	public void act(float delta) {
		actor.act(delta);
	}
	
	@Override
	public boolean remove() {
		if (textureRegion != null && textureRegion.getTexture() != null) {
			textureRegion.getTexture().dispose();
		}
		return super.remove();
	}
	
}
