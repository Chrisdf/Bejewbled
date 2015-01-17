package core;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Window;

import core.Jewbel.Color;
import ui_elements.JewbelSelect;


public class GameBoard implements Drawable {

	private GameTile gameBoardSize[][];

	private Jewbel jewbelsOnScreen[][];

	private RectangleShape boundingBox;
	
	private Window renderWindow;
	
	private JewbelSelect selectionBox;

	public GameBoard(RenderWindow window) {

		gameBoardSize = new GameTile[8][8];
		jewbelsOnScreen = new Jewbel[gameBoardSize.length][gameBoardSize.length];
		renderWindow = window;
		selectionBox = new JewbelSelect();
		boundingBox = new RectangleShape();

		for (int i = 0; i < gameBoardSize.length; i++ )
			for (int d = 0; d < gameBoardSize[i].length; d++ )
				gameBoardSize[i][d] = new GameTile();		
		
		Vector2i boundingBoxSize = Vector2i.mul(gameBoardSize[0][0].getTextureSize(), gameBoardSize.length);
		boundingBox.setSize(new Vector2f(boundingBoxSize));
		Vector2f renderWindowCenter = new Vector2f(renderWindow.getSize().x / 2, renderWindow.getSize().y / 1.5f);
		Vector2f boundingBoxCenter = new Vector2f(boundingBox.getGlobalBounds().width/2, boundingBox.getGlobalBounds().height/ 1.5f);
		Vector2f boundingBoxPosition = Vector2f.sub(renderWindowCenter, boundingBoxCenter);
		boundingBox.setPosition(boundingBoxPosition);
		boundingBox.setFillColor(org.jsfml.graphics.Color.TRANSPARENT);
		
		for (int i = 0; i < gameBoardSize.length; i++)
			for(int d = 0; d < gameBoardSize[i].length; d++)
			{
				gameBoardSize[i][d].setPosition(boundingBox.getPosition());
				gameBoardSize[i][d].setPosition(Vector2f.add(gameBoardSize[i][d].getTilePosition(), new Vector2f(i * 64, d * 64)));
				jewbelsOnScreen[i][d] = new Jewbel(new Vector2i(i, d));
				jewbelsOnScreen[i][d].setInitialPosition(gameBoardSize[i][d].getTilePosition());
			}
	}

	public void draw(RenderTarget target, RenderStates states) {

		//Draws every gameboard tile
		for (GameTile[] horizontalRows : gameBoardSize)
			for (GameTile verticalRows : horizontalRows)
				verticalRows.draw(target, states);
		
		selectionBox.draw(target, states);

		//Draws all jewbels that are not null
		for (Jewbel[] horizontalJewbels : jewbelsOnScreen)
			for (Jewbel verticalJewbels : horizontalJewbels)
				if(verticalJewbels != null)
					verticalJewbels.draw(target, states);
		
		boundingBox.draw(target, states);
	}

	public void findJewbelToSelect(Vector2i mousePosition) {


		for (int i = 0; i<gameBoardSize.length; i++)
			for (int d = 0; d<gameBoardSize[i].length; d++)
			{
				if (!selectionBox.getIfJewbelSelected())
				{	
					//First click on game board if a jewbel has not been selected already
					if (mouseOnGameTile(gameBoardSize[i][d], mousePosition))
					{
						Vector2f jewbelPosition = gameBoardSize[i][d].getTilePosition();
						selectionBox = new JewbelSelect(jewbelPosition, new Vector2i(i, d));
						selectionBox.setJewbelSelect(true);
					}
				}
				else if (selectionBox.getIfJewbelSelected())
				{
					//The two selected jewbels swap
					if (mouseOnGameTile(gameBoardSize[i][d], mousePosition))
					{
						Jewbel firstJewbel = jewbelsOnScreen[selectionBox.getSelectedJewbelIndex().x][selectionBox.getSelectedJewbelIndex().y];
						Jewbel secondJewbel = jewbelsOnScreen[i][d];
						
						if(firstJewbel.getIfAdjacent(secondJewbel))
						{
							swapJewbels(firstJewbel, secondJewbel, i, d);
						} 
						
						else if (firstJewbel.equals(secondJewbel)) 
						{	
							// deselection
							selectionBox.setJewbelSelect(false);
							selectionBox = new JewbelSelect();
						}
					}
				}
			}
	}
	
	public boolean mouseOnGameTile(GameTile gameTile, Vector2i mousePosition){
		
		if(mousePosition.x >= gameTile.getSprite().getPosition().x 
				&& mousePosition.x <= gameTile.getSprite().getPosition().x + gameTile.getTextureSize().x)
			if (mousePosition.y >= gameTile.getSprite().getPosition().y 
				&& mousePosition.y <= gameTile.getSprite().getPosition().y + gameTile.getTextureSize().y)
				return true;
		
		return false;
		
	}
	
	public void swapJewbels(Jewbel firstJewbel, Jewbel secondJewbel, int jewbelBoardPositionX, int jewbelBoardPositionY){
		
		Vector2f firstJewbelPosition = firstJewbel.getSprite().getPosition();
		Vector2f secondJewbelPosition = secondJewbel.getSprite().getPosition();
		
		firstJewbel.setPosition(secondJewbelPosition);
		secondJewbel.setPosition(firstJewbelPosition);
		Vector2i firstJewbelBoardIndex = firstJewbel.getBoardIndex();
		firstJewbel.setBoardIndex(secondJewbel.getBoardIndex());
		secondJewbel.setBoardIndex(firstJewbelBoardIndex);
		jewbelsOnScreen[jewbelBoardPositionX][jewbelBoardPositionY] = firstJewbel;
		jewbelsOnScreen[selectionBox.getSelectedJewbelIndex().x][selectionBox.getSelectedJewbelIndex().y] = secondJewbel;
		
		selectionBox.setJewbelSelect(false);
		selectionBox = new JewbelSelect();
		
		checkForMatches(firstJewbel);
		checkForMatches(secondJewbel);
	}
	
	public void checkForMatches(Jewbel firstJewbel){
	
		/**
		 * This method recursively runs through both the horizontal matching and vertical matching methods. If
		 * either of the methods return that there are three or more jewels that are the same color as the
		 * original jewel then this method recursively reruns those methods with a true boolean, which will
		 * allow modification of the jewels after finding matches
		 */
		
		int totalMatchingHorizontally = checkForMatchesHorizontally(firstJewbel, firstJewbel.getBoardIndex(), false);
		int totalMatchingVertically = checkForMatchesVertically(firstJewbel, firstJewbel.getBoardIndex(), false);
		
		if(totalMatchingVertically >= 3 || totalMatchingHorizontally >= 3)
			{
			if(totalMatchingHorizontally >= totalMatchingVertically)
				checkForMatchesHorizontally(firstJewbel, firstJewbel.getBoardIndex(), true);
			
			if(totalMatchingVertically > totalMatchingHorizontally)
				checkForMatchesVertically(firstJewbel, firstJewbel.getBoardIndex(), true);
			}
		}
	
	public int checkForMatchesHorizontally(Jewbel firstJewbel, Vector2i jewbelIndex, boolean hasMatch){
		
		Jewbel oneToCheck = null;
		
		//This color will always be the original jewbels color
		Color colorToMatch = firstJewbel.getColor();
		
		//The amount of adjacent jewbels total that are matching
		int numMatching = 0;
		
		//Get the location of the new jewbel on the game board index
		int boardPositionX = jewbelIndex.x;
		int boardPositionY = jewbelIndex.y;
		
		//Check the bounds of the jewbel we are looking at
		if(boardPositionX >= 0 && boardPositionX < gameBoardSize.length)
			if(boardPositionY >= 0 && boardPositionY < gameBoardSize.length)
				oneToCheck = jewbelsOnScreen[boardPositionX][boardPositionY];
		
		//If the jewbel we are looking at exists and has not been checked yet,
		//continue checking
		if(oneToCheck != null && oneToCheck.passedBy == false)
		{
			if(colorToMatch.equals(oneToCheck.getColor()))
			{
				//If there have been three or more jewbels total that have been matched run below code
				if(hasMatch == true)
				{
					oneToCheck.passedBy = true;
					
					
					
					//CODE THAT RUNS WHEN A MATCH IS TRUE HORIZONTALLY
					jewbelsOnScreen[boardPositionX][boardPositionY] = null;
					
					
					
					//Recursively runs through all other jewbels horizontally
					checkForMatchesHorizontally(firstJewbel, oneToCheck.getLeftJewbel(), true);
					checkForMatchesHorizontally(firstJewbel, oneToCheck.getRightJewbel(), true);
				}
				
				//After checking jewbel, make sure to mark it as checked
				oneToCheck.passedBy = true;
				
				numMatching += checkForMatchesHorizontally(firstJewbel, oneToCheck.getLeftJewbel(), false);
				numMatching += checkForMatchesHorizontally(firstJewbel, oneToCheck.getRightJewbel(), false);
				
				//Remove marking the jewbel as checked, so it can be recursively called again on another update
				oneToCheck.passedBy = false;
				
				//Return the number of matching jewbels so far plus one if this jewbel matches
				return numMatching + 1;
			}
			
			//If it does not match the original color or the jewbel is null, return 0
			else
				return 0;
		}
		
		return numMatching;
	}
	
public int checkForMatchesVertically(Jewbel firstJewbel, Vector2i jewbelIndex, boolean hasMatch){
		
		Jewbel oneToCheck = null;
		
		//This color will always be the original jewbels color
		Color colorToMatch = firstJewbel.getColor();
		
		//The amount of adjacent jewbels total that are matching
		int numMatching = 0;
		
		//Get the location of the new jewbel on the game board index
		int boardPositionX = jewbelIndex.x;
		int boardPositionY = jewbelIndex.y;
		
		//Check the bounds of the jewbel we are looking at
		if(boardPositionX >= 0 && boardPositionX < gameBoardSize.length)
			if(boardPositionY >= 0 && boardPositionY < gameBoardSize.length)
				oneToCheck = jewbelsOnScreen[boardPositionX][boardPositionY];
		
		//If the jewbel we are looking at exists and has not been checked yet,
		//continue checking
		if(oneToCheck != null && oneToCheck.passedBy == false)
		{
			if(colorToMatch.equals(oneToCheck.getColor()))
			{
				//If there have been three or more jewbels total that have been matched run below code
				if(hasMatch == true)
				{
					oneToCheck.passedBy = true;
					
					
					
					//CODE THAT RUNS WHEN A MATCH IS TRUE VERTICALLY
					jewbelsOnScreen[boardPositionX][boardPositionY] = null;
					
					
					
					//Recursively runs through all other jewbels vertically if match is true
					checkForMatchesVertically(firstJewbel, oneToCheck.getAboveJewbel(), true);
					checkForMatchesVertically(firstJewbel, oneToCheck.getBelowJewbel(), true);
				}
				
				//After checking jewbel, make sure to mark it as checked
				oneToCheck.passedBy = true;
				
				numMatching += checkForMatchesVertically(firstJewbel, oneToCheck.getAboveJewbel(), false);
				numMatching += checkForMatchesVertically(firstJewbel, oneToCheck.getBelowJewbel(), false);
				
				//Remove marking the jewbel as checked, so it can be recursively called again on another update
				oneToCheck.passedBy = false;
				
				//Return the number of matching jewbels so far plus one if this jewbel matches
				return numMatching + 1;
			}
			
			//If it does not match the original color or the jewbel is null, return 0
			else
				return 0;
		}
		
		return numMatching;
	}
	
}
