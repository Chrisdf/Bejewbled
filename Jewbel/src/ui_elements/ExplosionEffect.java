package ui_elements;

import java.nio.file.Paths;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;


public class ExplosionEffect implements Drawable{

	private int frameCount = 0;
	
	private Texture explosionTexture;
	
	private Sprite explosionSprite;
	
	public ExplosionEffect(Vector2f initialPosition){
		
		explosionTexture = new Texture();
		
		try
		{
			explosionTexture.loadFromFile(Paths.get("Resources/explosionSpriteSheet.png"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		explosionSprite = new Sprite(explosionTexture);
		explosionSprite.setTextureRect(new IntRect(0, 0, 64, 64));
		explosionSprite.setPosition(initialPosition);
	}
	
	public void update(){
		
		frameCount++;
		
		int frameRow = frameCount / 5;
    int frameCol = frameCount % 5;
    explosionSprite.setTextureRect(new IntRect(frameCol * 64, frameRow * 64, 64, 64));
	}
	
	public boolean hasAnimationFinished(){
		
		if(frameCount > 125)
			return true;
		
		else
			return false;
	}

	public void draw(RenderTarget target, RenderStates states) {

		explosionSprite.draw(target, states);
	}
	
}
