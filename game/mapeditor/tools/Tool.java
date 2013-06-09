package game.mapeditor.tools;

import game.screen.ScreenMapEditor;

public class Tool {

	private String toolName = "Unidentifed tool";
	private int sprite = 0;

	public Tool(String toolName, int sprite) {
		this.toolName = toolName;
		this.sprite = sprite;
	}

	public String getToolName() {
		return toolName;
	}

	public void setToolName(String toolName) {
		this.toolName = toolName;
	}

	public int getSprite() {
		return sprite;
	}

	public void setSprite(int sprite) {
		this.sprite = sprite;
	}

	public void onToolUsed(int x, int y, ScreenMapEditor screen) {

	}
}
