package transparencyDemoEngine.controls;

import net.java.games.input.Controller;
import net.java.games.input.Event;
import graphicslib3D.Point3D;
import graphicslib3D.Vector3D;
import sage.camera.ICamera;
import sage.camera.controllers.AbstractCameraController;
import sage.input.IInputManager;
import sage.input.action.AbstractInputAction;
import sage.input.action.IAction;
import sage.scene.SceneNode;
import sage.util.MathUtils;

public class Camera3Pcontroller extends AbstractCameraController
{ 
 private float cameraAzimuth; //rotation of camera around target Y axis 
 private float cameraElevation; //elevation of camera above target 
 private float cameraDistanceFromTarget; 
 private Point3D targetPos; // avatar’s position in the world 
 private Controller.Type type;
 private boolean rotationOnly = true;
 public Camera3Pcontroller(ICamera cam, SceneNode target, IInputManager inputMgr, String controllerName, Controller con ) 
 { 
	 super(cam, target, inputMgr, con.getName());
	 this.camera = cam; 
 this.target = target; 
 this.type = con.getType();
 worldUpVector = new Vector3D(0,1,0); 
 cameraDistanceFromTarget = 15.0f; 
 cameraAzimuth = 180; // start from BEHIND and ABOVE the target 
 cameraElevation = 15.0f; // elevation is in degrees 
 updateTarget();
 updateCameraPosition();
 camera.lookAt(this.targetPos, this.worldUpVector);
 setInputMode(inputMgr, controllerName); 
 }
 
 public Point3D getTargetPosition(){
	 return targetPos;
 }
 
 public void update(float time) 
 { 
 updateTarget(); 
 updateCameraPosition(); 
 camera.lookAt(targetPos, worldUpVector); // SAGE built-in function 
 } 
 
 public void setAzimuth(float az)
 {
	 cameraAzimuth = az;
 }

 
 private void updateTarget() 
 { 
	 targetPos = new Point3D(target.getLocalTranslation().getCol(3)); } 

 private void updateCameraPosition() 
 { 
 double theta = cameraAzimuth; 
 double phi = cameraElevation ; 
 double r = cameraDistanceFromTarget; 
 
 // calculate new camera position in Cartesian coords 
 Point3D relativePosition = MathUtils.sphericalToCartesian(theta, phi, r); 
 Point3D desiredCameraLoc = relativePosition.add(targetPos); 
 camera.setLocation(desiredCameraLoc);
 
 }

@Override
public void setInputMode(IInputManager inputMgr, String controllerName) {
	IAction orbitAction = new OrbitAndRotateAction();
	IAction keyOrbitC = new KeyOrbitAction(-1);
	IAction keyOrbitCC = new KeyOrbitAction(1);
	IAction zoom = new ZoomAction();
	IAction zoomIn = new ZoomKeyAction(1);
	IAction zoomOut = new ZoomKeyAction(-1);
	IAction setFlag = new RotateFlagAction();
	if(type.equals(Controller.Type.KEYBOARD))
	{	
		inputMgr.associateAction(controllerName, net.java.games.input.Component.Identifier.Key.NUMPAD4, keyOrbitC, IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
		inputMgr.associateAction(controllerName, net.java.games.input.Component.Identifier.Key.NUMPAD6, keyOrbitCC, IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
		inputMgr.associateAction(controllerName, net.java.games.input.Component.Identifier.Key.NUMPAD1, zoomIn, IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
		inputMgr.associateAction(controllerName, net.java.games.input.Component.Identifier.Key.NUMPAD3, zoomOut, IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
		inputMgr.associateAction(controllerName, net.java.games.input.Component.Identifier.Key.NUMPAD5, setFlag, IInputManager.INPUT_ACTION_TYPE.ON_PRESS_ONLY);
	}
	else if(type.equals(Controller.Type.GAMEPAD))
	{
		inputMgr.associateAction(controllerName, net.java.games.input.Component.Identifier.Axis.RY, zoom, IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
		inputMgr.associateAction(controllerName, net.java.games.input.Component.Identifier.Axis.RX, orbitAction, IInputManager.INPUT_ACTION_TYPE.REPEAT_WHILE_DOWN);
		inputMgr.associateAction(controllerName, net.java.games.input.Component.Identifier.Button._9, setFlag, IInputManager.INPUT_ACTION_TYPE.ON_PRESS_ONLY);
	}
}

private class OrbitAndRotateAction extends AbstractInputAction 
{ 

@Override
public void performAction(float time, Event evt) {
	float rotAmount; 
	if (evt.getValue() < -0.2) { rotAmount=0.5f * evt.getValue() * time * .1f; } 
	else { if (evt.getValue() > 0.2) { rotAmount=0.5f * evt.getValue() * time * .1f; } 
	else { rotAmount=0.0f; } 
	} 
	cameraAzimuth += rotAmount ; 
	cameraAzimuth = cameraAzimuth % 360 ;
	
	if(!rotationOnly)
	{
		//target.getLocalRotation().setToIdentity();
		target.rotate(rotAmount, worldUpVector);
	}
	
} }

private class KeyOrbitAction extends AbstractInputAction 
{ 
	int dir;
	public KeyOrbitAction(int direction)
	{
		dir = direction;
	}
@Override
public void performAction(float time, Event evt) {
	float rotAmount; 
	rotAmount = 0.5f * dir * time * .1f; 
	cameraAzimuth += rotAmount ; 
	cameraAzimuth = cameraAzimuth % 360 ;
	
	if(!rotationOnly)
	{
		//target.getLocalRotation().setToIdentity();
		target.rotate(rotAmount, worldUpVector);
	}
	
} }

private class ZoomAction extends AbstractInputAction 
{ 

@Override
public void performAction(float time, Event evt) {
	if (evt.getValue() < -0.2) 
	{ 
		cameraDistanceFromTarget = cameraDistanceFromTarget  + 0.5f * evt.getValue() < 2? 2 : cameraDistanceFromTarget  + 0.5f * evt.getValue() ; 
	} 
	else { if (evt.getValue() > 0.2) { cameraDistanceFromTarget+=0.5f * evt.getValue(); } 
	} 
	
} }

private class ZoomKeyAction extends AbstractInputAction 
{
	int dir;
	public ZoomKeyAction(int direction)
	{
		dir = direction;
	}

@Override
public void performAction(float time, Event evt) {

		cameraDistanceFromTarget = cameraDistanceFromTarget  + 0.5f * dir < 2? 2 : cameraDistanceFromTarget  + 0.5f * dir ;  
	
} }

private class RotateFlagAction extends AbstractInputAction 
{ 

@Override
public void performAction(float time, Event evt) {
rotationOnly = rotationOnly == true ? false : true;
	
} }

} 

