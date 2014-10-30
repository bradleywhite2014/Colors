package com.colorverse.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.colorverse.screens.Splash;

public class ColorVerse extends Game {

	public SpriteBatch batch;
	public BitmapFont font;
	
	@Override
	public void create () {
		
		batch = new SpriteBatch();
		font = new BitmapFont();
		
		this.setScreen(new Splash(this));
	}

	@Override
	public void render () {
		super.render();
	}
	

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
		font.dispose();
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
