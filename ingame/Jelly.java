package ingame;

import java.awt.*;


public class Jelly {
	private Image image;
	

	private int x;
	private int y;
	private int width;
	private int height;
	

	private int alpha;
	

	private int score;


	public Jelly() {
	}

	public Jelly(Image image, int x, int y, int width, int height, int alpha, int score) {
		this.image = image;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.alpha = alpha;
		this.score = score;
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

	public int getAlpha() {
		return alpha;
	}

	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Jelly{" +
				"image=" + image +
				", x=" + x +
				", y=" + y +
				", width=" + width +
				", height=" + height +
				", alpha=" + alpha +
				", score=" + score +
				'}';
	}
}

