package transparencyDemoGame;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.ResourceBundle.Control;

import javax.swing.JOptionPane;

import net.java.games.input.Controller;
import graphicslib3D.Matrix3D;
import graphicslib3D.Point3D;
import graphicslib3D.Vector3D;
import sage.app.BaseGame;
import sage.renderer.IRenderer;
import sage.scene.Group;
import sage.scene.HUDString;
import sage.scene.SceneNode;
import sage.scene.SceneNode.CULL_MODE;
import sage.scene.SceneNode.RENDER_MODE;
import sage.scene.SkyBox;
import sage.scene.SkyBox.Face;
import sage.display.*;
import sage.input.*;
import sage.input.action.*;
import sage.event.EventManager;
import sage.event.IEventManager;
import sage.scene.shape.Cube;
import sage.scene.shape.Line;
import sage.scene.shape.Pyramid;
import sage.scene.shape.Rectangle;
import sage.scene.state.BlendState;
import sage.scene.state.RenderState;
import sage.scene.state.RenderState.RenderStateType;
import sage.texture.Texture;
import sage.texture.TextureManager;
import transparencyDemoEngine.audio.SoundRepo;
import transparencyDemoEngine.controls.Camera3Pcontroller;
import transparencyDemoEngine.controls.YawAction;
import transparencyDemoEngine.controls.controller.ControllerManager;
import transparencyDemoEngine.controls.groupControllers.RotationController;
import transparencyDemoEngine.controls.groupControllers.TranslationController;
import transparencyDemoEngine.controls.movement.MoveXAxis;
import transparencyDemoEngine.controls.movement.MoveYAxis;
import transparencyDemoEngine.controls.movement.XMoveAction;
import transparencyDemoEngine.controls.movement.YMoveAction;
import transparencyDemoEngine.events.AcquireTreasureEvent;
import transparencyDemoEngine.events.JumpAction;
import transparencyDemoEngine.events.SpaceEvent;
import transparencyDemoGame.avatar.Avatar;
import transparencyDemoGame.treasures.CoinTreasure;
import transparencyDemoGame.treasures.ITreasure;
import transparencyDemoGame.treasures.Rupee;
import transparencyDemoGame.treasures.SpikeBall;
import transparencyDemoGame.treasures.TreasureLoader;
import utilities.MyDisplaySystem;
import utilities.GlobalAxes;
import sage.camera.*;

public class TransparencyDemo extends BaseGame {
	IDisplaySystem display;
	ICamera cameraP1;
	private int scoreP1;
	private float time = 0; // game elapsed time
	private int treasureCountP1 = 0, treasureCountP2 = 0;
	IEventManager eventMgr;
	// Camera3Pcontroller cc;
	// ArrayList<Treasure> hoard = new ArrayList<Treasure>();
	private String bg, hit, otherhit, goodScore, badScore;
	IRenderer renderer;
	private SkyBox s, s1;
	// set a time for the game to end
	private  int endTime = 500, spawntimer = 0;
	private RotationController treasureRotationController;
	private TranslationController treasureTranslationController;
	protected Group rotGroup;
	protected Group tranGroup;
	private Avatar p1;
	private ArrayList<Controller> controls;
	private ArrayList<Avatar> numOfPlayers;
	private Group sun, root;
	private BlendState bl;

	private Camera3Pcontroller cam1Con;
	private PlayerHUD hud1;

	protected void initSystem() {
		String info = "This is a demo of the current implementation of transparency\n"
				+ "in SAGE. It is based off of a a student assignment and should therefore\n"
				+ "be used for demonstration purposes only. Application documentation is\n"
				+ "currently sparse, but should be updated as the contributors have more\n"
				+ "time.";
		JOptionPane.showMessageDialog(null, info, "Welcome!", 1);
		try {
			initControllers();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createDisplaySystem();
		display.setTitle("Transparency Demo");
		setDisplaySystem(display); 
		createInputManager();
		setGameWorld(new ArrayList<SceneNode>());
	}

	private void initControllers() throws InterruptedException {
		controls = new ArrayList<Controller>();
		// display controls dialog
		ControllerManager wind = new ControllerManager(controls);
		wind.setVisible(true);

		System.out.print("Waiting for user to choose controllers");
		// fix later, works for now
		while (wind.isShowing()) {
			System.out.print(".");
			Thread.sleep(1000);
		}
		;// busy loop until controls are set

	}

	protected void initGame() {

		// initialize game world objects

		initGameObjects();

		createPlayers();

		// create audio file repo
		initAudio();
		initInput();
		
		// start the music
		SoundRepo.getInstance().retrieveAudioFile(bg).loop();
		super.update(0.0f);
	}

	private void initInput() {

		IInputManager im = getInputManager();

		// cam1Con = new ThirdPersonCameraController(cameraP1,p1,im, kbName);
		// cam2Con = new ThirdPersonCameraController(cameraP2,p2,im, gpName);
		// create IAction objects to use in mapping controls
		IAction quitGame = new QuitGameAction(this);

		for (int i = 0; i < numOfPlayers.size(); i++) {
			Avatar currentPlayer = numOfPlayers.get(i);
			if (controls.get(i).getType().equals(Controller.Type.GAMEPAD)) {
				IAction yAxisMove = new MoveYAxis(currentPlayer, 0.025f);
				IAction xAxisMove = new MoveXAxis(currentPlayer, 0.025f);
				IAction jump = new JumpAction(currentPlayer);
				IAction menu = new SpaceEvent(this);
				System.out.println(controls.get(i).getName());
				System.out.println(i);
				im.associateAction(controls.get(i).getName(),
						net.java.games.input.Component.Identifier.Axis.Y,
						yAxisMove,
						IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
				im.associateAction(controls.get(i).getName(),
						net.java.games.input.Component.Identifier.Axis.X,
						xAxisMove,
						IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
				im.associateAction(controls.get(i).getName(),
						net.java.games.input.Component.Identifier.Button._0,
						jump, IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
			} else if (controls.get(i).getType()
					.equals(Controller.Type.KEYBOARD)) {
				IAction forward = new YMoveAction(0.025f,
						GlobalAxes.getInstance(), numOfPlayers.get(i));
				IAction backward = new YMoveAction(-0.025f,
						GlobalAxes.getInstance(), numOfPlayers.get(i));
				IAction left = new XMoveAction(currentPlayer, -0.025f);
				IAction right = new XMoveAction(currentPlayer, 0.025f);
				IAction yawLeft = new YawAction(currentPlayer, .2f,
						GlobalAxes.getInstance());
				IAction yawRight = new YawAction(currentPlayer, -.2f,
						GlobalAxes.getInstance());
				IAction jump = new JumpAction(currentPlayer);
				IAction menu = new SpaceEvent(this);
				
				im.associateAction(controls.get(i).getName(),
						net.java.games.input.Component.Identifier.Key.W,
						forward,
						IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
				im.associateAction(controls.get(i).getName(),
						net.java.games.input.Component.Identifier.Key.D, right,
						IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
				im.associateAction(controls.get(i).getName(),
						net.java.games.input.Component.Identifier.Key.S,
						backward,
						IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
				im.associateAction(controls.get(i).getName(),
						net.java.games.input.Component.Identifier.Key.A, left,
						IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
				im.associateAction(controls.get(i).getName(),
						net.java.games.input.Component.Identifier.Key.ESCAPE,
						quitGame,
						IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
				im.associateAction(controls.get(i).getName(),
						net.java.games.input.Component.Identifier.Key.J, jump,
						IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
				im.associateAction(controls.get(i).getName(),
						net.java.games.input.Component.Identifier.Key.SPACE, menu,
						IInputManager.INPUT_ACTION_TYPE.ON_PRESS_ONLY);
			}
		}

		cam1Con = new Camera3Pcontroller(cameraP1, numOfPlayers.get(0), im,
				controls.get(0).getName(), controls.get(0));
		cam1Con.setAzimuth(0);
		renderer.setCamera(cameraP1);

	}

	private void createPlayers() {
		numOfPlayers = new ArrayList<Avatar>();
		p1 = new Avatar();
		p1.translate(10, 1, 95);
		// p1.rotate(180, new Vector3D(0,1,0));
		root.addChild(p1);
		this.addGameWorldObject(root);
		//this.addGameWorldObject(p1);
		p1.updateLocalBound();
		p1.updateWorldBound();
		numOfPlayers.add(p1);

		createPlayerHUDs();
	}

	private void createPlayerHUDs() {
		// Add a HUD
		hud1 = new PlayerHUD();
		cameraP1.addToHUD(hud1);
	}

	private void initAudio() {
		bg = "bg.wav";
		hit = "hit.wav";
		otherhit = "otherhit.wav";
		goodScore = "winning.wav";
		badScore = "yousuck.wav";

		SoundRepo.getInstance().addAudioFile(bg);
		SoundRepo.getInstance().addAudioFile(hit);
		SoundRepo.getInstance().addAudioFile(otherhit);
		SoundRepo.getInstance().addAudioFile(goodScore);
		SoundRepo.getInstance().addAudioFile(badScore);

	}

	private void initGameObjects() {
		renderer = display.getRenderer();

		cameraP1 = new JOGLCamera(renderer);
		cameraP1.setPerspectiveFrustum(45, .5, 0.01, 1000);
		cameraP1.setViewport(0.0, 1.0, 0.0, 1.0);
		cameraP1.setLocation(new Point3D(10, 10, 100));

		// Build a skybox
		s = new SkyBox();
		Texture tex = TextureManager.loadTexture2D("." + File.separator + "tex"
				+ File.separator + "sky.jpg");
		s.setTexture(Face.South, tex);
		s.setTexture(Face.North, tex);
		s.setTexture(Face.East, tex);
		s.setTexture(Face.West, tex);
		s.setTexture(Face.Up, tex);
		s.setTexture(Face.Down, tex);
		// s.scale(100, 100, 100);
		s.translate((float) cameraP1.getLocation().getX(), (float) cameraP1
				.getLocation().getY(), (float) cameraP1.getLocation().getZ());
		this.addGameWorldObject(s);

		// build a ground plane
		Rectangle rec = new Rectangle();
		rec.setTexture(TextureManager.loadTexture2D("." + File.separator
				+ "tex" + File.separator + "floor.jpg"));
		rec.translate(50, 0, 50);
		rec.rotate(90, new Vector3D(1, 0, 0));
		rec.scale(100, 100, 100);
		this.addGameWorldObject(rec);

		// create transparency group displaying different object hierarchies
		createTransparencyGroup();

		// create world axes
		Point3D origin = new Point3D(0, 0, 0);
		Point3D xEnd = new Point3D(100, 0, 0);
		Point3D yEnd = new Point3D(0, 100, 0);
		Point3D zEnd = new Point3D(0, 0, 100);
		Line xAxis = new Line(origin, xEnd, Color.red, 2);
		Line yAxis = new Line(origin, yEnd, Color.green, 2);
		Line zAxis = new Line(origin, zEnd, Color.blue, 2);
		addGameWorldObject(xAxis);
		addGameWorldObject(yAxis);
		addGameWorldObject(zAxis);

	}

	private void createTransparencyGroup() {
		
		bl = createBlendState();
		
		CoinTreasure center = new CoinTreasure();
		
		SpikeBall sb1 = new SpikeBall();

		
		Rupee r1 = new Rupee();
		Rupee r2 = new Rupee();
		Rupee r3 = new Rupee();
		
		TreasureLoader cube = new TreasureLoader("naga.obj");
		Texture t = TextureManager.loadTexture2D("." + File.separator + "tex" + File.separator + "mud.jpg");
		t.setApplyMode(Texture.ApplyMode.Replace);
		cube.setTexture(t);
		/*
		CoinTreasure c1 = new CoinTreasure();
		CoinTreasure c2 = new CoinTreasure();
		
		Group root = new Group("root");
		*/
		//create group hierarchy
		RotationController rc = new RotationController();
		TranslationController tr = new TranslationController();
		
		sun = new Group("root");
		sun.translate(20, 5, 20);
		
		rc.addControlledNode(sun);
		sun.addController(rc);
		
		Group planets = new Group("sub-root");
		planets.translate(10, 0, 0);
		
		Group moons = new Group("leaf");
		moons.translate(0, 10, 0);
		
		tr.addControlledNode(moons);
		moons.addController(tr);
		
		planets.addChild(moons);
		sun.addChild(planets);
		
		center.translate(5, 2, 0);
		
		sun.addChild(center);
		planets.addChild(cube);
		
		//make 3 rupees in the transparent group, one of which is solid
		r1.translate(-2, 0, 0);
		r2.translate(-4, 0, 0);
		r3.translate(6, 0, 0);
		
		
		
		planets.addChild(r1);
		planets.addChild(r2);
		planets.addChild(r3);
		
		//put a spike ball in the final group
		moons.addChild(sb1);
		
		//make a transparent coin in the sun(not transparent by default) group
		center.setRenderState(bl);
		center.setRenderMode(RENDER_MODE.TRANSPARENT);
		center.updateRenderStates();
		
		//make all planets tranparent
		planets.setRenderState(bl);
		planets.setRenderMode(RENDER_MODE.TRANSPARENT);
		planets.updateRenderStates();
		
		//make one planet opaque
		r2.clearRenderState(RenderStateType.Blend);
		
		root = new Group("root-node");
		root.addChild(sun);
		
		this.addGameWorldObject(root);
	}

	/**
	 * creates a BlendState with a default source and destination function
	 * @return a BlendState
	 */
	private BlendState createBlendState() {
		BlendState b = (BlendState)renderer.createRenderState(RenderState.RenderStateType.Blend);
		b.setBlendEnabled(true);//potentially redundant function ( setEnabled() )
		//use source alpha in comparative test function
		b.setSourceFunction(BlendState.SourceFunction.SourceAlpha);
		//use destination function in comparative test function
		b.setDestinationFunction(BlendState.DestinationFunction.DestinationAlpha);
		//enable testing for blend
		b.setTestEnabled(true);
		//blend source with dest if source alpha > dest alpha
		b.setTestFunction(BlendState.TestFunction.GreaterThan);
		//enable blend state
		b.setEnabled(true);
		
		return b;
	}

	private void createDisplaySystem() {
		DisplaySettingsDialog dispChooser = new DisplaySettingsDialog(
				GraphicsEnvironment.getLocalGraphicsEnvironment()
						.getDefaultScreenDevice());
		dispChooser.showIt();
		DisplayMode gd = dispChooser.getSelectedDisplayMode();
		display = new MyDisplaySystem(gd.getWidth(), gd.getHeight(),
				gd.getBitDepth(), gd.getRefreshRate(), dispChooser.isFullScreenModeSelected(),
				"sage.renderer.jogl.JOGLRenderer");
		System.out.print("\nWaiting for display creation...");
		int count = 0;

		// wait until display creation completes or a timeout occurs
		while (!display.isCreated()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				throw new RuntimeException("Display creation interrupted");
			}

			count++;
			System.out.print("+");
			if (count % 80 == 0) {
				System.out.println();
			}

			if (count > 2000) // 20 seconds (approx.)
			{
				throw new RuntimeException("Unable to create display");
			}
		}
		try {
			Thread.sleep(60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
	}

	protected void shutdown() {
		display.close();
		// ...other shutdown methods here as necessary...
	}


	public void update(float elapsedTimeMS) {

		cam1Con.update(elapsedTimeMS);
		updateAvatars(elapsedTimeMS);
		// rotGroup.updateGeometricState(elapsedTimeMS, true);
		// tranGroup.updateGeometricState(elapsedTimeMS, true);
		Matrix3D skLoc = new Matrix3D();

		skLoc.translate((float) cameraP1.getLocation().getX(), (float) cameraP1
				.getLocation().getY(), (float) cameraP1.getLocation().getZ());
		s.setLocalTranslation(skLoc);

		// update the HUD

		time += elapsedTimeMS;
		DecimalFormat df = new DecimalFormat("0.0");
		hud1.updateHUD("Time = " + df.format(time / 1000),
				"Position: X:" + (int)cam1Con.getTargetPosition().getX() + " Y:" + (int)cam1Con.getTargetPosition().getY() + " Z:" + (int)cam1Con.getTargetPosition().getZ());

		super.update(elapsedTimeMS);

		// check if the game has ended due to timeout
		if (time / 1000 > endTime || scoreP1 > 1500) {

			this.setGameOver(true);
			SoundRepo.getInstance().retrieveAudioFile(bg).stop();
			if (this.display.isFullScreen()) {
				((MyDisplaySystem) display).setShowing(false);
			}
			String message = String.format(
					"Game over! Your Final Scores: %n%-20s%d%n",
					"Player1:", scoreP1);
			if (scoreP1 < 1000) {
				SoundRepo.getInstance().retrieveAudioFile(badScore).play();
				message += "\nYou fail!";
			} else{
				SoundRepo.getInstance().retrieveAudioFile(goodScore).play();
				message += "\nA winner is you!";
			}
			JOptionPane.showMessageDialog(null, message, "Game Over", 1);
		}

	}

	private void updateAvatars(float elapsedTimeMS) {
		p1.update(elapsedTimeMS);
	}

	public void addGameWorldObject(SceneNode s) {
		super.addGameWorldObject(s);
	}

	public void alterBlendState(int src, int dest, int test, int blend) {
		alterSource(src);
		alterDest(dest);
		alterTest(test);
		alterBlend(blend);
		System.out.println("Source function: " + src + " Dest function: " + dest + " Test Function: " + test + " Blend Equation: " + blend);
		
		//update all object render states
		sun.updateRenderStates();
		
	}

	private void alterSource(int src) {
		switch(src){
			case 1: bl.setSourceFunction(BlendState.SourceFunction.SourceAlpha);
			break;
			
			case 2: bl.setSourceFunction(BlendState.SourceFunction.DestinationAlpha);
			break;
			
			case 3: bl.setSourceFunction(BlendState.SourceFunction.ConstantAlpha);
			break;
			
			case 4: bl.setSourceFunction(BlendState.SourceFunction.ConstantColor);
			break;
			
			case 5: bl.setSourceFunction(BlendState.SourceFunction.DestinationColor);
			break;
			
			case 6: bl.setSourceFunction(BlendState.SourceFunction.One);
			break;
			
			case 7: bl.setSourceFunction(BlendState.SourceFunction.OneMinusConstantAlpha);
			break;
			
			case 8: bl.setSourceFunction(BlendState.SourceFunction.OneMinusConstantColor);
			break;
			
			case 9: bl.setSourceFunction(BlendState.SourceFunction.OneMinusDestinationAlpha);
			break;
			
			case 10: bl.setSourceFunction(BlendState.SourceFunction.OneMinusDestinationColor);
			break;
			
			case 11: bl.setSourceFunction(BlendState.SourceFunction.OneMinusSourceAlpha);
			break;
			
			case 12: bl.setSourceFunction(BlendState.SourceFunction.SourceAlphaSaturate);
			break;
			
			case 13: bl.setSourceFunction(BlendState.SourceFunction.Zero);
			break;
			
			
		}
		
	}
	
	private void alterDest(int dest) {
		switch(dest){
		case 1: bl.setDestinationFunction(BlendState.DestinationFunction.SourceAlpha);
		break;
		
		case 2: bl.setDestinationFunction(BlendState.DestinationFunction.DestinationAlpha);
		break;
		
		case 3: bl.setDestinationFunction(BlendState.DestinationFunction.ConstantAlpha);
		break;
		
		case 4: bl.setDestinationFunction(BlendState.DestinationFunction.ConstantColor);
		break;
		
		case 5: bl.setDestinationFunction(BlendState.DestinationFunction.SourceColor);
		break;
		
		case 6: bl.setDestinationFunction(BlendState.DestinationFunction.One);
		break;
		
		case 7: bl.setDestinationFunction(BlendState.DestinationFunction.OneMinusConstantAlpha);
		break;
		
		case 8: bl.setDestinationFunction(BlendState.DestinationFunction.OneMinusConstantColor);
		break;
		
		case 9: bl.setDestinationFunction(BlendState.DestinationFunction.OneMinusDestinationAlpha);
		break;
		
		case 10: bl.setDestinationFunction(BlendState.DestinationFunction.OneMinusSourceAlpha);
		break;
		
		case 11: bl.setDestinationFunction(BlendState.DestinationFunction.OneMinusSourceColor);
		break;
		
		case 12: bl.setDestinationFunction(BlendState.DestinationFunction.Zero);
		break;
		
		}
		
	}
	
	private void alterTest(int test) {
		
		switch(test){
		case 1: bl.setTestFunction(BlendState.TestFunction.GreaterThan);
		break;
		
		case 2: bl.setTestFunction(BlendState.TestFunction.EqualTo);
		break;
		
		case 3: bl.setTestFunction(BlendState.TestFunction.GreaterThanOrEqualTo);
		break;
		
		case 4: bl.setTestFunction(BlendState.TestFunction.Always);
		break;
		
		case 5: bl.setTestFunction(BlendState.TestFunction.LessThan);
		break;
		
		case 6: bl.setTestFunction(BlendState.TestFunction.LessThanOrEqualTo);
		break;
		
		case 7: bl.setTestFunction(BlendState.TestFunction.Never);
		break;
		
		case 8: bl.setTestFunction(BlendState.TestFunction.NotEqualTo);
		break;
		
		}
	}
	
	private void alterBlend(int blend) {
		
		switch(blend){
		case 1: bl.setBlendEquation(BlendState.BlendEquation.Add);
		break;
		
		case 2: bl.setBlendEquation(BlendState.BlendEquation.Max);
		break;
		
		case 3: bl.setBlendEquation(BlendState.BlendEquation.Min);
		break;
		
		case 4: bl.setBlendEquation(BlendState.BlendEquation.Subtract);
		break;
		
		case 5: bl.setBlendEquation(BlendState.BlendEquation.ReverseSubtract);
		break;
		
		}

		
	}

}
