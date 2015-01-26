package util;

import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;


public class AnimatedSprite extends Sprite {

	private boolean animated = false;

	private Vector2f rateOfChangeOfPosition = null;

	private Vector2f rateOfChangeOfScale = null;

	private float rateOfChangeOfRotation = 0.f;

	private Vector2f targetPosition = null;

	private Vector2f targetScale = null;

	private float targetRotationDegrees;

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

	public void spinToRotation(float targetDegrees, boolean clockWise, float animationSpeed) {

		targetRotationDegrees = targetDegrees;
		setAnimated(true);
		float distanceBetweenDegrees;

		if (clockWise)
			distanceBetweenDegrees = targetDegrees - super.getRotation();
		else
			distanceBetweenDegrees = super.getRotation() - targetDegrees;

		rateOfChangeOfRotation = distanceBetweenDegrees / animationSpeed;
	}

	public void slideToPosition(Vector2f finalPosition, float animationSpeed) {

		targetPosition = finalPosition;
		setAnimated(true);

		Vector2f distanceBetweenPositions = Vector2f.sub(finalPosition, super.getPosition());
		rateOfChangeOfPosition = Vector2f.div(distanceBetweenPositions, animationSpeed);
	}

	public void animateToScale(Vector2f finalScale, float animationSpeed) {

		targetScale = finalScale;
		setAnimated(true);
		
		Vector2f distanceBetweenScales = Vector2f.sub(finalScale, super.getScale());
		rateOfChangeOfScale = Vector2f.div(distanceBetweenScales, animationSpeed);
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

			if (rateOfChangeOfScale != null)
			{
				if (Math.round(super.getScale().x * 100) == Math.round(targetScale.x * 100))
					if (Math.round(super.getScale().y * 100) == Math.round(targetScale.y * 100))
						rateOfChangeOfScale = null;

				else
					super.setScale(Vector2f.add(super.getScale(), rateOfChangeOfScale));
			}

			if (rateOfChangeOfRotation != 0.f)
			{
				if (Math.round(super.getRotation()) == Math.round(targetRotationDegrees))
					rateOfChangeOfRotation = 0.f;

				else
					super.setRotation(super.getRotation() + rateOfChangeOfRotation);
			}
		}

		if (rateOfChangeOfPosition == null && rateOfChangeOfScale == null && rateOfChangeOfRotation == 0.f)
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
