package game.console;

import game.Game;
import game.console.command.Command;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ConsoleWindow extends JFrame implements KeyListener{

	private JScrollPane cScroll;
	private JTextArea console;
	private JTextField cmdbar;
	private JButton cmdSubmit;
	
	private HashMap<String, Command>commandRegistry = new HashMap<String, Command>();
	
	
	public ConsoleWindow()
	{
		this.setTitle(Game.TITLE+" console.");
		this.setVisible(true);
		this.setResizable(true);
		this.setFocusable(true);
		this.addKeyListener(this);

		
		console = new JTextArea();
		console.setEditable(false);
		console.setRows(10);
		console.setColumns(10);
		
		cScroll = new JScrollPane();
		cScroll.getViewport().add(console);
		
		cmdbar = new JTextField();
		
		cmdSubmit = new JButton("Enter");
		
		add(cScroll);
		add(cmdbar, BorderLayout.PAGE_END);
		//add(cmdSubmit);
		validate();
		
	}
	
	public void log(String s)
	{
		console.setText(console.getText()+"\n"+s);
	}
	
	public void command(String[] cmd)
	{
		if(commandRegistry.get(cmd[0]) == null)
		{
			log("Unknown Command '"+cmd[0]+"'!");
		}else
		{
			Command command = commandRegistry.get(cmd[0]);
			command.executeCommand(cmd);
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
	
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		System.out.println("Key released");
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER && cmdbar.getText() != null)
		{
			command(cmdbar.getText().split(" "));
			cmdbar.setText("");
			
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	
}
