package ui_elements;

import java.nio.file.Paths;

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

public class ScoreCounter implements Drawable {
	
	private int totalScore;
	
	private RectangleShape rightScoreBackground;
	
	private Text rightDisplayedScore;
	
	private RectangleShape leftScoreBackground;
	
	private Text leftDisplayedScore;
	
	private Font freeSans;
	
	private RenderWindow window;
	
	private float borderSize;
	
	private Vector2f leftPositioning;
	
	private Vector2f rightPositioning;
	
	public ScoreCounter(RenderWindow renderWindow){
		
		window = renderWindow;
		freeSans = new Font();
		rightScoreBackground = new RectangleShape();
		
		try
		{
			freeSans.loadFromFile(Paths.get("Resources/free_mono.ttf"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		borderSize = 4f;
		
		leftPositioning = new Vector2f(window.getSize().x / 15, window.getSize().y / 8);
		rightPositioning = new Vector2f(window.getSize().x - window.getSize().x / 15, window.getSize().y / 8);
		
		
		rightDisplayedScore = new Text(totalScore + "", freeSans, 120);
		FloatRect bounds = rightDisplayedScore.getLocalBounds();
		rightDisplayedScore.setOrigin(bounds.left + bounds.width / 2.0f, bounds.top + bounds.height / 2.0f);
		rightDisplayedScore.setColor(Color.BLACK);
		
		Vector2f displaySize = new Vector2f(rightDisplayedScore.getLocalBounds().width, rightDisplayedScore.getLocalBounds().height);
		displaySize = Vector2f.mul(displaySize, 1.5f);
		rightScoreBackground.setSize(displaySize);
		rightScoreBackground.setFillColor(new Color(Color.CYAN, 150));
		rightScoreBackground.setOutlineColor(Color.CYAN);
		rightScoreBackground.setOutlineThickness(borderSize);
		FloatRect backBounds = rightScoreBackground.getLocalBounds();
		rightScoreBackground.setOrigin(backBounds.left + backBounds.width / 2.0f, backBounds.top + backBounds.height / 2.0f);
		
		
		rightScoreBackground.setPosition(rightPositioning);
		rightDisplayedScore.setPosition(rightPositioning);
		
		leftScoreBackground = rightScoreBackground;
		leftDisplayedScore = rightDisplayedScore;
		
		leftScoreBackground = rightScoreBackground;
		leftScoreBackground.setPosition(rightPositioning);
		
		
		
		
		
	}

	public void draw(RenderTarget target, RenderStates states) {
		
		rightScoreBackground.draw(target, states);
		rightDisplayedScore.draw(target, states);
		
		leftScoreBackground.draw(target, states);
		leftDisplayedScore.draw(target, states);
	}

}
