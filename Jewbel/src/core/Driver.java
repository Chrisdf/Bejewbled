package core;

import org.jsfml.graphics.ConstView;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.View;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;
import org.jsfml.window.VideoMode;
import org.jsfml.window.Window;
import org.jsfml.window.event.Event;

import ui_elements.Background;
import ui_elements.FPS_Counter;
import ui_elements.GameTitle;

public class Driver {

	private static gameBoard gameBoard;

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
		renderWindow.create(new VideoMode(1280, 720).getDesktopMode(), "Bejewbled");
		renderWindow.setFramerateLimit(60);

		background = new Background(renderWindow);
		gameTitle = new GameTitle(renderWindow);
		gameBoard = new gameBoard(renderWindow);
		gameClock = new Clock();
		fpsCounter = new FPS_Counter(gameClock);
		
		while (renderWindow.isOpen())
		{
			update();
			handleInput();
			drawWindow();
		}
	}

	public static void update() {
		fpsCounter.calcFPS();
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
		renderWindow.draw(fpsCounter);
		renderWindow.display();
	}
}
