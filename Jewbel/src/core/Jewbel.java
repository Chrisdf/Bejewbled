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
	
	private enum Color {BLUE, PURPLE, RED, YELLOW};
	
	private Color assignedColor;

	private Vector2i boardPosition;

	private Texture jewbelTexture;

	private Sprite jewbelSprite;

	public Jewbel(Vector2i gameBoardPosition) {

		int randomColor = (int) (Math.random() * 4 + 1);
		
		switch (randomColor)
		{
			case 1:
				assignedColor = Color.RED;
				break;
			case 2:
				assignedColor = Color.YELLOW;
				break;
			case 3:
				assignedColor = Color.PURPLE;
				break;
			case 4:
				assignedColor = Color.BLUE;
				break;
		}
		
		boardPosition = gameBoardPosition;
		initSprite();
	}

	public Jewbel(Vector2i gameBoardPosition, Color jewbelColor) {

		this(gameBoardPosition);
		assignedColor = jewbelColor;
	}
	
	public void swapJewbel(Jewbel oneToSwap) {
		
		Vector2f firstJewbelPosition = getSprite().getPosition();
		Vector2f secondJewbelPosition = oneToSwap.getSprite().getPosition();
		setPosition(secondJewbelPosition);
		oneToSwap.setPosition(firstJewbelPosition);
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

	public void setInitialPosition(Vector2f tilePosition) {

		jewbelSprite.setPosition(Vector2f.add(tilePosition,
				new Vector2f(jewbelTexture.getSize().x / 8, jewbelTexture.getSize().y / 8)));
	}
	
	public void setPosition(Vector2f jewbelPosition){
		
		jewbelSprite.setPosition(jewbelPosition);
	}

	public void initSprite() {
		jewbelTexture = new Texture();
		try
		{
			switch (assignedColor)
			{
				case RED:
					jewbelTexture.loadFromFile(Paths.get("Resources/redJewel.png"));
					break;
				case YELLOW:
					jewbelTexture.loadFromFile(Paths.get("Resources/yellowJewel.png"));
					break;
				case PURPLE:
					jewbelTexture.loadFromFile(Paths.get("Resources/purpleJewel.png"));
					break;
				case BLUE:
					jewbelTexture.loadFromFile(Paths.get("Resources/blueJewel.png"));
					break;
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
	
	public Sprite getSprite(){
		
		return jewbelSprite;
	}
}
