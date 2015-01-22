package core;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Clock;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

import ui_elements.Background;
import ui_elements.FPS_Counter;
import ui_elements.GameTitle;

public class Driver {

	private static GameBoard gameBoard;

	private static RenderWindow renderWindow;

	private static Clock gameClock;

	private static FPS_Counter fpsCounter;

	private static Background background;

	private static GameTitle gameTitle;


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
		gameClock = new Clock();
		fpsCounter = new FPS_Counter();

		final float UPDATES_PER_SECOND = 1 / 20f; //Seconds between each update
		float lag = 0f; //How far the game is behind real time
		
		while (renderWindow.isOpen())
		{
			float elapsed = gameClock.restart().asSeconds();
			lag += elapsed;
			fpsCounter.addElapsed(elapsed);

			handleInput();

			/* Update until the game is back up with real time*/
			while (lag >= UPDATES_PER_SECOND)
			{
				update();
				lag -= UPDATES_PER_SECOND; //Decrease amount of lag time behind real time
			}
			
			drawWindow(lag / UPDATES_PER_SECOND);
			fpsCounter.calcFPS();
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

	public static void drawWindow(float extrapolation) {
		renderWindow.clear();
		renderWindow.draw(background);
		renderWindow.draw(gameTitle);
		renderWindow.draw(gameBoard);
		renderWindow.draw(fpsCounter);
		renderWindow.display();
	}
}
