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
				if (super.getPosition() == targetPosition)
					rateOfChangeOfPosition = null;

				else
					super.setPosition(Vector2f.add(super.getPosition(), rateOfChangeOfPosition));
			}

			if (rateOfChangeOfScale != null)
			{
				if (super.getScale() == targetScale)
					rateOfChangeOfScale = null;

				else
					super.setScale(Vector2f.add(super.getScale(), rateOfChangeOfScale));
			}

			if (rateOfChangeOfRotation != 0.f)
			{
				if (super.getRotation() == targetRotationDegrees)
					rateOfChangeOfRotation = 0.f;

				else
					super.setRotation(super.getRotation() + rateOfChangeOfRotation);
			}
		}

		if (rateOfChangeOfPosition == null && rateOfChangeOfScale == null && rateOfChangeOfRotation == 0.f)
			setAnimated(false);
	}

}
