package transparencyDemoEngine.controls.movement;

import graphicslib3D.Matrix3D;
import graphicslib3D.Vector3D;
import sage.camera.ICamera;
import sage.input.action.AbstractInputAction;

public class MoveRXAxis extends AbstractInputAction 
{ 
 private ICamera camera;
 private float rAmt;
 public MoveRXAxis(ICamera c) 
 { camera = c;
   rAmt = 0.25f;
 } 
 public void performAction(float time, net.java.games.input.Event e) 
 { 
	 if (e.getValue() < -0.2) 
	 { 
		 yaw(Math.abs(e.getValue()* time/10));
	 } 
	 else 
	 { 
		 if (e.getValue() > 0.2) 
		 {
			yaw(-e.getValue()* time/10); 
		 }  
	 } 
 }
private void yaw(float rot) 
{
	 Matrix3D rotationAmt = new Matrix3D(); 
	 Vector3D vd = camera.getViewDirection(); 
	 Vector3D ud = camera.getUpAxis(); 
	 Vector3D rd = camera.getRightAxis(); 
	  
	 Vector3D globalUp = new Vector3D(0,1,0);
	 rotationAmt.rotate(rot,globalUp); 
	 
	 ud= ud.mult(rotationAmt);
	 vd = vd.mult(rotationAmt); 
	 rd = rd.mult(rotationAmt); 
	 camera.setRightAxis(rd.normalize()); 
	 camera.setViewDirection(vd.normalize());
	 camera.setUpAxis(ud.normalize());
}
}