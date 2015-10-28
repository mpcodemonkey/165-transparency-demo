package transparencyDemoEngine.controls.groupControllers;

import graphicslib3D.Matrix3D;
import graphicslib3D.Vector3D;
import sage.scene.Controller;
import sage.scene.SceneNode;

public class TranslationController extends Controller 
{ 
	private float maxTranslationValue;
	private float currentVal;
	private float incVal;
	
	public TranslationController()
	{
		maxTranslationValue = 360;
		currentVal = 0;
		incVal = .0001f;
	}
	
public void update(double time) 
{ 
	if (Math.abs(currentVal) >= maxTranslationValue) 
	{
	      currentVal = currentVal % maxTranslationValue;
	}

	currentVal += incVal;
for (SceneNode node : controlledNodes) 
{
	   Matrix3D currentPosMatrix = node.getLocalTranslation();
	      
	   Vector3D location = new Vector3D();
	   location.setW(1);
	   location.setX(location.getX() + 50*( Math.cos(currentVal) * Math.sin(currentVal)));
	   location.setY(location.getY() + 5*Math.sin(currentVal) + 3);
	   location.setZ(location.getZ() + 5*Math.sin(currentVal) );
	   
	   currentPosMatrix.setCol(3, location);
	   node.setLocalTranslation(currentPosMatrix);
} 
} 
} 
