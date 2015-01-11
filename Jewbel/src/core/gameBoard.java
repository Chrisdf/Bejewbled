package core;

import java.io.IOException;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Window;

import ui_elements.JewbelSelect;


public class gameBoard implements Drawable {

	private gameTile gameBoardSize[][];

	private Jewbel jewbelsOnScreen[][];

	private Window renderWindow;
	
	private JewbelSelect selectionBox;

	public gameBoard(RenderWindow window) {

		gameBoardSize = new gameTile[8][8];
		jewbelsOnScreen = new Jewbel[gameBoardSize.length][gameBoardSize.length];
		renderWindow = window;
		selectionBox = new JewbelSelect();

		for (int i = 0; i < gameBoardSize.length; i++ )
			for (int d = 0; d < gameBoardSize[i].length; d++ )
			{
				gameBoardSize[i][d] = new gameTile();
				gameBoardSize[i][d].setPosition(new Vector2f(renderWindow.getSize().x / 3, renderWindow.getSize().y / 6));
				gameBoardSize[i][d].setPosition(Vector2f.add(gameBoardSize[i][d].getTilePosition(),
						new Vector2f(i * 64, d * 64)));
				jewbelsOnScreen[i][d] = new Jewbel(new Vector2i(i, d));
				jewbelsOnScreen[i][d].setPosition(gameBoardSize[i][d].getTilePosition());
			}
	}

	public void draw(RenderTarget target, RenderStates states) {

		//Goes through every tile on the gameboard and draws it
		for (gameTile[] horizontalRows : gameBoardSize)
			for (gameTile verticalRows : horizontalRows)
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
				if (mousePosition.x >= gameBoardSize[i][d].getSprite().getPosition().x
						&& mousePosition.x <= gameBoardSize[i][d].getSprite().getPosition().x + gameBoardSize[i][d].getTextureSize().x)
					if (mousePosition.y >= gameBoardSize[i][d].getSprite().getPosition().y
							&& mousePosition.y <= gameBoardSize[i][d].getSprite().getPosition().y + gameBoardSize[i][d].getTextureSize().y){
						Vector2f jewbelPosition = gameBoardSize[i][d].getTilePosition();
						selectionBox = new JewbelSelect(jewbelPosition);
			
					}
			}
	}

}
