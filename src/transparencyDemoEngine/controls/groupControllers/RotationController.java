package transparencyDemoEngine.controls.groupControllers;

import graphicslib3D.Matrix3D;
import graphicslib3D.Vector3D;
import sage.scene.Controller;
import sage.scene.SceneNode;

 
public class RotationController extends Controller 
{ private double rotationRate = 50 ; //degrees to rotate per second 
 private Vector3D rotationAxis = new Vector3D(0,1,0) ; //default axis 
 //...accessors here (getters & setters for rotationRate and rotationAxis) 
 //Apply this controller's rotation to each SceneNode to which it is attached. 
 public void update(double time) 
 { // compute amount to rotate based on rotationRate being degrees/second 
 // and time being millisecs 
 double rotAmount = rotationRate/1000.0 * time ; 
 //compute a new rotation transform 
 Matrix3D newRot = new Matrix3D(rotAmount, rotationAxis); 
 //add the rotation to the local transform of every controlled node 
 for (SceneNode node : controlledNodes) 
 { //get node’s current rotation 
 Matrix3D curRot = node.getLocalRotation(); 
 //add new rotation into node’s current local rotation 
 curRot.concatenate(newRot); 
 node.setLocalRotation(curRot); 
 } 
 } 
} 