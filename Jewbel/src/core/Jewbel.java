package core;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

import util.AnimatedSprite;


public class Jewbel implements Drawable {

	public enum Color {
		BLUE, PURPLE, RED, YELLOW, ORANGE, GREEN
	};
	
	private int gameBoardSize;

	private Color assignedColor;

	private Vector2i boardPosition;

	private Texture jewbelTexture;

	private AnimatedSprite jewbelSprite;

	public boolean passedBy = false;

	public Jewbel(Vector2i gameBoardPosition, int gameBoardSize) {

		this.gameBoardSize = gameBoardSize;
		
		int randomColor = (int) (Math.random() * 6 + 1);

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
			case 5:
				assignedColor = Color.ORANGE;
				break;
			case 6:
				assignedColor = Color.GREEN;
				break;
		}

		boardPosition = gameBoardPosition;
		initSprite();

	}

	public Jewbel(Vector2i gameBoardPosition, Color jewbelColor, int gameBoardSize) {

		this(gameBoardPosition, gameBoardSize);
		assignedColor = jewbelColor;
	}

	public boolean getIfAdjacent(Jewbel secondJewbel) {

		int xDistance = Math.abs(boardPosition.x - secondJewbel.getBoardIndex().x);
		int yDistance = Math.abs(boardPosition.y - secondJewbel.getBoardIndex().y);

		if ((xDistance == 1 && yDistance == 0) || (yDistance == 1 && xDistance == 0))
			return true;
		else
			return false;
	}

	public Vector2i getAboveJewbel() {

		if (boardPosition.y == 0)
			return new Vector2i(boardPosition.x, -1);
		else
			return new Vector2i(boardPosition.x, boardPosition.y - 1);
	}

	public Vector2i getBelowJewbel() {

		if (boardPosition.y == gameBoardSize - 1)
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

		if (boardPosition.x == gameBoardSize - 1)
			return new Vector2i( -1, boardPosition.y);
		else
			return new Vector2i(boardPosition.x + 1, boardPosition.y);
	}

	public void setCenterTilePosition(Vector2f tilePosition) {

		jewbelSprite.slideToPosition(
				Vector2f.add(tilePosition, new Vector2f(jewbelTexture.getSize().x / 8, jewbelTexture.getSize().y / 8)), 45f);
	}

	public void setPosition(Vector2f jewbelPosition) {

		jewbelSprite.slideToPosition(jewbelPosition, 45f);
	}

	public Vector2i getBoardIndex() {

		return boardPosition;
	}

	public void setBoardIndex(Vector2i jewbelIndex) {

		boardPosition = jewbelIndex;
	}

	private void initSprite() {
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
				case GREEN:
					jewbelTexture.loadFromFile(Paths.get("Resources/greenJewel.png"));
					break;
				case ORANGE:
					jewbelTexture.loadFromFile(Paths.get("Resources/orangeJewel.png"));
					break;
			}

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		jewbelSprite = new AnimatedSprite(jewbelTexture);
	}

	public AnimatedSprite getSprite() {

		return jewbelSprite;
	}

	public Color getColor() {

		return assignedColor;
	}

	public void update() {

		jewbelSprite.animate();
	}

	public void draw(RenderTarget target, RenderStates states) {

		jewbelSprite.draw(target, states);
		/**indexNumber.draw(target, states);**/
	}

}
