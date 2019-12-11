package com.hongbao.bloons.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hongbao.bloons.BloonsTouhouDefense;


public class DesktopLauncher {
	
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new BloonsTouhouDefense(), config);
	}
}
