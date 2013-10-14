package game.triggers;

import java.io.Serializable;

public class SerialTrigger implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5131993755835029121L;
	public String relatedTrigger = "game.triggers.Trigger";
	public int x = 0, y = 0, health = 20;

	public SerialTrigger(int x, int y,SerialTrigger link, String relatedTrigger) {
		this.x = x;
		this.y = y;
		this.relatedTrigger = relatedTrigger;
	}

}
