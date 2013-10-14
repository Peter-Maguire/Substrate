package game.entity;

import java.io.Serializable;

public class SerialEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5131993755835029121L;
	public String relatedEntity = "game.entity.Entity";
	public int x = 0, y = 0, health = 20;

	public SerialEntity(int x, int y, int health, String relatedEntity) {
		this.x = x;
		this.y = y;
		this.health = health;
		this.relatedEntity = relatedEntity;
	}

}
