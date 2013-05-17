package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;

public class FileSaver {

	
	public static void savePropertiesFile(HashMap<String, String> props, String path)
	{
		PrintWriter out;
		try
		{
			Game.log("Saving property file "+path);
			 out = new PrintWriter(path);
			 out.println("#"+Game.TITLE+" Property File. ");
				for (int i = 0; i < props.keySet().size(); i++) {
			
					String property =  props.keySet().toArray()[i]+"";
					String value = props.get(property);
					out.println(property+": "+value);
				}
				out.checkError();
				out.close();
		}catch(Exception e)
		{
			Game.log("File saving did an uh oh at:");
			e.printStackTrace();
		}
		
	}
	
	public static HashMap<String, String> readPropertiesFile(String path)
	{
		HashMap<String, String> props = new HashMap<String, String>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(path));
			String line;
			while ((line = br.readLine()) != null) {
			  if(!line.startsWith("#"))
			  {	  
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
	
	public static void save(Object file, String path)
	{
		FileOutputStream fos;
		ObjectOutputStream oos;
	
		try{
			Game.log("Saving file "+path);
			
			fos = new FileOutputStream(path);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(file);
		}
		catch(FileNotFoundException e)
		{
			new File(path).mkdir();
			save(file, path);
		}
		catch(Exception e)
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
