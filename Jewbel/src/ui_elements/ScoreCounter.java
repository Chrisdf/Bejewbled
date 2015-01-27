package ui_elements;

import java.nio.file.Paths;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;

public class ScoreCounter implements Drawable {
	
	private int totalScore;
	
	private RectangleShape scoreBackground;
	
	private Text displayedScore;
	
	private Font freeSans;
	
	public ScoreCounter(){
		
		scoreBackground = new RectangleShape();
		freeSans = new Font();
		displayedScore = new Text(totalScore + "DDDDDDDDDDD", freeSans, 50);
		
		
		try
		{
			freeSans.loadFromFile(Paths.get("Resources/freeSans.otf"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		Vector2f displaySize = new Vector2f(displayedScore.getLocalBounds().width, displayedScore.getLocalBounds().height);
		scoreBackground.setSize(displaySize);
		scoreBackground.setFillColor(new Color(Color.CYAN, 150));
		scoreBackground.setOutlineColor(Color.CYAN);
		
		displayedScore = new Text(totalScore + "", freeSans, 50);
		displayedScore.setColor(Color.YELLOW);
		displayedScore.setOrigin(displayedScore.getGlobalBounds().width/2, displayedScore.getGlobalBounds().height/3);
		Vector2f scoreBackgroundBounds = new Vector2f(scoreBackground.getGlobalBounds().width/2, scoreBackground.getGlobalBounds().height/3);
		displayedScore.setPosition(scoreBackgroundBounds);
		
	}

	public void draw(RenderTarget target, RenderStates states) {
		
		scoreBackground.draw(target, states);
		displayedScore.draw(target, states);
	}

}
