package transparencyDemoEngine.controls.movement;

import graphicslib3D.Matrix3D;
import graphicslib3D.Vector3D;
import sage.input.action.AbstractInputAction;
import transparencyDemoGame.avatar.Avatar;

public class MoveXAxis extends AbstractInputAction 
{ 
 private Avatar aang;  
 public MoveXAxis(Avatar p2, float s) 
 { aang = p2; 
 setSpeed(s);
 } 
 public void performAction(float time, net.java.games.input.Event e) 
 { 
		Matrix3D rot = aang.getLocalRotation(); 
		 Vector3D dir = new Vector3D(1,0,0); 
		 dir = dir.mult(rot); 
		 dir.scale((double)(e.getValue() * getSpeed() * time)); 
		 aang.translate((float)dir.getX(),(float)dir.getY(),(float)dir.getZ());
		 
 /*Vector3D newLocVector = new Vector3D(); 
 Vector3D viewDir = GlobalAxes.getInstance().getRight();
 Vector3D curLocVector = aang.getLocalTranslation().getCol(3);
 if (e.getValue() < -0.2) 
 { newLocVector = curLocVector.minus(viewDir.mult(Math.abs(e.getValue()/50) * time)); } 
 else { if (e.getValue() > 0.2) 
 { newLocVector = curLocVector.add(viewDir.mult(Math.abs(e.getValue()/50) * time)); } 
 else { newLocVector = curLocVector; } 
 } 
 //create a point for the new location 
aang.setLocation(newLocVector);*/
} }