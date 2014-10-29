package com.colorverse.game;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {

	final Drop game;
	
	OrthographicCamera camera;
	
	
	Texture circleImage;
	
	//Music rainMusic;
	CircleShape circle;
	BodyDef bodyDef;
	Body body;
	FixtureDef fixtureDef;
	Fixture fixture;
	//Circle circle;
	
	World world;
	Box2DDebugRenderer dbRender;
	
	
	
	//create vector up here to avoid lots of garbage collection
	Vector3 touchPos;

	private int dropsGathered;
	
	public GameScreen(final Drop gam) {
		this.game = gam;
		game.batch = new SpriteBatch();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false,480,800);
		
		world = new World(new Vector2(0,0),true);
		dbRender = new Box2DDebugRenderer();
		
		bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(240,20);
		bodyDef.linearVelocity.set(new Vector2(0,25));
		
		
		body = world.createBody(bodyDef);
		
		circle = new CircleShape();
		circle.setRadius(26f);
		
		
		
		fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 0.5f;
		fixtureDef.friction = 0.0f;
		fixtureDef.restitution = 0.6f;
		
		
		fixture = body.createFixture(fixtureDef);
		
		
		
		
		//circle setup
	/*	circle = new Circle();
		circle.x = 480 / 2 - 23 / 2;
		circle.y = 20;
		circle.radius = 23;*/
		
		
		
		
	
		
		//load images for circle
		circleImage = new Texture(Gdx.files.internal("circleTest.png"));
		
		
		//load audio sounds
		
	//	rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
		
		// start the playback of the background music immediately
		//rainMusic.setLooping(true);
		
	}

	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		
	//	game.batch.draw(circleImage, circle.x, circle.y);
		
		game.batch.end();
		
		dbRender.render(world, camera.combined);
		
		world.step(1/45f, 6, 2);
		
		
		
		
		//bucket stuff
		//if(Gdx.input.isTouched()) {
		//	touchPos = new Vector3();
		//	touchPos.set(Gdx.input.getX(), Gdx.input.getY(),0);
		//	camera.unproject(touchPos);
		//	bucket.x = touchPos.x - 64 / 2;
		//}
		
		//if(Gdx.input.isKeyPressed(Keys.LEFT)) bucket.x -= 200 * Gdx.graphics.getDeltaTime();
		//if(Gdx.input.isKeyPressed(Keys.RIGHT)) bucket.x += 200 * Gdx.graphics.getDeltaTime();
		
		//if(bucket.x < 0) bucket.x = 0;
		//if(bucket.x > 800 - 64) bucket.x = 800 - 64;
		
		//raindrop stuff
		//if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();
		
	/*	Iterator<Rectangle> iter = raindrops.iterator();
		while(iter.hasNext()) {
			Rectangle raindrop = iter.next();
			raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
			if(raindrop.y + 64 < 0) iter.remove();
			if(raindrop.overlaps(bucket)) {
				dropSound.play();
				iter.remove();
				dropsGathered++;
			}*/
		//}
	}
	
	/*private void spawnRaindrop() {
		Rectangle raindrop = new Rectangle();
		raindrop.x = MathUtils.random(0, 800-64);
		raindrop.y = 480;
		raindrop.width = 64;
		raindrop.height = 64;
		raindrops.add(raindrop);
		lastDropTime = TimeUtils.nanoTime();
	}
	*/
	
	@Override
	public void pause() {
		
	}

	
	public void dispose() {
		circle.dispose();
		//rainMusic.dispose();
		
	}





	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void show() {
		//rainMusic.play();
		
	}


	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}


