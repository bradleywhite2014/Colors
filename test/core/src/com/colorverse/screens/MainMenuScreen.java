package com.colorverse.screens;

import java.awt.Font;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.colorverse.game.ColorVerse;
import com.colorverse.tween.SpriteAccessor;

public class MainMenuScreen implements Screen {

	private SpriteBatch batch;
	private Sprite mainMenu;
	private TweenManager tweenManager;
	final ColorVerse game;
	private Stage stage;
	private Table table;
	private Skin skin;
	private OrthographicCamera camera;
	private TextButton play;
	private TextButton exit;
	
	public MainMenuScreen(ColorVerse gam) {
		game = gam;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false,480,800);
	}
	
	
	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
		tweenManager.update(delta);
		
		batch.begin();
		//mainMenu.draw(batch);
		batch.end();
		
		
		/*if(Gdx.input.isTouched()) {
			 Vector3 touchPos = new Vector3();
			 touchPos.set(Gdx.input.getX(), Gdx.input.getY(),0);
			 
			 if(touchPos.x > 215 && touchPos.x < 550 && touchPos.y > 750 && touchPos.y < 810) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen(game));
				
			 }
			 
			 if(touchPos.x > 215 && touchPos.x < 550 && touchPos.y > 900 && touchPos.y < 1050) {
				    //dispose();
					Gdx.app.exit();
					
					
					
				 }
			
		}*/
			
		
	}

	@Override
	public void resize(int width, int height) {
			camera.viewportWidth = width / 25;
			camera.viewportHeight = height / 25;
			table.invalidateHierarchy();
	}

	@Override
	public void show() {
		
		stage = new Stage();
	    Gdx.input.setInputProcessor(stage);
		table = new Table();
		table.setFillParent(true);
		
		//skin = new Skin();
		batch = new SpriteBatch();
		tweenManager = new TweenManager();
		
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());
		
		//Texture menuTexture = new Texture("colorVerseHomeScreenTwo.png");
		//mainMenu = new Sprite(menuTexture);
		//mainMenu.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		TextButtonStyle tbs = new TextButtonStyle();
		tbs.fontColor = Color.BLACK;
		tbs.font = new BitmapFont();
		play = new TextButton("Play", tbs);
		exit = new TextButton("Exit", tbs);
		
		play.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				
				
				((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen(game));
				
			}
		});
		
		exit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				
				
				Gdx.app.exit();
				
			}
		});
		//atlas = new TextureAtlas("");
		table.add(play).spaceBottom(15).row();
		table.add(exit).spaceBottom(15).row();
		stage.addActor(table);
		//white = new BitmapFont(Gdx.files.internal("font/white.fnt"),false);
		
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		batch.dispose();
		game.dispose();
		mainMenu.getTexture().dispose();
		stage.dispose();
		//skin.dispose();
		
	}

}
