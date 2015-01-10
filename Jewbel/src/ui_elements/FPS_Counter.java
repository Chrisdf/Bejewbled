package ui_elements;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Text;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.system.Clock;
import org.jsfml.system.Time;

public class FPS_Counter implements Drawable{

	private int frameCount;

	private int elapsedTime;
	
	private Text fpsElement;

	public FPS_Counter(Clock gameClock){
		
		Font freeSans = new Font();
		try
		{
			freeSans.loadFromFile(Paths.get("FreeSans.otf"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		Time elapsedTime = gameClock.getElapsedTime();
		
	}

	public void draw(RenderTarget target, RenderStates states) {
		
		fpsElement.draw(target, states);
	}
}
