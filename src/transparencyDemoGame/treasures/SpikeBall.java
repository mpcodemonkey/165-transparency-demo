package transparencyDemoGame.treasures;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class SpikeBall extends Treasure 
{ 
	private static float[] vrts = new float[] 
	{
	-3,0,0,
	-1,1,1,
	-1,-1,1,
	-1,-1,-1,
	-1,1,-1,
	
	0,3,0,
	1,1,1,
	-1,1,1,
	-1,1,-1,
	1,1,-1,
	
	3,0,0,
	1,-1,1,
	1,1,1,
	1,1,-1,
	1,-1,-1,
	
	0,-3,0,
	-1,-1,1,
	1,-1,1,
	1,-1,-1,
	-1,-1,-1,
	
	0,0,3,
	-1,1,1,
	1,1,1,
	1,-1,1,
	-1,-1,1,
	
	0,0,-3,
	1,1,-1,
	-1,1,-1,
	-1,-1,-1,
	1,-1,-1
	}; 
	
//private static float[] vrts = new float[] {0,1,0,-1,-1,1,1,-1,1,1,-1,-1,-1,-1,-1}; 
private static float[] cl = new float[] {0,.4f,.7f,1,0,.7f,.4f,1,0,.4f,.7f,1,0,.7f,.4f,1,0,.4f,.7f,1,    
										0,.7f,.4f,1,0,.4f,.7f,1,0,.7f,.4f,1,0,.4f,.7f,1,0,.7f,.4f,1, 
										0,.4f,.7f,1,0,.7f,.4f,1,0,.4f,.7f,1,0,.7f,.4f,1,0,.4f,.7f,1, 
										0,.7f,.4f,1,0,.4f,.7f,1,0,.7f,.4f,1,0,.4f,.7f,1,0,.7f,.4f,1, 
										0,.4f,.7f,1,0,.7f,.4f,1,0,.4f,.7f,1,0,.7f,.4f,1,0,.4f,.7f,1,
										0,.4f,.7f,1,0,.7f,.4f,1,0,.4f,.7f,1,0,.7f,.4f,1,0,.4f,.7f,1,};

private static float[] altcl = new float[] {.8f,0,.8f,1,.8f,0,.8f,1,.8f,0,.8f,1,.8f,0,.8f,1,.8f,0,.8f,1,    
	.8f,0,.8f,1,.8f,0,.8f,1,.8f,0,.8f,1,.8f,0,.8f,1,.8f,0,.8f,1, 
	.8f,0,.8f,1,.8f,0,.8f,1,.8f,0,.8f,1,.8f,0,.8f,1,.8f,0,.8f,1,
	.8f,0,.8f,1,.8f,0,.8f,1,.8f,0,.8f,1,.8f,0,.8f,1,.8f,0,.8f,1,
	.8f,0,.8f,1,.8f,0,.8f,1,.8f,0,.8f,1,.8f,0,.8f,1,.8f,0,.8f,1,
	.8f,0,.8f,1,.8f,0,.8f,1,.8f,0,.8f,1,.8f,0,.8f,1,.8f,0,.8f,1,};


private static int[] triangles = new int[] {0,1,2,0,2,3,0,3,4,0,4,1,1,4,2,4,3,2,     
											5,7,6,5,8,7,5,9,8,5,6,9,6,7,9,9,7,8,
											10,12,11,10,13,12,10,14,13,10,11,14,11,12,14,14,12,13,
											15,17,16,15,18,17,15,19,18,15,16,19,16,17,19,19,17,18,
											20,22,21,20,23,22,20,24,23,20,21,24,21,22,24,24,22,23,
											25,27,26,25,28,27,25,29,28,25,26,29,26,27,29,29,27,28};  
//private static int[] triangles = new int[] {0,2,1,0,3,2,0,4,3,0,1,4,1,2,4,4,2,3};
 
 public SpikeBall() 
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
	 this.scale(.75f, .75f, .75f);
} 
 }
