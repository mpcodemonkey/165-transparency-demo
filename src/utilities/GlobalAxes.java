package utilities;


import graphicslib3D.Vector3D;

public class GlobalAxes 
{
private static GlobalAxes g;
Vector3D up, right, forward;
	private GlobalAxes()
	{
		up = new Vector3D(0,1,0);
		right = new Vector3D(1,0,0);
		forward = new Vector3D(0,0,1);
	}
	public static synchronized GlobalAxes getInstance()
	{
		if(g == null)
		{
			g = new GlobalAxes();
		}
		return g;
	}
	
	public Vector3D getUp()
	{
		return up;
	}
	public Vector3D getRight()
	{
		return right;
	}
	public Vector3D getForward()
	{
		return forward;
	}
	public void setForward(Vector3D mult) {
		forward = mult;
		
	}
}