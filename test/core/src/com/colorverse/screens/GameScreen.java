package com.colorverse.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.colorverse.game.ColorVerse;

import aurelienribon.tweenengine.TweenManager;


public class GameScreen implements Screen {
    
	private SpriteBatch batch;
	private Sprite gameScreen;
	private TweenManager tweenManager;
	static final float WORLD_TO_BOX = 0.1f;
	static final float BOX_TO_WORLD = 10f;
	
	World world;
	Box2DDebugRenderer debugRenderer;
	Texture circleImage;
	BodyDef circleBodyDef;
	BodyDef groundBodyDef;
	BodyDef ceilingBodyDef;
	BodyDef leftWallDef;
	BodyDef rightWallDef;
    BodyDef lineDef;
	Body leftBody;
	Body rightBody;
	Body groundBody;
	Body ceilingBody;
	Body circleBody;
	PolygonShape leftBox;
	PolygonShape rightBox;
	PolygonShape groundBox;
	PolygonShape ceilingBox;
    PolygonShape lineBox;
	CircleShape circle2;
	FixtureDef circleFixtureDef;
	Fixture fixture;
	
	private Array<Body> tempBodies = new Array<Body>();
	private Sprite circleSprite,groundSprite,ceilingSprite,rightSprite,leftSprite;
	private Vector3 bottomLeft, bottomRight,topLeft,topRight;
    private ShapeRenderer renderer;
    private Vector3 touchPos;
	private Vector2 position;
    private Body lineBody;
	Circle circle;
	
    OrthographicCamera camera;
 

    final ColorVerse game;

	
	public GameScreen(ColorVerse gam) {
		game = gam;

		
		Gdx.input.setCatchBackKey(true);
		
		 //circleImage = new Texture(Gdx.files.internal("circleTest.png"));
		circleSprite = new Sprite(new Texture(Gdx.files.internal("C:/Users/bwhite/Desktop/ColorVerse/ColorVerse/test/libs/sounds&images/circleTest.png")));
		groundSprite = new Sprite(new Texture(Gdx.files.internal("C:/Users/bwhite/Desktop/ColorVerse/ColorVerse/test/libs/sounds&images/wall.png")));
        ceilingSprite = new Sprite(new Texture(Gdx.files.internal("C:/Users/bwhite/Desktop/ColorVerse/ColorVerse/test/libs/sounds&images/wall.png")));
        rightSprite = new Sprite(new Texture(Gdx.files.internal("C:/Users/bwhite/Desktop/ColorVerse/ColorVerse/test/libs/sounds&images/sideWall.png")));
        leftSprite = new Sprite(new Texture(Gdx.files.internal("C:/Users/bwhite/Desktop/ColorVerse/ColorVerse/test/libs/sounds&images/sideWall.png")));
		//circleSprite.setOrigin(circleSprite.getWidth() / 2, circleSprite.getHeight() / 2);
        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 800);

       
        renderer = new ShapeRenderer();
        
        // new stuff below
		
		
		world = new World(new Vector2(0,0),true);
		debugRenderer = new Box2DDebugRenderer();
		
		circleBodyDef = new BodyDef();
		circleBodyDef.type = BodyType.DynamicBody;
		circleBodyDef.position.set(200 * WORLD_TO_BOX ,60 * WORLD_TO_BOX);

		//circleBodyDef.position.set(200,600);

		circleBody = world.createBody(circleBodyDef);
		
		//circleBody.setLinearVelocity(new Vector2(3f,3f));
		//circleBody.applyLinearImpulse(new Vector2(.003f,.003f), circleBody.getPosition(), true);
		circleBody.setLinearVelocity(new Vector2(10f,30f));
		CircleShape circle = new CircleShape();
        circle.setRadius(22 * WORLD_TO_BOX);
		circleFixtureDef = new FixtureDef();
		circleFixtureDef.density = .5f;
		circleFixtureDef.restitution = 1;
		circleFixtureDef.friction = 0;
		circleFixtureDef.shape = circle;
	
		
		circleBody.createFixture(circleFixtureDef);
		circleBody.setUserData(circleSprite);
		//fixture.setUserData(circleImage);
		
		

//		BodyDef groundDef = new BodyDef();
//		FixtureDef groundFixture = new FixtureDef();
//			// GROUND
//		// body definition
//		groundDef.type = BodyType.StaticBody;
//		groundDef.position.set(0, 50 * WORLD_TO_BOX);
//
//		// ground shape
//		ChainShape groundShape = new ChainShape();
//		bottomLeft = new Vector3(0, Gdx.graphics.getHeight(), 0);
//		bottomRight = new Vector3(Gdx.graphics.getWidth(), bottomLeft.y, 0);
//		camera.unproject(bottomLeft);
//		camera.unproject(bottomRight);
//
//		groundShape.createChain(new float[] {bottomLeft.x, bottomLeft.y, bottomRight.x, bottomRight.y});
//		//groundShape.createChain( new float[] {0,0,480,0});
//
//		// fixture definition
//		groundFixture.shape = groundShape;
//		groundFixture.friction = 0f;
//		groundFixture.restitution = 0;
//
//		Body ground = world.createBody(groundDef);
//		ground.createFixture(groundFixture);
//        ground.setUserData(groundSprite);
//
//
//		BodyDef ceilingDef = new BodyDef();
//		FixtureDef ceilingFixture = new FixtureDef();
//
//		ceilingDef.type = BodyType.StaticBody;
//		ceilingDef.position.set(0,750 * WORLD_TO_BOX);
//	    //need to fix position!!!
//		ChainShape ceilingShape = new ChainShape();
//		//topLeft = new Vector3(0, Gdx.graphics.getHeight(),0);
//        topLeft = new Vector3(0,Gdx.graphics.getHeight(),0);
//		topRight = new Vector3(Gdx.graphics.getWidth(), topLeft.y, 0);
//		camera.unproject(topLeft);
//		camera.unproject(topRight);
//
//		ceilingShape.createChain(new float[] { topLeft.x, topLeft.y, topRight.x, topRight.y});
//		//ceilingShape.createChain(new float[] { 0, 750 * WORLD_TO_BOX , 480 * WORLD_TO_BOX , 750 * WORLD_TO_BOX});
//
//		ceilingFixture.shape = ceilingShape;
//		ceilingFixture.friction = 0f;
//		ceilingFixture.restitution = 0;
//
//		Body ceiling = world.createBody(ceilingDef);
//		ceiling.createFixture(ceilingFixture);
//        ceiling.setUserData(ceilingSprite);



		
		
		groundBodyDef = new BodyDef();
		groundBodyDef.position.set(new Vector2(0,10 * WORLD_TO_BOX));
		groundBody = world.createBody(groundBodyDef);
		groundBox = new PolygonShape();
		groundBox.setAsBox(camera.viewportWidth, 10.0f * WORLD_TO_BOX);
		groundBody.createFixture(groundBox,0.0f);
        groundBody.setUserData(groundSprite);
		
		ceilingBodyDef = new BodyDef();
		ceilingBodyDef.position.set(new Vector2(0,790 * WORLD_TO_BOX));
		ceilingBody = world.createBody(ceilingBodyDef);
		ceilingBox = new PolygonShape();
		ceilingBox.setAsBox(camera.viewportWidth, 10.0f * WORLD_TO_BOX);
		ceilingBody.createFixture(ceilingBox,0.0f);
        ceilingBody.setUserData(ceilingSprite);
		
		leftWallDef = new BodyDef();
		leftWallDef.position.set(new Vector2(5 * WORLD_TO_BOX,0));
		leftBody = world.createBody(leftWallDef);
		leftBox = new PolygonShape();
		leftBox.setAsBox(10.0f * WORLD_TO_BOX, camera.viewportHeight);
		leftBody.createFixture(leftBox,0.0f);
        leftBody.setUserData(leftSprite);
		
		rightWallDef = new BodyDef();
		rightWallDef.position.set(new Vector2(470 * WORLD_TO_BOX,0));
		rightBody = world.createBody(rightWallDef);
		rightBox = new PolygonShape();
		rightBox.setAsBox(10.0f * WORLD_TO_BOX, camera.viewportHeight);
		rightBody.createFixture(rightBox,0.0f);
        rightBody.setUserData(rightSprite);

        touchPos= new Vector3();
        position = new Vector2(1,1);

	}


    @Override
    public void render(float delta) {
        // clear the screen with a dark blue color. The
        // arguments to glClearColor are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        world.step(1/60f,8,3);
        
       
        
        // new body stuff
        

        // tell the camera to update its matrices.
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        //batch.setProjectionMatrix(camera.combined);


        //this is currently what can render lines
        
        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            renderer.begin(ShapeRenderer.ShapeType.Line); // shape type
            renderer.setColor(1, 0.5F, 0.5F, 1); // line's color
            renderer.line(position.x, position.y, touchPos.x, touchPos.y);  // shape's set of coordinates (x1,y1,x2,y2)
            renderer.end();

        }

        // begin a new batch and draw the bucket and
        // all drops
        game.batch.begin();
        world.getBodies(tempBodies);
        for(Body body : tempBodies) {
        	if(body.getUserData() != null && body.getUserData() instanceof Sprite) {
        		Sprite sprite = (Sprite)body.getUserData();
               // System.out.println(body.getPosition().y * BOX_TO_WORLD);
                if(body.getType() == BodyType.DynamicBody) {
                    sprite.setPosition((body.getPosition().x * BOX_TO_WORLD) - (sprite.getWidth() / 2), (body.getPosition().y * BOX_TO_WORLD) - (sprite.getHeight() / 2));
                }
                else
                {
                    sprite.setPosition(body.getPosition().x * BOX_TO_WORLD , body.getPosition().y * BOX_TO_WORLD );
                }
        		//sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);
        		sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
        		sprite.draw(game.batch);
        	}
        }

      //  game.batch.draw(circleImage, circle.x, circle.y);
       
        game.batch.end();
        
        

 
        
        if (Gdx.input.isKeyPressed(Keys.BACK)){
        	((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen(game));
        	}
     
        if (Gdx.input.isKeyPressed(Keys.BACKSPACE)){
        	((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen(game));
        	}
        debugRenderer.render(world, camera.combined);
      
        
    
       
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
      
    	
         
    	
    	
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
       circleImage.dispose();
       circle2.dispose();
       groundBox.dispose();
       ceilingBox.dispose();
       
       game.dispose();
       world.dispose();
       circleSprite.getTexture().dispose();
       
    }

}