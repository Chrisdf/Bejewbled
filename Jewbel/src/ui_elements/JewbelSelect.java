package ui_elements;

import java.nio.file.Paths;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;


public class JewbelSelect implements Drawable {

	private Texture selectionTexture = new Texture();

	private Sprite selectionSprite = new Sprite();

	private boolean jewbelSelected = false;

	private Vector2i selectedJewbelIndex;

	public JewbelSelect() {

		try
		{
			selectionTexture.loadFromFile(Paths.get("Resources/selectTexture.jpg"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		selectionSprite.setTexture(selectionTexture);
		selectionSprite.setPosition(new Vector2f( -100, -100));
	}

	public JewbelSelect(Vector2f position, Vector2i jewbelBoardIndex) {

		this();
		selectedJewbelIndex = jewbelBoardIndex;
		selectionSprite.setPosition(position);
	}

	public void setPosition(Vector2f position) {

		selectionSprite.setPosition(position);
	}

	public void draw(RenderTarget target, RenderStates states) {

		selectionSprite.draw(target, states);
	}

	public void setJewbelSelect(boolean ifSelected) {

		jewbelSelected = ifSelected;
	}

	public boolean getIfJewbelSelected() {

		return jewbelSelected;
	}

	public Vector2i getSelectedJewbelIndex() {

		return selectedJewbelIndex;
	}

	public void setSelectedJewbelIndex(Vector2i index) {

		selectedJewbelIndex = index;
	}
}
