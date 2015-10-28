package transparencyDemoEngine.controls;

import graphicslib3D.Matrix3D;
import graphicslib3D.Vector3D;
import net.java.games.input.Event;
import sage.input.action.AbstractInputAction;
import transparencyDemoGame.avatar.Avatar;
import utilities.GlobalAxes;

public class YawAction extends AbstractInputAction 
{ 
	private Avatar a;
	private float rot;
	GlobalAxes glob;
 
	public YawAction(Avatar c, float degrees, GlobalAxes globalAxes) 
	{
		a = c;
		rot = degrees;
		glob = globalAxes;

	}	 
 
	public void performAction(float time, Event e) 
	{ 
 
		 Matrix3D rotationAmt = new Matrix3D(); 
		 rotationAmt.rotate(0, rot, 0);
		 
		 a.rotate(rot, new Vector3D(0,1,0));
/*		 Vector3D vd = camera.getViewDirection(); 
		 Vector3D ud = camera.getUpAxis(); 
		 Vector3D rd = camera.getRightAxis(); 
		  
		 rotationAmt.rotate(rot,glob.getUp());
		 
		 
		 ud= ud.mult(rotationAmt);
		 vd = vd.mult(rotationAmt); 
		 rd = rd.mult(rotationAmt); 
		 glob.setForward(glob.getForward().mult(rotationAmt));
		 //glob = glob.normalize();
		 Debug.log(glob.getForward().toString());
		 camera.setRightAxis(rd.normalize()); 
		 camera.setViewDirection(vd.normalize());
		 camera.setUpAxis(ud.normalize()); 
*/
		
	} 
} 
