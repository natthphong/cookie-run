package ingame;

import java.awt.*;


public class Tacle {
	
	private Image image;
	

	private int x;
	private int y;
	private int width;
	private int height;
	

	private int state;

	public Tacle() {
	}

	public Tacle(Image image, int x, int y, int width, int height, int state) {
		this.image = image;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.state = state;
	}


	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Tacle{" +
				"image=" + image +
				", x=" + x +
				", y=" + y +
				", width=" + width +
				", height=" + height +
				", state=" + state +
				'}';
	}
}

