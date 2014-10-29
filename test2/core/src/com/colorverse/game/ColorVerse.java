package com.colorverse.game;

import com.badlogic.gdx.Game;
import com.colorverse.screens.Splash;

public class ColorVerse extends Game {

	
	@Override
	public void create () {
		setScreen(new Splash());
	}

	@Override
	public void render () {
		super.render();
	}
	

	@Override
	public void dispose() {
		super.dispose();
	}
	
	@Override
	public void resume() {
		super.resume();
	}
	
	@Override
	public void resize(int width,int height) {
		super.resize(width,height);
	}
	
	@Override
	public void pause() {
		super.pause();
	}
}
