package core;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Clock;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

import ui_elements.Background;
import ui_elements.FPS_Counter;
import ui_elements.GameTitle;
import ui_elements.ScoreCounter;
import ui_elements.TurnsLeft;

public class Driver {

	private static GameBoard gameBoard;

	private static RenderWindow renderWindow;

	private static Clock gameClock;

	private static FPS_Counter fpsCounter;

	private static Background background;

	private static GameTitle gameTitle;

	private static ScoreCounter gameScore;

	private static TurnsLeft turnsLeft;


	public static void main(String args[]) {
		gameSetup();
	}

	public static void gameSetup() {

		renderWindow = new RenderWindow();
		renderWindow.create(VideoMode.getDesktopMode(), "Bejewbled");
		renderWindow.setFramerateLimit(60);

		background = new Background(renderWindow);
		gameTitle = new GameTitle(renderWindow);
		turnsLeft = new TurnsLeft(15, gameTitle);
		gameScore = new ScoreCounter(renderWindow);
		gameBoard = new GameBoard(renderWindow, gameScore, turnsLeft);
		gameClock = new Clock();
		fpsCounter = new FPS_Counter(gameClock);

		while (renderWindow.isOpen())
		{
			handleInput();

			update();

			drawWindow();
		}
	}

	public static void update() {

		fpsCounter.calcFPS();
		
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
		renderWindow.draw(turnsLeft);
		renderWindow.draw(gameBoard);
		renderWindow.draw(gameScore);
		renderWindow.draw(fpsCounter);
		renderWindow.display();
	}
}
