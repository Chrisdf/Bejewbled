package core;

import java.io.IOException;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Window;


public class gameBoard implements Drawable {

	private gameTile gameBoardSize[][];

	private Window renderWindow;
	
	public gameBoard(RenderWindow window) {
		
		gameBoardSize = new gameTile[8][8];
		renderWindow = window;

		for(int i = 0; i< gameBoardSize.length; i++)
			for(int d = 0; d<gameBoardSize[i].length; d++){
				gameBoardSize[i][d] = new gameTile();
				gameBoardSize[i][d].setPosition(new Vector2f(renderWindow.getSize().x/3, renderWindow.getSize().y/6));
				gameBoardSize[i][d].setPosition(Vector2f.add(gameBoardSize[i][d].getTilePosition(), new Vector2f(i*64, d*64)));
			}
	}

	public void draw(RenderTarget target, RenderStates states) {
		
		//Goes through every tile on the gameboard and draws it
		for (gameTile[] horizontalRows : gameBoardSize)
			for (gameTile verticalRows : horizontalRows)
				verticalRows.draw(target, states);
	}
	
	public void findJewbelToSelect(Vector2i mousePosition){

		
		for(gameTile[] selectedTileRow: gameBoardSize)
			for(gameTile selectedTile: selectedTileRow){
				if(mousePosition.x >= selectedTile.getSprite().getPosition().x && mousePosition.x <= selectedTile.getSprite().getPosition().x + selectedTile.getTextureSize().x)
					if(mousePosition.y >= selectedTile.getSprite().getPosition().y && mousePosition.y <= selectedTile.getSprite().getPosition().y + selectedTile.getTextureSize().y)
						selectedTile.getSprite().setRotation(selectedTile.getSprite().getRotation() + 45f);
			}
	}
	
}
