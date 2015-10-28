package transparencyDemoGame.treasures;

import graphicslib3D.Matrix3D;

import java.awt.Color;
import java.util.Random;

import sage.scene.shape.Cylinder;

public class CoinTreasure extends Cylinder implements ITreasure
{
	
	private float scaleFactor;
	private boolean isRare = false;
	private float basePointValue = 10;
	private int pointsWorth;
	private boolean isCollected;

	public CoinTreasure()
	{
		super(1, 2, 20, 4);
		super.setSolid(true);
		super.setColor(Color.YELLOW);
		//super.scale(.5f, .5f, .5f);
		//super.updateLocalBound();
		buildObject();
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
		else if(f < .95)
		{
			scaleFactor = 1;
		}
		else
		{
			scaleFactor = .125f;
		}
		//assign treasure point value based on the size of the treasure(inversely proportional)
		float pointFactor = (basePointValue * (1/scaleFactor)) ;
		pointsWorth = pointFactor < 50 ? (int)pointFactor : 50;
		Matrix3D scaleFail = new Matrix3D();
		scaleFail.scale(scaleFactor, scaleFactor, scaleFactor);
		
		this.getLocalScale().concatenate(scaleFail);
		//this.updateLocalBound();
	}

	@Override
	public int getPoints() {
		return pointsWorth;
	}

	@Override
	public boolean getRarity() {
		return isRare;
	}

	@Override
	public void setCollected(boolean b) 
	{
		isCollected = b;
		
	}

	@Override
	public boolean getCollected() {
		return isCollected;
	}
	@Override
	public void setLocalTranslation(Matrix3D localTranslation) {
		super.setLocalTranslation(localTranslation);
	}
	@Override
	public float getScaleFactor() {
		// TODO Auto-generated method stub
		return this.scaleFactor;
	}

}
