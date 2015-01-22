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
		BLUE, PURPLE, RED, YELLOW
	};

	private Color assignedColor;

	private Vector2i boardPosition;

	private Texture jewbelTexture;

	private AnimatedSprite jewbelSprite;
	
	private Font freeSans = new Font();
	
	private Text indexNumber;

	public boolean passedBy = false;

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
		
		/**
		
		**/

		boardPosition = gameBoardPosition;
		initSprite();
		
		/**
		indexNumber = new Text("" + boardPosition.x + "," + boardPosition.y, freeSans, 20);
		indexNumber.setColor(org.jsfml.graphics.Color.BLACK);
		indexNumber.setStyle(Text.BOLD);
		**/
	}

	public Jewbel(Vector2i gameBoardPosition, Color jewbelColor) {

		this(gameBoardPosition);
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

	public void setCenterTilePosition(Vector2f tilePosition) {

		jewbelSprite.setPosition(Vector2f.add(tilePosition,
				new Vector2f(jewbelTexture.getSize().x / 8, jewbelTexture.getSize().y / 8)));
		
		/**indexNumber.setPosition(jewbelSprite.getPosition());**/
	}

	public void setPosition(Vector2f jewbelPosition) {

		jewbelSprite.slideToPosition(jewbelPosition, 100f);
		/**indexNumber = new Text("" + boardPosition.x + "," + boardPosition.y, freeSans, 20);
		indexNumber.setColor(org.jsfml.graphics.Color.BLACK);
		indexNumber.setStyle(Text.BOLD);
		indexNumber.setPosition(jewbelSprite.getPosition());**/
	}

	public Vector2i getBoardIndex() {

		return boardPosition;
	}

	public void setBoardIndex(Vector2i jewbelIndex) {

		boardPosition = jewbelIndex;
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
		jewbelSprite = new AnimatedSprite(jewbelTexture);
	}

	public Sprite getSprite() {

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
