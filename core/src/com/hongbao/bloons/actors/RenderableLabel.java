package com.hongbao.bloons.actors;

import com.badlogic.gdx.scenes.scene2d.ui.Label;


public class RenderableLabel extends RenderableActor {
	
	public RenderableLabel(Label label, int zIndex) {
		setActor(label);
		setZIndex(zIndex);
	}

	public Label getActor() {
		return (Label)super.getActor();
	}
}
