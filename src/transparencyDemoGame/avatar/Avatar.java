package transparencyDemoGame.avatar;

import graphicslib3D.Matrix3D;
import graphicslib3D.Point3D;
import graphicslib3D.Vector3D;
import sage.event.IEventListener;
import sage.event.IGameEvent;
import sage.renderer.IRenderer;
import sage.scene.shape.Pyramid;

public class Avatar extends Pyramid
{
	private float baseHeight;
	private float maxHeight;
	private float currentHeight;
	private boolean isJumping;
	private boolean jumpDirection;
	
	public Avatar()
	{
		super();
		isJumping = false;
		jumpDirection = false;
		baseHeight = (float) this.getLocalTranslation().getRow(1).getW();
		currentHeight = baseHeight;
		maxHeight = 5;
	}
	
	public void update(float time)
	{

		if(isJumping)
		{
			float jumpAmt = .0025f * time;
			currentHeight = (float) this.getLocalTranslation().getRow(1).getW();
			if(jumpDirection)
			{
				if(currentHeight < maxHeight)//jumping up
				{
					currentHeight += jumpAmt;
				}
				else//at apex
				{
					currentHeight = maxHeight;
					jumpDirection = false;
				}
			}
			else
			{
				if(currentHeight > baseHeight)
				{
					if(currentHeight - jumpAmt < 1)
					{
						currentHeight = 1;
						isJumping = false;
					}
					else
					{
						currentHeight -= jumpAmt;
					}
				}
				else
				{
					currentHeight = 1;
					isJumping = false;
				}
			}
			this.getLocalTranslation().setElementAt(1, 3, currentHeight);
		}

		
	}


	public void jump()
	{
		if(!isJumping)
		{
			isJumping = true;
			jumpDirection = true;
		}
	}

	public Point3D getLocation() {
		Point3D p = new Point3D();
		p.setX(this.getLocalTranslation().getRow(0).getW());
		p.setY(this.getLocalTranslation().getRow(1).getW());
		p.setZ(this.getLocalTranslation().getRow(2).getW());
		
		return p;
	}

	public void setLocation(Vector3D newLoc) 
	{
		this.getLocalTranslation().setToIdentity();
		this.translate((float)newLoc.getX(), (float)newLoc.getY(), (float)newLoc.getZ());
		
	}
	
	
	
	
	
	
	

}
