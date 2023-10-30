package ingame;

import java.awt.*;


public class Cookie {
	
	private Image image;
	

	private int x = 160;
	private int y = 0;
	private int width = 80;
	private int height = 120;
	

	private int alpha = 255;
	

	private int health = 1000;
	

	private int big = 0;
	private int fast = 0;
	private int countJump = 0;
	private boolean invincible = false;
	private boolean fall = false;
	private boolean jump = false;

	private int reset = 2;

	public int getReset() {
		return reset;
	}

	public void setReset(int reset) {
		this.reset = reset;
	}

	public Cookie(Image image){
		this.image = image;
	}


	public Cookie() {
	}

	public Cookie(Image image, int x, int y, int width, int height, int alpha, int health, int big, int fast, int countJump, boolean invincible, boolean fall, boolean jump) {
		this.image = image;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.alpha = alpha;
		this.health = health;
		this.big = big;
		this.fast = fast;
		this.countJump = countJump;
		this.invincible = invincible;
		this.fall = fall;
		this.jump = jump;
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

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getBig() {
		return big;
	}

	public void setBig(int big) {
		this.big = big;
	}

	public int getFast() {
		return fast;
	}

	public void setFast(int fast) {
		this.fast = fast;
	}

	public int getCountJump() {
		return countJump;
	}

	public void setCountJump(int countJump) {
		this.countJump = countJump;
	}

	public boolean isInvincible() {
		return invincible;
	}

	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
	}

	public boolean isFall() {
		return fall;
	}

	public void setFall(boolean fall) {
		this.fall = fall;
	}

	public boolean isJump() {
		return jump;
	}

	public void setJump(boolean jump) {
		this.jump = jump;
	}

	@Override
	public String toString() {
		return "Cookie{" +
				"image=" + image +
				", x=" + x +
				", y=" + y +
				", width=" + width +
				", height=" + height +
				", alpha=" + alpha +
				", health=" + health +
				", big=" + big +
				", fast=" + fast +
				", countJump=" + countJump +
				", invincible=" + invincible +
				", fall=" + fall +
				", jump=" + jump +
				'}';
	}
}

