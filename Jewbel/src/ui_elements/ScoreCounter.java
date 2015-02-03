package ui_elements;

import java.nio.file.Paths;
import java.util.ArrayList;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;

import util.AnimatedText;

public class ScoreCounter implements Drawable {

	private int totalScore;

	private RectangleShape rightScoreBackground;

	private Text rightDisplayedScore;

	private RectangleShape leftScoreBackground;

	private Text leftDisplayedScore;

	private int fontSize;

	private Font freeSans;

	private RenderWindow window;

	private float borderSize;

	private Vector2f leftPosition;

	private Vector2f rightPosition;

	private ArrayList<AnimatedText> scoreToAdd;

	public ScoreCounter(RenderWindow renderWindow) {

		window = renderWindow;
		freeSans = new Font();
		fontSize = 60;
		rightScoreBackground = new RectangleShape();
		leftScoreBackground = new RectangleShape();
		scoreToAdd = new ArrayList<AnimatedText>();

		try
		{
			freeSans.loadFromFile(Paths.get("Resources/Comic.ttf"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		borderSize = 4f;

		leftPosition = new Vector2f(window.getSize().x / 6.5f, window.getSize().y / 5);
		rightPosition = new Vector2f(window.getSize().x - window.getSize().x / 6.5f, window.getSize().y / 5);


		rightDisplayedScore = new Text(totalScore + "", freeSans, fontSize);
		FloatRect rightBounds = rightDisplayedScore.getLocalBounds();
		rightDisplayedScore.setOrigin(rightBounds.left + rightBounds.width / 2.0f, rightBounds.top + rightBounds.height
				/ 2.0f);
		rightDisplayedScore.setColor(Color.BLACK);

		Vector2f displaySize =
				new Vector2f(rightDisplayedScore.getLocalBounds().width, rightDisplayedScore.getLocalBounds().height);
		displaySize = Vector2f.mul(displaySize, 1.5f);
		rightScoreBackground.setSize(displaySize);
		rightScoreBackground.setFillColor(new Color(Color.CYAN, 150));
		rightScoreBackground.setOutlineColor(Color.BLACK);
		rightScoreBackground.setOutlineThickness(borderSize);
		FloatRect rightBackBounds = rightScoreBackground.getLocalBounds();
		rightScoreBackground.setOrigin(rightBackBounds.left + rightBackBounds.width / 2.0f, rightBackBounds.top
				+ rightBackBounds.height / 2.0f);

		rightScoreBackground.setPosition(rightPosition);
		rightDisplayedScore.setPosition(rightPosition);



		leftDisplayedScore = new Text(totalScore + "", freeSans, fontSize);
		FloatRect leftBounds = leftDisplayedScore.getLocalBounds();
		leftDisplayedScore.setOrigin(leftBounds.left + leftBounds.width / 2.0f, leftBounds.top + leftBounds.height / 2.0f);
		leftDisplayedScore.setColor(Color.BLACK);

		leftScoreBackground.setSize(displaySize);
		leftScoreBackground.setFillColor(new Color(Color.CYAN, 150));
		leftScoreBackground.setOutlineColor(Color.BLACK);
		leftScoreBackground.setOutlineThickness(borderSize);
		FloatRect leftBackBounds = leftScoreBackground.getLocalBounds();
		leftScoreBackground.setOrigin(leftBackBounds.left + leftBackBounds.width / 2.0f, leftBackBounds.top
				+ leftBackBounds.height / 2.0f);

		leftScoreBackground.setPosition(leftPosition);
		leftDisplayedScore.setPosition(leftPosition);
	}

	public void update() {


		for (int i = 0; i < scoreToAdd.size(); i++ )
		{
			if(!scoreToAdd.get(i).getIfAnimated())
			{
				int score = Integer.parseInt(scoreToAdd.get(i).getString());
				scoreToAdd.remove(i);
				i--;
				totalScore += score;
			}
		}
		
		for(AnimatedText selected: scoreToAdd)
			selected.animate();


		//Update the string and center origin on the new string
		rightDisplayedScore.setString(totalScore + "");
		FloatRect rightBounds = rightDisplayedScore.getLocalBounds();
		rightDisplayedScore.setOrigin(rightBounds.left + rightBounds.width / 2.0f, rightBounds.top + rightBounds.height
				/ 2.0f);

		//Create the background size as 1.5 times the string size
		Vector2f displaySize =
				new Vector2f(rightDisplayedScore.getLocalBounds().width, rightDisplayedScore.getLocalBounds().height);
		displaySize = Vector2f.mul(displaySize, 1.5f);

		//Set the background size and center background origin on rectangle
		rightScoreBackground.setSize(displaySize);
		FloatRect rightBackBounds = rightScoreBackground.getLocalBounds();
		rightScoreBackground.setOrigin(rightBackBounds.left + rightBackBounds.width / 2.0f, rightBackBounds.top
				+ rightBackBounds.height / 2.0f);

		//Set the now centered origins of both text and rectangle at the desired position
		rightScoreBackground.setPosition(rightPosition);
		rightDisplayedScore.setPosition(rightPosition);



		//Do the above process for the left side
		leftDisplayedScore.setString(totalScore + "");
		FloatRect leftBounds = leftDisplayedScore.getLocalBounds();
		leftDisplayedScore.setOrigin(leftBounds.left + leftBounds.width / 2.0f, leftBounds.top + leftBounds.height / 2.0f);

		leftScoreBackground.setSize(displaySize);
		FloatRect leftBackBounds = leftScoreBackground.getLocalBounds();
		leftScoreBackground.setOrigin(leftBackBounds.left + leftBackBounds.width / 2.0f, leftBackBounds.top
				+ leftBackBounds.height / 2.0f);

		leftScoreBackground.setPosition(leftPosition);
		leftDisplayedScore.setPosition(leftPosition);
	}



	public void draw(RenderTarget target, RenderStates states) {
		
		for(AnimatedText selected: scoreToAdd)
			selected.draw(target, states);

		rightScoreBackground.draw(target, states);
		rightDisplayedScore.draw(target, states);

		leftScoreBackground.draw(target, states);
		leftDisplayedScore.draw(target, states);
	}

	public void add(int totalMatching) {
		
		Vector2f actualLeftPosition = Vector2f.sub(leftDisplayedScore.getPosition(), new Vector2f(leftDisplayedScore.getLocalBounds().width/2, 0));
		Vector2f actualRightPosition = Vector2f.sub(rightDisplayedScore.getPosition(), new Vector2f(rightDisplayedScore.getLocalBounds().width/2, 0));
		
		
		AnimatedText leftAddToScore = new AnimatedText(totalMatching * 10 + "", freeSans, fontSize);
		leftAddToScore.setColor(Color.BLACK);
		leftAddToScore.setPosition(new Vector2f(actualLeftPosition.x, window.getSize().y + 200));

		AnimatedText rightAddToScore = new AnimatedText(totalMatching * 10 + "", freeSans, fontSize);
		rightAddToScore.setColor(Color.BLACK);
		rightAddToScore.setPosition(new Vector2f(actualRightPosition.x, window.getSize().y + 200));

		leftAddToScore.slideToPosition(new Vector2f(actualLeftPosition.x, leftDisplayedScore.getPosition().y), 50f);
		rightAddToScore.slideToPosition(new Vector2f(actualRightPosition.x, rightDisplayedScore.getPosition().y), 50f);

		scoreToAdd.add(leftAddToScore);
		scoreToAdd.add(rightAddToScore);
	}

}
