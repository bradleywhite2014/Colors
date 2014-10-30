package com.colorverse.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.colorverse.game.ColorVerse;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		
		config.title = "ColorVerse";
		config.width = 480;
		config.height = 800;
		
		new LwjglApplication(new ColorVerse(), config);
		
		
	}
}
