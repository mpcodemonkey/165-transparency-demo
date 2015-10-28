package transparencyDemoEngine.events;

import sage.input.action.AbstractInputAction;
import transparencyDemoGame.TransparencyDemo;
import transparencyDemoGame.avatar.Avatar;
import utilities.BlendMenu;

public class SpaceEvent extends AbstractInputAction 
{
	TransparencyDemo td;
	BlendMenu bd;

	public SpaceEvent(TransparencyDemo fix) 
	{ 
		td = fix;
		bd = new BlendMenu(td);
	} 
	public void performAction(float time, net.java.games.input.Event e) 
	{ 
		if(bd.isShowing()){
			bd.setVisible(false);
		}
		else{
			bd.setVisible(true);
		}
	}
}
