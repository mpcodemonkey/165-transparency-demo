package transparencyDemoEngine.controls.movement;

import graphicslib3D.Matrix3D;
import graphicslib3D.Vector3D;
import sage.input.action.AbstractInputAction;
import transparencyDemoGame.avatar.Avatar;

public class MoveYAxis extends AbstractInputAction 
{ 
 private Avatar aang; 
 public MoveYAxis(Avatar p2, float s) 
 { aang = p2; 
 setSpeed(s);
 } 
 public void performAction(float time, net.java.games.input.Event e ) 
 { 
		Matrix3D rot = aang.getLocalRotation(); 
		 Vector3D dir = new Vector3D(0,0,1);
		 dir = dir.mult(rot); 
		 dir.scale((double)((e.getValue() * getSpeed() * time))); 
		 aang.translate((float)dir.getX(),(float)dir.getY(),(float)dir.getZ());
	 /*
 Vector3D newLocVector = new Vector3D(); 
 Vector3D viewDir = camera.getViewDirection().normalize(); 
 Vector3D curLocVector = new Vector3D(camera.getLocation()); 
 if (e.getValue() < -0.2) 
 { newLocVector = curLocVector.add(viewDir.mult(Math.abs(e.getValue()/25) * time)); } 
 else { if (e.getValue() > 0.2) 
 { newLocVector = curLocVector.minus(viewDir.mult(Math.abs(e.getValue()/25) * time)); } 
 else { newLocVector = curLocVector; } 
 } 
 //create a point for the new location 
 double newX = newLocVector.getX(); 
 double newY = newLocVector.getY(); 
 double newZ = newLocVector.getZ(); 
 Point3D newLoc = new Point3D(newX, newY, newZ); 
 camera.setLocation(newLoc); */
} }