package ui_elements;

import java.nio.file.Paths;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;


public class TurnsLeft implements Drawable {


	private Font comic;

	private Text turnsLeftDisplay;

	private int fontSize;

	private int numOfTurnsLeft;

	private int swapCount;
	
	GameTitle gameTitle;


	public TurnsLeft(int turnsLeft, GameTitle title) {

		gameTitle = title;
		numOfTurnsLeft = turnsLeft;
		comic = new Font();
		fontSize = 30;

		try
		{
			comic.loadFromFile(Paths.get("Resources/Comic.ttf"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		turnsLeftDisplay = new Text("Turns left: " + numOfTurnsLeft, comic, fontSize);
		
		
		Sprite titleSprite = gameTitle.getTitleSprite();
		Vector2f gameTitleCenterPosition = titleSprite.getPosition();
		gameTitleCenterPosition = Vector2f.add(gameTitleCenterPosition, new Vector2f(0, titleSprite.getLocalBounds().height/2));
		
		turnsLeftDisplay.setOrigin(turnsLeftDisplay.getLocalBounds().width/2, turnsLeftDisplay.getLocalBounds().height);
		turnsLeftDisplay.setPosition(gameTitleCenterPosition);
		turnsLeftDisplay.setColor(Color.BLACK);
		
	}

	public int getSwapCount() {

		return swapCount;
	}

	public void setSwapCount(int count) {

		swapCount = count;
	}

	public int getTurnsLeft() {

		return numOfTurnsLeft;
	}

	public void setTurnsLeft(int turnsLeft) {

		numOfTurnsLeft = turnsLeft;
	}

	public void draw(RenderTarget target, RenderStates states) {

		turnsLeftDisplay.draw(target, states);
	}
	
	public void decreaseTurnsLeft(){
		
		numOfTurnsLeft--;
	}
	
	public void update(){
		
		if(numOfTurnsLeft > 0)
			turnsLeftDisplay.setString("Turns Left: " + numOfTurnsLeft);
		else
			turnsLeftDisplay.setString("GAME OVER!!!");
	}

}
