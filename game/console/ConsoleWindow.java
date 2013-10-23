package game.console;

import game.Game;
import game.console.command.Command;
import game.console.command.CommandAddTile;
import game.console.command.CommandCrash;
import game.console.command.CommandFontInit;
import game.console.command.CommandHelp;
import game.console.command.CommandReinit;
import game.console.command.CommandSetSetting;
import game.console.command.CommandWave;

import java.awt.BorderLayout;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;



public class ConsoleWindow extends JFrame implements KeyListener {

	private JScrollPane cScroll;
	private JTextPane console;
	private JTextField cmdbar;
	private JButton cmdSubmit;
	private Game game;

	private HashMap<String, Command> commandRegistry = new HashMap<String, Command>();

	public ConsoleWindow(Game game) {
		this.game = game;
		init();
		this.setTitle(Game.TITLE + " console.");
		this.setVisible(true);
		this.setResizable(true);
		this.setFocusable(true);

		console = new JTextPane();
		console.setEditable(false);
		setSize(300, 400);

		cScroll = new JScrollPane();
		cScroll.getViewport().add(console);

		cmdbar = new JTextField();
		cmdbar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					JTextField textField = (JTextField) e.getSource();
					String text = ((JTextField) (e.getSource())).getText();
					command(text.split(" "));
					textField.setText("");
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});

		cmdSubmit = new JButton("Enter");

		add(cScroll);
		add(cmdbar, BorderLayout.PAGE_END);
		// add(cmdSubmit);
		validate();

	}

	private void init() {
		registerCommand("help", new CommandHelp(game));
		registerCommand("tile", new CommandAddTile(game));
		registerCommand("setting", new CommandSetSetting(game));
		registerCommand("reinit", new CommandReinit(game));
		registerCommand("crash", new CommandCrash(game));
		registerCommand("wave", new CommandWave(game));
		registerCommand("font", new CommandFontInit(game));
	}

	public void registerCommand(String var, Command command) {
		commandRegistry.put(var, command);
	}

	public void log(String s) {
		console.setText(console.getText() + "\n" + s.replace("%red%", "[CRITICAL]"));
	}

	public void command(String[] cmd) {
		if (commandRegistry.get(cmd[0]) == null) {
			log("Unknown Command '" + cmd[0] + "'!");
		} else {
			Command command = commandRegistry.get(cmd[0]);
			command.executeCommand(cmd);
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
