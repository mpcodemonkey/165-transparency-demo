package transparencyDemoGame;

import java.awt.Color;
import java.util.ArrayList;

import sage.renderer.IRenderer;
import sage.scene.HUDObject;
import sage.scene.HUDString;

public class PlayerHUD extends HUDObject
{
	private HUDString position, time;
	private ArrayList<HUDString> ui;
	
	public PlayerHUD()
	{
		ui = new ArrayList<HUDString>();
		time = new HUDString("Time = " + 0);
		time.setLocation(0,0.05); // (0,0) [lower-left] to (1,1) 
		time.setColor(Color.GREEN);
		time.setRenderMode(sage.scene.SceneNode.RENDER_MODE.ORTHO);
		time.setCullMode(sage.scene.SceneNode.CULL_MODE.NEVER);
		
		position = new HUDString ("Position: X:0, Y:0, Z:0");
		position.setColor(Color.GREEN);
		position.setRenderMode(sage.scene.SceneNode.RENDER_MODE.ORTHO);
		position.setCullMode(sage.scene.SceneNode.CULL_MODE.NEVER);
		
		ui.add(time);
		ui.add(position);
		

	}
	
	public void updateHUD(String t, String p)
	{
		time.setText(t);
		position.setText(p);
	}
	@Override
	public void draw(IRenderer r) {
		for(HUDString h : ui)
		{
			h.draw(r);
		}
		
	}

}
