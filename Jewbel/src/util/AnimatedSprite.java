package util;

import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;


public class AnimatedSprite extends Sprite {

	private boolean animated = false;

	private Vector2f rateOfChangeOfPosition = null;

	private Vector2f targetPosition = null;

	public AnimatedSprite() {}
	
	public AnimatedSprite(Texture texture){
		
		super(texture);
	}

	public void setAnimated(boolean ifAnimated) {

		animated = ifAnimated;
	}

	public boolean getIfAnimated() {

		return animated;
	}

	public void slideToPosition(Vector2f finalPosition, float animationSpeed) {

		targetPosition = finalPosition;
		setAnimated(true);

		Vector2f distanceBetweenPositions = Vector2f.sub(finalPosition, super.getPosition());
		rateOfChangeOfPosition = Vector2f.div(distanceBetweenPositions, animationSpeed);
	}

	public void animate() {

		if (animated)
		{
			if (rateOfChangeOfPosition != null)
			{
				if (isEqualHorizontally(super.getPosition()) && isEqualVertically(super.getPosition()))
					rateOfChangeOfPosition = null;

				else
					super.setPosition(Vector2f.add(super.getPosition(), rateOfChangeOfPosition));
			}
		}

		if (rateOfChangeOfPosition == null)
			setAnimated(false);
	}
	
	private boolean isEqualHorizontally(Vector2f currentLocation){
		
		if(Math.round(currentLocation.x) == Math.round(targetPosition.x))
			return true;
		else
			return false;
	}
	
	private boolean isEqualVertically(Vector2f currentLocation){
		
		if(Math.round(currentLocation.y) == Math.round(targetPosition.y))
			return true;
		else
			return false;
	}

	public Vector2f getFinalPosition(){
		
		return targetPosition;
	}
	
}
