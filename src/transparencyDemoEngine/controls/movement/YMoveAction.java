package transparencyDemoEngine.controls.movement;

import graphicslib3D.Matrix3D;
import graphicslib3D.Point3D;
import graphicslib3D.Vector3D;
import net.java.games.input.Event;
import sage.camera.ICamera;
import sage.input.action.AbstractInputAction;
import sage.util.Debug;
import transparencyDemoGame.avatar.Avatar;
import utilities.GlobalAxes;

public class YMoveAction extends AbstractInputAction 
{ 
	private float speed; 
	GlobalAxes glob;
	Avatar aang;
 
	public YMoveAction(float spd, GlobalAxes g, Avatar p1) 
	{
		speed = spd;
		glob = g;
		aang = p1;
	}	 
 
	public void performAction(float time, Event e) 
	{ 
		Matrix3D rot = aang.getLocalRotation(); 
		 Vector3D dir = new Vector3D(0,0,-1); 
		 dir = dir.mult(rot); 
		 dir.scale((double)(speed * time)); 
		 aang.translate((float)dir.getX(),(float)dir.getY(),(float)dir.getZ()); 
  /*
		Vector3D curLocVector = new Vector3D(camera.getLocation()); 
		Vector3D newLocVec = curLocVector.add(glob.getForward().mult(speed)); 
		Debug.log(glob.getForward().toString());
		double newX = newLocVec.getX(); 
		double newY = newLocVec.getY(); 
		double newZ = newLocVec.getZ(); 
		Point3D newLoc = new Point3D(newX, newY, newZ); 
		camera.setLocation(newLoc);  
	*/
	} 
} 
