package core;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.View;
import org.jsfml.system.Clock;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

import ui_elements.Background;
import ui_elements.FPS_Counter;
import ui_elements.GameTitle;
import ui_elements.ScoreCounter;

public class Driver {

	private static GameBoard gameBoard;

	private static RenderWindow renderWindow;

	private static Clock gameClock;

	private static FPS_Counter fpsCounter;

	private static Background background;

	private static GameTitle gameTitle;
	
	private static ScoreCounter gameScore;


	public static void main(String args[]) {
		gameSetup();
	}

	public static void gameSetup() {
		
		renderWindow = new RenderWindow();
		renderWindow.create(VideoMode.getDesktopMode(), "Bejewbled");
		renderWindow.setFramerateLimit(60);

		background = new Background(renderWindow);
		gameTitle = new GameTitle(renderWindow);
		gameBoard = new GameBoard(renderWindow);
		gameScore = new ScoreCounter(renderWindow);
		gameClock = new Clock();
		fpsCounter = new FPS_Counter(gameClock);

		while (renderWindow.isOpen())
		{
			handleInput();

			update();

			fpsCounter.calcFPS();

			drawWindow();
		}
	}

	public static void update() {

		gameBoard.update();

	}

	public static void handleInput() {
		for (Event event : renderWindow.pollEvents())
		{
			if (event.type == Event.Type.CLOSED)
				renderWindow.close();

			else if (event.type == Event.Type.MOUSE_BUTTON_PRESSED)
				gameBoard.findJewbelToSelect(event.asMouseEvent().position);
		}
	}

	public static void drawWindow() {
		renderWindow.clear();
		renderWindow.draw(background);
		renderWindow.draw(gameTitle);
		renderWindow.draw(gameBoard);
		renderWindow.draw(gameScore);
		renderWindow.draw(fpsCounter);
		renderWindow.display();
	}
}
