package transparencyDemoGame.treasures;

import graphicslib3D.Matrix3D;

import java.util.Random;

import sage.scene.TriMesh;
/**
 * 
 * @author Jon
 * An abstract treasure class that can be extended to create different
 * treasure objects with varying attributes
 */
public abstract class Treasure extends TriMesh implements ITreasure
{
	private static float basePointValue = 10;
	private int pointsWorth;
	private float scaleFactor;
	private boolean isRare, isCollected;
	
	public Treasure()
	{
		buildObject();
	}
	
	/**
	 * returns the treasure point value
	 * @return pointsworth - the point value of the treasure
	 */
	public int getPoints()
	{
		return pointsWorth;
	}
	
	private void buildObject()
	{
		Random r = new Random();
		
		float f = r.nextFloat();
		
		if(f < .25)
		{
			scaleFactor = .25f;
		}
		else if(f < .5)
		{
			scaleFactor = .5f;
		}
		else if(f < .85)
		{
			scaleFactor = 1;
			//isRare = true;
		}
		else
		{
			scaleFactor = .125f;
			isRare = true;
		}
		//assign treasure point value based on the size of the treasure(inversely proportional)
		float pointFactor = (basePointValue * (1/scaleFactor)) ;
		pointsWorth = pointFactor < 50 ? (int)pointFactor : 50;
		if(isRare)
		{
			pointsWorth *= 5;
		}
		Matrix3D scaleFail = new Matrix3D();
		scaleFail.scale(scaleFactor, scaleFactor, scaleFactor);
		
		this.getLocalScale().concatenate(scaleFail);
	}
	
	/**
	 * indicates whether or not this treasure is a rare treasure object
	 * @return isRare - the rarity state
	 */
	public boolean getRarity()
	{
		return isRare;
	}
	
	/**
	 * sets the collected state of the treasure
	 * @param b - the collected state
	 */
	public void setCollected(boolean b)
	{
		isCollected = true;
	}
	
	/**
	 * gets the collected state of the treasure
	 * @return isCollected - the collected state of the treasure
	 */
	public boolean getCollected()
	{
		return isCollected;
	}
	
	public float getScaleFactor()
	{
		return scaleFactor;
	}
}
