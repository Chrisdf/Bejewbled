package ui_elements;

import java.nio.file.Paths;

import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;
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
	
	private SoundBuffer explosionNoise;
	
	public ExplosionEffect(Vector2f initialPosition){
		
		explosionTexture = new Texture();
		explosionNoise = new SoundBuffer();
		
		try
		{	
			explosionNoise.loadFromFile(Paths.get("Resources/explosionNoise.wav"));
			explosionTexture.loadFromFile(Paths.get("Resources/explosionSpriteSheet.png"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		Sound explode = new Sound(explosionNoise);
		explode.setVolume(0.9f);
		explode.play();
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
