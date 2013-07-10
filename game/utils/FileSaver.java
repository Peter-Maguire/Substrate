package game.utils;

import game.Game;
import game.entity.Entity;
import game.entity.SerialEntity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

public class FileSaver {

	
	
	
	public static ByteBuffer loadPNGTexture(String path)
	{
		try{
		ByteBuffer buf = null;
		int tWidth = 0,
			tHeight = 0;
		InputStream in = new FileInputStream(FileSaver.getCleanPath()+"//res//tiles.png");
		PNGDecoder decoder = new PNGDecoder(in);
		
		tWidth = decoder.getWidth();
		tHeight = decoder.getHeight();
		buf = ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getHeight());
		decoder.decode(buf, decoder.getWidth() * 4, Format.RGBA);
		buf.flip();
		in.close();
		return buf;
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * Used for map loading to retrieve entity instances from the map file.
	 * @param entlist The list of serial entities supplied by the map file
	 * @param game The game instance
	 * @return A list of entity instances
	 * @throws ClassNotFoundException If entity list is invalid
	 * @throws InstantiationException If entity list is invalid
	 * @throws IllegalAccessException If entity list is invalid
	 */
	public static ArrayList<Entity> serialToEntity(
			ArrayList<SerialEntity> entlist, Game game)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		System.out.println("Deserializing entity array...");
		System.out.println(entlist.size() + " entities to deserialize.");
		ArrayList<Entity> newlist = new ArrayList<Entity>();
		for (SerialEntity se : entlist) {
			System.out.println("Deserialized entity " + se.relatedEntity);
			Class<?> c = Class.forName(se.relatedEntity);
			Entity dummyentity = (Entity) c.newInstance();
			dummyentity.x = se.x;
			dummyentity.y = se.y;
			dummyentity.game = game;
			newlist.add(dummyentity);
		}
		return newlist;
	}

	/**
	 * Used for map saving to convert entities to saveable versions
	 * @param entlist A list of entities supplied by the Map instance
	 * @return A list of saveable entities
	 */
	public static ArrayList<SerialEntity> entityToSerial(
			ArrayList<Entity> entlist) {
		System.out.println("Serializing entity array...");
		System.out.println(entlist.size() + " entities to serialize.");
		ArrayList<SerialEntity> newlist = new ArrayList<SerialEntity>();
		for (Entity e : entlist) {
			System.out.println("Serialized entity "
					+ e.getClass().getCanonicalName());
			newlist.add(new SerialEntity(e.x, e.y, 0, e.getClass()
					.getCanonicalName()));
		}
		return newlist;
	}

	/**
	 * Used by the crash screen to the stack trace
	 * @param aThrowable The throwable error
	 * @return The stacktrace
	 */
	public static String getStackTrace(Throwable aThrowable) {
		final Writer result = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(result);
		aThrowable.printStackTrace(printWriter);
		return result.toString();
	}

	/**
	 *  Saves all the properties in the hashmap props into a file like this:
	 *  Key: Value
	 * @param props The hashmap to save to a file.
	 * @param path The path to save at.
	 */
	public static void savePropertiesFile(HashMap<String, String> props,
			String path) {
		PrintWriter out;
		try {
			Game.log("Saving property file " + path);
			out = new PrintWriter(path);
			out.println("#" + Game.TITLE + " Property File. ");
			for (int i = 0; i < props.keySet().size(); i++) {

				String property = props.keySet().toArray()[i] + "";
				String value = props.get(property);
				out.println(property + ": " + value);
			}
			out.checkError();
			out.close();
		} catch (Exception e) {
			Game.log("File saving did an uh oh at:");
			e.printStackTrace();
		}

	}

	/**
	 * Used for reading property files
	 * @param path Path to read from
	 * @return Hashmap form of property file
	 */
	public static HashMap<String, String> readPropertiesFile(String path) {
		HashMap<String, String> props = new HashMap<String, String>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(path));
			String line;
			while ((line = br.readLine()) != null) {
				if (!(line.charAt(0) == "#".charAt(0))) {
					String[] properties = line.split(": ");
					props.put(properties[0], properties[1]);
				}
			}
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return props;
	}

	/**
	 * Basic file saver
	 * @param file File to save
	 * @param path Path to save at
	 */
	public static void save(Object file, String path) {
		FileOutputStream fos;
		ObjectOutputStream oos;

		try {
			Game.log("Saving file " + path);

			fos = new FileOutputStream(path);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(file);
		} catch (FileNotFoundException e) {
			new File(path).mkdir();
			save(file, path);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Game.log("Done!");

	}

	/**
	 * Basic file loader
	 * @param path Path to load from
	 * @return Object returned
	 */
	public static Object load(String path) {
		try {
			Game.log("Loading file " + path);
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);
			return ois.readObject();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Gets working directory
	 * @return Working directory
	 */
	public static String getCleanPath() {

		return new File(FileSaver.class.getProtectionDomain().getCodeSource()
				.getLocation().getPath()).getAbsolutePath();
	}

}
