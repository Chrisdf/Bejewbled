package ui_elements;

import java.nio.file.Paths;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class GameTitle implements Drawable {

	private Texture gameTitleImage;
	
	private Sprite gameTitle;

	public GameTitle(RenderWindow renderWindow) {

		gameTitleImage = new Texture();
		
		try
		{
			gameTitleImage.loadFromFile(Paths.get("Resources/titleLogo.png"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		gameTitle = new Sprite(gameTitleImage);

		FloatRect gameTitleBounds = gameTitle.getLocalBounds();
		gameTitle.setOrigin(gameTitleBounds.width / 2, gameTitleBounds.height / 3);
		gameTitle.setPosition(new Vector2f(renderWindow.getSize().x / 2, renderWindow.getSize().y / 20));
	}

	public void draw(RenderTarget target, RenderStates states) {

		gameTitle.draw(target, states);
	}

}
