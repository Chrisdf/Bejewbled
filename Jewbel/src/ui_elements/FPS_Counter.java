package ui_elements;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Text;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.system.Clock;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;

public class FPS_Counter implements Drawable{

	private Clock fpsClock;
	
	private int frameCount;

	private float elapsedTime;
	
	private Text fpsElement;
	
	private Font freeSans;

	public FPS_Counter(Clock gameClock){
		
		fpsClock = gameClock;
		
		freeSans = new Font();
		try
		{
			freeSans.loadFromFile(Paths.get("Resources/FreeSans.otf"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		fpsElement = new Text(frameCount + "", freeSans, 24);
		fpsElement.setColor(Color.YELLOW);
	}
	
	public void calcFPS(){
		
		frameCount++;
		elapsedTime = fpsClock.getElapsedTime().asSeconds();
		
		if(elapsedTime >= 1){
			fpsElement = new Text((int)(frameCount / elapsedTime) + "", freeSans, 24);
			fpsElement.setColor(Color.YELLOW);
			fpsClock.restart();
			frameCount = 0;
		}
	}

	public void draw(RenderTarget target, RenderStates states) {
		
		fpsElement.draw(target, states);
	}
}
