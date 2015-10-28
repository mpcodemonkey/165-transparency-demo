package transparencyDemoEngine.controls.movement;

import graphicslib3D.Matrix3D;
import graphicslib3D.Vector3D;
import net.java.games.input.Event;
import sage.input.action.AbstractInputAction;
import transparencyDemoGame.avatar.Avatar;

public class XMoveAction extends AbstractInputAction 
{ 
	private Avatar aang;
 
	public XMoveAction(Avatar c, float spd) 
	{
		aang = c; 
		setSpeed(spd);
	}	 
 
	public void performAction(float time, Event e) 
	{ 
 
		Matrix3D rot = aang.getLocalRotation(); 
		 Vector3D dir = new Vector3D(1,0,0);
		 dir = dir.mult(rot); 
		 dir.scale((double)(getSpeed() * time)); 
		 aang.translate((float)dir.getX(),(float)dir.getY(),(float)dir.getZ());
		/*Vector3D rightAxis = GlobalAxes.getInstance().getRight();
		Vector3D curLocVector = new Vector3D(aang.getLocation()); 
		Vector3D newLocVec = curLocVector.add(rightAxis.mult(speed * time)); 
		aang.setLocation(newLocVec);*/
	} 
} 
