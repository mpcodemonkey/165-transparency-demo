package transparencyDemoGame.treasures;

import graphicslib3D.Matrix3D;
import graphicslib3D.Vector3D;
import sage.scene.bounding.BoundingVolume;
/**
 * 
 * @author Jon
 * Interface implemented by the Treasure absttract class. Its only use
 * is to wrap sage shape objects and make the application more polymorphically sound
 */
public interface ITreasure 
{
		//static float basePointValue = 0;
		//int pointsWorth = 0;
		//float scaleFactor = 0;
		//boolean isRare =  false, isCollected = false;
		public int getPoints();
		boolean getRarity();
		void setCollected(boolean b);
		boolean getCollected();
		void setLocalTranslation(Matrix3D localTranslation);
		void updateWorldBound();
		void updateLocalBound();
		BoundingVolume getWorldBound();
		public void rotate(float f, Vector3D vector3d);
		public float getScaleFactor();

}
