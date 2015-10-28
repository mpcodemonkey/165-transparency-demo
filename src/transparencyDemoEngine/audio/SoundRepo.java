package transparencyDemoEngine.audio;


import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SoundRepo 
{
	/*
	 * This class is a singleton sound repository that can be called from anywhere
	 * in the game. this allows for ease of pausing/playing sounds at the correct moments, and
	 * enforces single instantiation of sound objects for each game object. It also keeps game and
	 * gameworld decoupled by having a middleman handle the sound flags
	 */
	
	/*
	 * Original comment from 133 above. I decided to keep the class as is, and not worry about the flagging mechanism for
	 * each pause/sound state combination(I know it's simple, but I'm lazy). Anywho, this class is super useful for playing
	 * sound anywhere and everywhere, so I keep it around for times like these. Once we're using JOAL and 3D point sound I'll switch over
	 */
	private static SoundRepo s;
	private String path = "." + File.separator + "sounds" + File.separator;
	private boolean audible = true;
	private Map<String, Sound> boombox;
	private SoundRepo()
	{
		boombox = new HashMap<String, Sound>();
	}
	public static synchronized SoundRepo getInstance()
	{
		if(s == null)
		{
			s = new SoundRepo();
		}
		return s;
	}
	public boolean isAudible()
	{
		return audible;
	}
	public void setAudible(boolean b)
	{
		audible = b;
	}
	public void addAudioFile(String key)
	{
		Sound s = new Sound(path + key);
		boombox.put(key, s);
	}
	public Sound retrieveAudioFile(String key)
	{
		Sound s = boombox.get(key);
		return s;
	}

}
