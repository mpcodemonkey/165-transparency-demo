package transparencyDemoEngine.controls;

import graphicslib3D.Matrix3D;
import graphicslib3D.Point3D;
import graphicslib3D.Vector3D;
import net.java.games.input.Event;
import sage.camera.ICamera;
import sage.input.action.AbstractInputAction;

public class RollAction extends AbstractInputAction 
{ 
	private ICamera camera;
	private float rot; 
 
	public RollAction(ICamera c, float degrees) 
	{
		camera = c;
		rot = degrees;

	}	 
 
	public void performAction(float time, Event e) 
	{ 
 
		 Matrix3D rotationAmt = new Matrix3D(); 
		 Vector3D vd = camera.getViewDirection(); 
		 Vector3D ud = camera.getUpAxis(); 
		 Vector3D rd = camera.getRightAxis(); 
		  
		 //Vector3D globalView = new Vector3D(1,0,0);
		 rotationAmt.rotate(rot,vd); 
		 

		 rd = rd.mult(rotationAmt); 
		 ud = ud.mult(rotationAmt); 
		 camera.setUpAxis(ud.normalize()); 
		 camera.setRightAxis(rd.normalize()); 
	} 
} 
