package ui_elements;

import java.nio.file.Paths;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;

public class ScoreCounter implements Drawable {
	
	private int totalScore;
	
	private RectangleShape scoreBackground;
	
	private Text displayedScore;
	
	private Font freeSans;
	
	private RenderWindow window;
	
	private RectangleShape test;
	
	public ScoreCounter(RenderWindow renderWindow){
		
		window = renderWindow;
		freeSans = new Font();
		scoreBackground = new RectangleShape();
		
		try
		{
			freeSans.loadFromFile(Paths.get("Resources/free_mono.ttf"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		Vector2f positioning = new Vector2f(window.getSize().x / 15, window.getSize().y / 8);
		
		displayedScore = new Text(totalScore + "", freeSans, 90);
		displayedScore.setColor(Color.GREEN);
		
		Vector2f displaySize = new Vector2f(displayedScore.getLocalBounds().width, displayedScore.getLocalBounds().height);
		displaySize = Vector2f.mul(displaySize, 1.5f);
		scoreBackground.setSize(displaySize);
		scoreBackground.setFillColor(new Color(Color.CYAN, 150));
		scoreBackground.setOutlineColor(Color.CYAN);
		scoreBackground.setOutlineThickness(4f);
		scoreBackground.setPosition(positioning);
		
		float centerXPosition = scoreBackground.getPosition().x + ((scoreBackground.getGlobalBounds().width - displayedScore.getLocalBounds().width) / 2);
		float centerYPosition = scoreBackground.getPosition().y + ((scoreBackground.getGlobalBounds().height - displayedScore.getLocalBounds().height) / 2);
		displayedScore.setPosition(centerXPosition, centerYPosition);
		
		
		test = new RectangleShape();
		//test.setPosition(displayedScore.getPosition());
		test.setPosition(Vector2f.add(displayedScore.getPosition(), new Vector2f(displayedScore.getLocalBounds().width, displayedScore.getLocalBounds().height)));
		test.setSize(new Vector2f(2,2));
		test.setFillColor(Color.BLACK);
	}

	public void draw(RenderTarget target, RenderStates states) {
		
		scoreBackground.draw(target, states);
		displayedScore.draw(target, states);
		test.draw(target, states);
	}

}
