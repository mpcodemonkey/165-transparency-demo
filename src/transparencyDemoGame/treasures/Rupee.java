package transparencyDemoGame.treasures;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Rupee extends Treasure 
{ 
	private static float[] vrts = new float[] 
	{
		-1,2,-1,
		1,2,-1,
		1,2,1,
		-1,2,1,
		-1,-2,-1,
		1,-2,-1,
		1,-2,1,
		-1,-2,1,
		
		0,3.5f,0,
		0,-3.5f,0
	}; 
	
	//private static float[] vrts = new float[] {0,1,0,-1,-1,1,1,-1,1,1,-1,-1,-1,-1,-1}; 
	private static float[] cl = new float[] {.5f,0,0,1,.5f,0,0,1,.5f,0,0,1,.5f,0,0,1,.5f,0,0,1,.5f,0,0,1,.5f,0,0,1,.5f,0,0,1,.5f,0,0,1,.5f,0,0,1,};
	private static float[] altcl = new float[]{0f,1f,1f,1f,0f,1f,1f,1f,0f,1f,1f,1f,0f,1f,1f,1f,0f,1f,1f,1f,0f,1f,1f,1f,0f,1f,1f,1f,0f,1f,1f,1f,0f,1f,1f,1f,0f,1f,1f,1f};
	
	private static int[] triangles = new int[] 
	{
		0,1,2,0,2,3,0,3,7,0,7,4,1,0,4,1,4,5,2,1,5,2,5,6,3,2,6,3,6,7,7,6,5,7,5,4,//rectangle
		8,2,3,8,3,0,8,0,1,8,1,2,//top pyramid
		9,7,6,9,7,4,9,5,4,9,5,6//bottom pyramid
	};  
	//private static int[] triangles = new int[] {0,2,1,0,3,2,0,4,3,0,1,4,1,2,4,4,2,3};
 
	 public Rupee() 
	 {
		 super();
		 FloatBuffer vertBuf = 
		 com.jogamp.common.nio.Buffers.newDirectFloatBuffer(vrts); 
		 FloatBuffer colorBuf = 
		 com.jogamp.common.nio.Buffers.newDirectFloatBuffer(cl); 
		 FloatBuffer altColorBuf = 
		 com.jogamp.common.nio.Buffers.newDirectFloatBuffer(altcl); 
		 IntBuffer triangleBuf = 
		 com.jogamp.common.nio.Buffers.newDirectIntBuffer(triangles); 
		 this.setVertexBuffer(vertBuf);
		 
		 if(!getRarity())
		 {
			 this.setColorBuffer(colorBuf);  
		 }
		 else
		 {
			 this.setColorBuffer(altColorBuf);
		 }
		 
		 this.setIndexBuffer(triangleBuf); 
		 
		 this.scale(.5f, .5f, .5f);
	 }
	 
 }
