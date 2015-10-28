package transparencyDemoEngine.events;

import sage.input.action.AbstractInputAction;
import transparencyDemoGame.avatar.Avatar;

public class JumpAction extends AbstractInputAction 
{
	Avatar korra;

	public JumpAction(Avatar aang) 
	{ 
		korra = aang;
	} 
	public void performAction(float time, net.java.games.input.Event e) 
	{ 
		korra.jump();
	}
}
