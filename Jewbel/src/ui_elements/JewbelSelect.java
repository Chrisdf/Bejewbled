package ui_elements;

import java.nio.file.Paths;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;


public class JewbelSelect implements Drawable {

	private Texture selectionTexture = new Texture();
	
	private Sprite selectionSprite = new Sprite();
	
	public JewbelSelect(){
		
		try
		{
			selectionTexture.loadFromFile(Paths.get("Resources/selectionTexture.jpg"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		selectionSprite.setTexture(selectionTexture);
		selectionSprite.setPosition(new Vector2f(-100, -100));
	}
	
	public JewbelSelect(Vector2f position){
		
		this();
		selectionSprite.setPosition(position);
	}
	
	private void setPosition(Vector2f position){
		
		selectionSprite.setPosition(position);
	}

	public void draw(RenderTarget target, RenderStates states) {
	
		selectionSprite.draw(target, states);
	}
}
