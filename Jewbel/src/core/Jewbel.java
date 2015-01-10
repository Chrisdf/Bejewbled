package core;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;


public class Jewbel implements Drawable {

	private int color;

	private Vector2i boardPosition;

	private Texture jewbelTexture;

	private Sprite jewbelSprite;

	public Jewbel(Vector2i gameBoardPosition) {

		color = (int) (Math.random() * 4 + 1);
		boardPosition = gameBoardPosition;
		initSprite(color);
	}

	public Jewbel(Vector2i gameBoardPosition, int jewbelType) {

		color = jewbelType;
		boardPosition = gameBoardPosition;
		initSprite(color);
	}

	public Vector2i getAboveJewbel() {

		if (boardPosition.y == 0)
			return new Vector2i(boardPosition.x, -1);
		else
			return new Vector2i(boardPosition.x, boardPosition.y - 1);
	}

	public Vector2i getBelowJewbel() {

		if (boardPosition.y == 7)
			return new Vector2i(boardPosition.x, -1);
		else
			return new Vector2i(boardPosition.x, boardPosition.y + 1);
	}

	public Vector2i getLeftJewbel() {

		if (boardPosition.x == 0)
			return new Vector2i( -1, boardPosition.y);
		else
			return new Vector2i(boardPosition.x - 1, boardPosition.y);
	}

	public Vector2i getRightJewbel() {

		if (boardPosition.x == 7)
			return new Vector2i( -1, boardPosition.y);
		else
			return new Vector2i(boardPosition.x + 1, boardPosition.y);
	}

	public void setPosition(Vector2f tilePosition) {

		jewbelSprite.setPosition(Vector2f.add(tilePosition, new Vector2f(jewbelTexture.getSize().x/8, jewbelTexture.getSize().y/8)));
	}

	public void initSprite(int color) {
		jewbelTexture = new Texture();
		try
		{
			switch (color)
			{
				case 1:
					jewbelTexture.loadFromFile(Paths.get("Resources/redJewel.png"));
					break;
				case 2:
					jewbelTexture.loadFromFile(Paths.get("Resources/yellowJewel.png"));
					break;
				case 3:
					jewbelTexture.loadFromFile(Paths.get("Resources/purpleJewel.png"));
					break;
				case 4:
					jewbelTexture.loadFromFile(Paths.get("Resources/blueJewel.png"));
					break;
				//default:
					//jewbelTexture.loadFromFile(Paths.get("Resources/yellowJewel.png"));
			}

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		jewbelSprite = new Sprite(jewbelTexture);
	}

	public void draw(RenderTarget target, RenderStates states) {

		jewbelSprite.draw(target, states);
	}
}
