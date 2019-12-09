package com.hongbao.bloons.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;


public abstract class RenderableActor extends Actor {
	
	// This class exists because libgdx doesn't have a decent way to manage z-indexes. Any actor that is drawn
	// in the game (so really, all of them) needs to extend this class so that z indexes can be done properly.

	private int zIndex;
	private Actor actor;
	
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
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		actor.draw(batch, parentAlpha);
	}
	
	@Override
	public void act(float delta) {
		actor.act(delta);
	}
	
}
