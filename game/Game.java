package game;

import game.console.ConsoleWindow;
import game.console.ThreadConsole;
import game.screen.Dialogue;
import game.screen.Screen;
import game.screen.ScreenLoading;
import game.screen.ScreenMainMenu;
import game.sound.SoundManager;
import game.tile.Tile;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.nio.file.FileSystem;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends Canvas implements KeyListener, MouseListener,
		FocusListener {

	private static final long serialVersionUID = 1L;
	public static String TITLE = "Substrate";
	public static int WIDTH = 800, HEIGHT = 600, SCALE = 2, SIZE = SCALE *  32;
	public HashMap<String, String> SETTINGS = new HashMap<String, String>();

	public BufferStrategy strategy;

	public SpriteSheet sheet;
	private Font font;
	public Graphics2D g;
	public Options settings;
	public SoundManager soundman;
	private Screen currentScreen;
	private Dialogue currentDialogue;
	private static ConsoleWindow console = null;
	private Tile tileinit;

	public Controls controls = new Controls();
	private int tps = 0, fps = 0;

	public boolean gameRunning = true;

	public Game() {
  
		setSize(200,200);
		setBounds(0, 0, 800, 600);
		setIgnoreRepaint(true);
		addKeyListener(this);
		addFocusListener(this);
		addMouseListener(this);
		

	}

	public static void main(String[] args) {
		Game game = new Game();
		JFrame container = new JFrame(TITLE);
		container.setVisible(true);
		container.setFocusable(true);
		container.add(game, BorderLayout.CENTER);
		container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container.pack();
		container.setResizable(true);
		container.setVisible(true);
		game.start();
		

	}
	


	public synchronized void start() {

		createBufferStrategy(2);
		strategy = getBufferStrategy();

		try {
			gameLoop();
		} catch (Exception e) {
			e.printStackTrace();	
			String[] crashDump = {currentScreen.toString(), Integer.toString(fps), Integer.toString(tps), e.getMessage()};
			setScreen(new ScreenLoading(WIDTH, HEIGHT, null, crashDump));
			render();
			
		}
		requestFocus();

	}

	public synchronized void stop() {
		gameRunning = false;
		System.exit(ABORT);
	}
	public void shutdown()
	{
		log("Saving files...");
		FileSaver.save(SETTINGS, "settings.dat");
		FileSaver.save(controls.keyMap, "keymap.dat");
		this.strategy.dispose();
		g.clearRect(0, 0, Game.WIDTH, Game.HEIGHT);
		log("Copyright UnacceptableUse 2013");
		log("Shutting down game, peace.");
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e){}
		System.exit(0);
		
	}

	public Font getFontRenderer() {
		return font;
	}

	
	@SuppressWarnings("unchecked")
	public void init() {
		/*ThreadConsole tc = new ThreadConsole();
		Thread consoleThread = new Thread(tc);
		consoleThread.run();
		console = tc.console;*/
		log("Loading...");
		
		File f = new File("settings.dat");
		if(!f.exists())
		{  
			log("Settings file does not exist!");
			SETTINGS.put("Sound", "OFF");
			SETTINGS.put("Debug", "ON");
			SETTINGS.put("Music", "OFF");
			SETTINGS.put("Volume", "100");
			SETTINGS.put("Cheats", "OFF");
			SETTINGS.put("HasDoneIntro", "OFF");
			FileSaver.save(SETTINGS, "settings.dat");
		}else
		{
			log("Found settings.dat, loading...");
			SETTINGS = (HashMap<String, String>) FileSaver.load("settings.dat");
		}
		settings = new Options(SETTINGS);


		tileinit = new Tile();
		

		
		
		try {
			sheet = new SpriteSheet(ImageIO.read(Game.class
					.getResource("/res/icons.png")));
			font = new Font(ImageIO.read(Game.class
					.getResource("/res/font.png")), strategy.getDrawGraphics(),
					this);

		} catch (Exception e) {
			log("Sheet loading failed!");
			throw new RuntimeException("Sheet loading failed!");
		}

		


		
		
		
		f = new File("keymap.dat");
		if(!f.exists())
		{  
			FileSaver.save(controls.keyMap, "keymap.dat");
		}else
		{
			controls.keyMap = (HashMap<Integer, Integer>) FileSaver.load("keymap.dat");
		}

		soundman = new SoundManager(this);

		setScreen(new ScreenMainMenu(WIDTH, HEIGHT, sheet));
		log("Loaded!");
	}
	
	public static void log(String s)
	{
		if(console != null)
		{
			console.log(s);
		}else
		{
			System.out.println(s);
		}
	}

	public void gameLoop() {
		long lastTime = System.nanoTime();
		double unprocessed = 0;
		double nsPerTick = 1000000000.0 / 60.0;

		int ticks = 0;
		int frames = 0;
		long lastTimer1 = System.currentTimeMillis();

		init();

		while (gameRunning) {
			long now = System.nanoTime();
			unprocessed += (now - lastTime) / nsPerTick;
			lastTime = now;
			while (unprocessed >= 1) {
				ticks++;
				tick();
				unprocessed -= 1;

			}
			{
				frames++;
				render();
			}

			if (System.currentTimeMillis() - lastTimer1 > 1000) {
				lastTimer1 += 1000;

				fps = frames;
				tps = ticks;
				ticks = 0;
				frames = 0;

			}

		}
		System.err.println("Game stopped :(");
		shutdown();
	}

	public void tick() {
		currentScreen.tick();

	}

	public void setScreen(Screen screen) {
		currentScreen = screen;
		currentScreen.init(this);
	}

	public void render() {
		
		g = (Graphics2D) strategy.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH * 2, HEIGHT * 2);


		currentScreen.render(g);

		if (settings.getSetting("Debug") == "ON")
			font.drawString(tps + " Ticks, " + fps + " fps", 1, 1, 2);
		if(settings.getSetting("Cheats") == "ON")
		{
			font.drawString("Cheat Menu:", 660, 10, 1);
			font.drawString("x - xxxxxx", 655, 25, 1);
		}

		g.dispose();
		strategy.show();
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		currentScreen.focusGained(arg0);

	}

	@Override
	public void focusLost(FocusEvent arg0) {
		currentScreen.focusLost(arg0);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
	
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		currentScreen.mousePressed(e);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		currentScreen.mouseReleased(e);

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		currentScreen.keyPressed(arg0);

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		currentScreen.keyReleased(arg0);

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
