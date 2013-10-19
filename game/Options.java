package game;

import java.util.HashMap;

public class Options {

	private HashMap<String, String> settings = new HashMap<String, String>();

	public Options(HashMap<String, String> settings) {
		this.settings = settings;

	}

	public String getSetting(String setting) {
		
		if(settings.get(setting) != null)
		return settings.get(setting);
		else{
		Game.log("[OPTIONS] CRITICAL ERROR! Could not retreive setting "+setting+"!");
		setSetting(setting, "OFF");
		return "OFF";
		}
		
	}

	public void setSetting(String setting, String value) {
		settings.put(setting, value);
	}

	public HashMap<String, String> getSettings() {
		return settings;
	}
}
