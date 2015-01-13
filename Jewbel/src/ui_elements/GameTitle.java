package ui_elements;

import java.nio.file.Paths;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;

public class GameTitle implements Drawable{

	private Font freeSans = new Font();

	private Text gameTitle;

	public GameTitle(RenderWindow renderWindow) {

		try
		{
			freeSans.loadFromFile(Paths.get("Resources/FreeSans.otf"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		gameTitle = new Text("BEJEWBLED", freeSans, 50);
		gameTitle.setColor(Color.BLACK);
		gameTitle.setStyle(Text.BOLD | Text.UNDERLINED);
		
		FloatRect gameTitleBounds = gameTitle.getLocalBounds();
		gameTitle.setOrigin(gameTitleBounds.width/2, gameTitleBounds.height/3);
		gameTitle.setPosition(new Vector2f(renderWindow.getSize().x / 2, renderWindow.getSize().y / 20));
	}

	public void draw(RenderTarget target, RenderStates states) {

		gameTitle.draw(target, states);
	}

}
