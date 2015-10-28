package transparencyDemoEngine.controls.movement;

import graphicslib3D.Matrix3D;
import graphicslib3D.Point3D;
import graphicslib3D.Vector3D;
import sage.camera.ICamera;
import sage.input.action.AbstractInputAction;

public class MoveRYAxis extends AbstractInputAction 
{ 
 private ICamera camera;
 private float rAmt;
 public MoveRYAxis(ICamera c) 
 { camera = c;
   rAmt = 0.25f;
 } 
 public void performAction(float time, net.java.games.input.Event e) 
 { 
	 if (e.getValue() < -0.2) 
	 { 
		 pitch(time * Math.abs(e.getValue()/10));
	 } 
	 else 
	 { 
		 if (e.getValue() > 0.2) 
		 {
			pitch(time * -e.getValue()/10); 
		 }  
	 } 
 }
private void pitch(float rot) 
{
	 Matrix3D rotationAmt = new Matrix3D(); 
	 Vector3D vd = camera.getViewDirection(); 
	 Vector3D ud = camera.getUpAxis(); 
	 Vector3D rd = camera.getRightAxis(); 
	  
	 //Vector3D globalView = new Vector3D(1,0,0);
	 rotationAmt.rotate(rot,rd); 
	 

	 vd = vd.mult(rotationAmt); 
	 ud = ud.mult(rotationAmt); 
	 camera.setUpAxis(ud.normalize()); 
	 camera.setViewDirection(vd.normalize());
}
}