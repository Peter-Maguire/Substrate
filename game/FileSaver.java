package game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileSaver {

	
	public static void save(Object file, String path)
	{
		FileOutputStream fos;
		ObjectOutputStream oos;
	
		try{
			Game.log("Saving file "+path);
			new File(path).mkdir();
			fos = new FileOutputStream(path);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(file);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	
		Game.log("Done!");
		
	}
	
	public static Object load(String path)
	{
		try {
			Game.log("Loading file "+path);
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);
			return ois.readObject();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	
	}
	public static String getCleanPath() {
	    ClassLoader classLoader = FileSaver.class.getClassLoader();
	    File classpathRoot = new File(classLoader.getResource("").getPath());

	    return classpathRoot.getPath();
	}
		

}
