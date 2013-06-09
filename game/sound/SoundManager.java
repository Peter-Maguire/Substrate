package game.sound;

import game.Game;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundManager {

	private static final String snd = "/res/snd/";

	private static final String[] sounds = { "select.wav", "enter.wav",

	};

	private AudioInputStream[] cache = new AudioInputStream[sounds.length];
	private Game game;

	public SoundManager(Game game) {
		this.game = game;

	}

	public synchronized void playSound(final String string) {

		if (game.settings.getSetting("Sound") == "OFF")
			return;

		new Thread(new Runnable() { // the wrapper thread is unnecessary, unless
									// it blocks on the Clip finishing, see
									// comments
					@Override
					public void run() {

						Thread thread = Thread.currentThread();

						Clip clip;
						try {
							clip = AudioSystem.getClip();
							AudioInputStream inputStream = AudioSystem
									.getAudioInputStream(Game.class
											.getResourceAsStream(snd + string));
							clip.open(inputStream);
							FloatControl gainControl = (FloatControl) clip
									.getControl(FloatControl.Type.MASTER_GAIN);
							gainControl.setValue((Integer
									.parseInt(game.settings
											.getSetting("Volume")) - 200) / 10);

							clip.start();
						} catch (LineUnavailableException e) {
							game.settings.setSetting("Sound",
									"Error! (Line Unavailable)");
							e.printStackTrace();
						} catch (UnsupportedAudioFileException e) {
							game.settings.setSetting("Sound",
									"Error! (Unsupported Audio File)");
							e.printStackTrace();
						} catch (IOException e) {
							game.settings.setSetting("Sound",
									"Error! (IOException)");
							e.printStackTrace();
						}

						thread.interrupt();
					}
				}).start();

	}

}
