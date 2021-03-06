package transparencyDemoEngine.controls;

import net.java.games.input.Event;
import sage.app.AbstractGame;
import sage.input.action.AbstractInputAction;

public class QuitGameAction extends AbstractInputAction 
{
 private AbstractGame game; 
 
 public QuitGameAction(AbstractGame g) // constructor 
 { this.game = g; } 
 
 // Sets the "game over" flag in the game associated with this 
 // IAction to true. The time and event parameters are ignored. 

@Override
public void performAction(float time, Event evt)
{
	game.setGameOver(true);
}
	
} 

