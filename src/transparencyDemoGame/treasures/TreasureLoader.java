package transparencyDemoGame.treasures;

import java.io.File;
import java.nio.FloatBuffer;
import sage.model.loader.OBJLoader;
import sage.scene.TriMesh;

/**
 * 
 * @author Jon
 * A simple wrapper for the model loader that puts the needed
 * values from the loader trimesh into a trimesh object
 */
public class TreasureLoader extends TriMesh
{
	private String fileName, path;;
	public TreasureLoader(String objName)
	{
		fileName = objName;
		path = "." + File.separator + "objects" + File.separator;
		loadModel();
	}
	private void loadModel() 
	{
		OBJLoader obload = new OBJLoader();
		TriMesh tmp = obload.loadModel(path + fileName);
		

		 FloatBuffer vertBuf = tmp.getVertexBuffer();
		 //FloatBuffer colorBuf = tmp.getColorBuffer(); 
		 //IntBuffer triangleBuf = tmp.getIndexBuffer(); 
		 this.setVertexBuffer(vertBuf);
		 //this.setColorBuffer(tmp.getColorBuffer());
		 this.setTextureBuffer(tmp.getTextureBuffer());
		 this.setIndexBuffer(tmp.getIndexBuffer()); 
		 this.setNormalBuffer(tmp.getNormalBuffer());
		 this.setColorBuffer(null);

	 }
}
