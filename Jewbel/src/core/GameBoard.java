package core;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Window;

import ui_elements.JewbelSelect;


public class GameBoard implements Drawable {

	private GameTile gameBoardSize[][];

	private Jewbel jewbelsOnScreen[][];

	private Window renderWindow;
	
	private JewbelSelect selectionBox;

	public GameBoard(RenderWindow window) {

		gameBoardSize = new GameTile[8][8];
		jewbelsOnScreen = new Jewbel[gameBoardSize.length][gameBoardSize.length];
		renderWindow = window;
		selectionBox = new JewbelSelect();

		for (int i = 0; i < gameBoardSize.length; i++ )
			for (int d = 0; d < gameBoardSize[i].length; d++ )
			{
				gameBoardSize[i][d] = new GameTile();
				gameBoardSize[i][d].setPosition(new Vector2f(renderWindow.getSize().x / 3, renderWindow.getSize().y / 6));
				gameBoardSize[i][d].setPosition(Vector2f.add(gameBoardSize[i][d].getTilePosition(), new Vector2f(i * 64, d * 64)));
				jewbelsOnScreen[i][d] = new Jewbel(new Vector2i(i, d));
				jewbelsOnScreen[i][d].setInitialPosition(gameBoardSize[i][d].getTilePosition());
			}
	}

	public void draw(RenderTarget target, RenderStates states) {

		//Goes through every tile on the gameboard and draws it
		for (GameTile[] horizontalRows : gameBoardSize)
			for (GameTile verticalRows : horizontalRows)
				verticalRows.draw(target, states);
		
		selectionBox.draw(target, states);

		for (Jewbel[] horizontalJewbels : jewbelsOnScreen)
			for (Jewbel verticalJewbels : horizontalJewbels)
				verticalJewbels.draw(target, states);
		
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
						} else if (firstJewbel.equals(secondJewbel)) {
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
		firstJewbel.setBoardPosition(secondJewbel.getBoardIndex());
		secondJewbel.setBoardPosition(firstJewbelBoardIndex);
		jewbelsOnScreen[jewbelBoardPositionX][jewbelBoardPositionY] = firstJewbel;
		jewbelsOnScreen[selectionBox.getSelectedJewbelIndex().x][selectionBox.getSelectedJewbelIndex().y] = secondJewbel;
		
		selectionBox.setJewbelSelect(false);
		selectionBox = new JewbelSelect();
	}
}