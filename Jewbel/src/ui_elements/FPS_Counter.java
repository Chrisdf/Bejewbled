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

public class FPS_Counter implements Drawable {

	private Text fpsElement;

	private Font freeSans;
	
	private float elapsed;
	private int framesDrawn;

	public FPS_Counter() {
		elapsed = 0f;
		framesDrawn = 0;
		freeSans = new Font();
		try
		{
			freeSans.loadFromFile(Paths.get("Resources/FreeSans.otf"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		fpsElement = new Text("", freeSans, 24);
		fpsElement.setColor(Color.YELLOW);
	}
	
	public void addElapsed(float elapsed) {
		this.elapsed += elapsed;
	}

	public void calcFPS() {
		framesDrawn++;
		System.out.println(elapsed);
		if (elapsed >= 1.0f)
		{
			System.out.println(true);
			fpsElement.setString((int) (framesDrawn / elapsed) + "");
			elapsed = 0f;
			framesDrawn = 0;
		}
	}

	public void draw(RenderTarget target, RenderStates states) {

		fpsElement.draw(target, states);
	}
}
