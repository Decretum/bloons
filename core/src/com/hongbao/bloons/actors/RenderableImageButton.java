package com.hongbao.bloons.actors;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;


public class RenderableImageButton extends RenderableActor {
	
	public RenderableImageButton(ImageButton imageButton, int zIndex) {
		setActor(imageButton);
		setZIndex(zIndex);
	}
	
}
