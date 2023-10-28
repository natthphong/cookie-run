package ingame;

import javax.swing.*;


public class CookieImg {
	private ImageIcon cookieIc;
	private ImageIcon jumpIc;
	private ImageIcon doubleJumpIc;
	private ImageIcon fallIc;
	private ImageIcon slideIc;
	private ImageIcon hitIc;


	public CookieImg() {
	}

	public CookieImg(ImageIcon cookieIc, ImageIcon jumpIc, ImageIcon doubleJumpIc, ImageIcon fallIc, ImageIcon slideIc, ImageIcon hitIc) {
		this.cookieIc = cookieIc;
		this.jumpIc = jumpIc;
		this.doubleJumpIc = doubleJumpIc;
		this.fallIc = fallIc;
		this.slideIc = slideIc;
		this.hitIc = hitIc;
	}

	public ImageIcon getCookieIc() {
		return cookieIc;
	}

	public void setCookieIc(ImageIcon cookieIc) {
		this.cookieIc = cookieIc;
	}

	public ImageIcon getJumpIc() {
		return jumpIc;
	}

	public void setJumpIc(ImageIcon jumpIc) {
		this.jumpIc = jumpIc;
	}

	public ImageIcon getDoubleJumpIc() {
		return doubleJumpIc;
	}

	public void setDoubleJumpIc(ImageIcon doubleJumpIc) {
		this.doubleJumpIc = doubleJumpIc;
	}

	public ImageIcon getFallIc() {
		return fallIc;
	}

	public void setFallIc(ImageIcon fallIc) {
		this.fallIc = fallIc;
	}

	public ImageIcon getSlideIc() {
		return slideIc;
	}

	public void setSlideIc(ImageIcon slideIc) {
		this.slideIc = slideIc;
	}

	public ImageIcon getHitIc() {
		return hitIc;
	}

	public void setHitIc(ImageIcon hitIc) {
		this.hitIc = hitIc;
	}

	@Override
	public String toString() {
		return "CookieImg{" +
				"cookieIc=" + cookieIc +
				", jumpIc=" + jumpIc +
				", doubleJumpIc=" + doubleJumpIc +
				", fallIc=" + fallIc +
				", slideIc=" + slideIc +
				", hitIc=" + hitIc +
				'}';
	}
}
