package ui_elements;

import java.nio.file.Paths;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import core.GameBoard;

public class Background implements Drawable {

	private RenderWindow renderWindow;

	private Texture backgroundTexture = new Texture();

	private Sprite backgroundSprite = new Sprite();

	public Background(RenderWindow window) {

		renderWindow = window;

		try
		{
			backgroundTexture.loadFromFile(Paths.get("Resources/background.jpg"));
			
			if(GameBoard.secret())
				backgroundTexture.loadFromFile(Paths.get("Resources/secretResources/back.png"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		backgroundSprite.setTexture(backgroundTexture);
		scaleBackground();
	}

	public void scaleBackground() {

		Vector2f scaleRatio =
				Vector2f.componentwiseDiv(new Vector2f(renderWindow.getSize()), new Vector2f(backgroundTexture.getSize()));

		backgroundSprite.scale(scaleRatio);
	}

	public void draw(RenderTarget target, RenderStates states) {

		backgroundSprite.draw(target, states);
	}
}
