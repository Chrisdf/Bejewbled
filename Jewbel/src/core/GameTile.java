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


public class GameTile implements Drawable {

	private Texture texture;

	private Sprite sprite;

	private Vector2f position;


	public GameTile() {
		texture = new Texture();
		try
		{
			texture.loadFromFile(Paths.get("Resources/gameTile.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		sprite = new Sprite(texture);
	}


	public void draw(RenderTarget target, RenderStates states) {
		
		sprite.draw(target, states);
	}

	public Vector2f getTilePosition() {
		
		return position;
	}

	public void setPosition(Vector2f pos) {
		
		position = pos;
		sprite.setPosition(position);
	}
	
	public void setOriginPosition(Vector2f origin){
		
		sprite.setOrigin(origin);
	}

	public Vector2i getTextureSize() {
		
		return texture.getSize();
	}

	public Sprite getSprite() {
		
		return sprite;
	}
}
