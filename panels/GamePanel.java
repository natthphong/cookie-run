package panels;

import ingame.*;
import main.Main;
import util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {


	private ImageIcon cookieIc;
	private ImageIcon jumpIc;
	private ImageIcon doubleJumpIc;
	private ImageIcon fallIc;
	private ImageIcon slideIc;
	private ImageIcon hitIc;

	private ImageIcon backIc;
	private ImageIcon secondBackIc;

	private ImageIcon backIc2;
	private ImageIcon secondBackIc2;

	private ImageIcon backIc3;
	private ImageIcon secondBackIc3;

	private ImageIcon backIc4;
	private ImageIcon secondBackIc4;

	private ImageIcon jelly1Ic;
	private ImageIcon jelly2Ic;
	private ImageIcon jelly3Ic;
	private ImageIcon jellyHPIc;

	private ImageIcon jellyEffectIc;

	private ImageIcon field1Ic;
	private ImageIcon field2Ic;

	private ImageIcon tacle10Ic;
	private ImageIcon tacle20Ic;
	private ImageIcon tacle30Ic;
	private ImageIcon tacle40Ic;


	private ImageIcon lifeBar;

	private ImageIcon redBg;

	private ImageIcon jumpButtonIconUp;
	private ImageIcon jumpButtonIconDown;

	private ImageIcon slideIconUp;
	private ImageIcon slideIconDown;

	Image jumpBtn;
	Image slideBtn;


	private List<Jelly> jellyList;

	private List<Field> fieldList;

	private List<Tacle> tacleList;

	private List<Integer> mapLengthList;

	private int mapLength = 0;

	private int runPage = 0;

	private int runStage = 1;

	private int resultScore = 0;

	private int gameSpeed = 5;

	private int nowField = 2000;

	private JButton escButton;

	private boolean fadeOn = false;

	private boolean escKeyOn = false;

	private boolean downKeyOn = false;

	private boolean redScreen = false;

	int face;
	int foot;


	private int[] sizeArr;
	private int[][] colorArr;

	private Image buffImage;
	private Graphics buffg;

	private AlphaComposite alphaComposite;

	Cookie c1;

	Back b11;
	Back b12;

	Back b21;
	Back b22;

	Color backFade;


	MapObjectImg mo1;
	MapObjectImg mo2;
	MapObjectImg mo3;
	MapObjectImg mo4;


	JFrame superFrame;
	CardLayout cl;
	Main main;


	public GamePanel(JFrame superFrame, CardLayout cl, Object o) {

		this.superFrame = superFrame;
		this.cl = cl;
		this.main = (Main) o;

		escButton = new JButton("back");
		escButton.setBounds(350, 200, 100, 30);
		escButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				remove(escButton);
				escKeyOn = false;
			}
		});

	}


	public void gameSet(CookieImg ci) {

		setFocusable(true);

		initCookieImg(ci);

		initObject();

		initListener();

		runRepaint();
	}


	public void gameStart() {

		mapMove();

		fall();

	}


	@Override
	protected void paintComponent(Graphics g) {

	

	
		if (buffg == null) {
			buffImage = createImage(this.getWidth(), this.getHeight());
			if (buffImage == null) {
				System.out.println("���� ���۸��� ���� ��ũ�� ���� ����");
			} else {
				buffg = buffImage.getGraphics();
			}
		}

	
		Graphics2D g2 = (Graphics2D) buffg;

		super.paintComponent(buffg);

	
		buffg.drawImage(b11.getImage(), b11.getX(), 0, b11.getWidth(), b11.getHeight() * 5 / 4, null);
		buffg.drawImage(b12.getImage(), b12.getX(), 0, b12.getWidth(), b12.getHeight() * 5 / 4, null);
		buffg.drawImage(b21.getImage(), b21.getX(), 0, b21.getWidth(), b21.getHeight() * 5 / 4, null);
		buffg.drawImage(b22.getImage(), b22.getX(), 0, b22.getWidth(), b22.getHeight() * 5 / 4, null);

	
		if (fadeOn) {
			buffg.setColor(backFade);
			buffg.fillRect(0, 0, this.getWidth(), this.getHeight());
		}

	
		for (int i = 0; i < fieldList.size(); i++) {

			Field tempFoot = fieldList.get(i);

		
			if (tempFoot.getX() > -90 && tempFoot.getX() < 810) {

				buffg.drawImage(tempFoot.getImage(), tempFoot.getX(), tempFoot.getY(), tempFoot.getWidth(),
						tempFoot.getHeight(), null);
			}

		}

	
		for (int i = 0; i < jellyList.size(); i++) {

			Jelly tempJelly = jellyList.get(i);

			if (tempJelly.getX() > -90 && tempJelly.getX() < 810) {

				alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
						(float) tempJelly.getAlpha() / 255);
				g2.setComposite(alphaComposite);

				buffg.drawImage(tempJelly.getImage(), tempJelly.getX(), tempJelly.getY(), tempJelly.getWidth(),
						tempJelly.getHeight(), null);

			
				alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 255 / 255);
				g2.setComposite(alphaComposite);
			}
		}

	
		for (int i = 0; i < tacleList.size(); i++) {

			Tacle tempTacle = tacleList.get(i);

			if (tempTacle.getX() > -90 && tempTacle.getX() < 810) {

				buffg.drawImage(tempTacle.getImage(), tempTacle.getX(), tempTacle.getY(), tempTacle.getWidth(),
						tempTacle.getHeight(), null);
			}
		}

		if (c1.isInvincible()) {
		
			alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) c1.getAlpha() / 255);
			g2.setComposite(alphaComposite);

		
			buffg.drawImage(c1.getImage(), c1.getX() - 110, c1.getY() - 170,
					cookieIc.getImage().getWidth(null) * 8 / 10, cookieIc.getImage().getHeight(null) * 8 / 10, null);

		
			alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 255 / 255);
			g2.setComposite(alphaComposite);

		} else {

		
			buffg.drawImage(c1.getImage(), c1.getX() - 110, c1.getY() - 170,
					cookieIc.getImage().getWidth(null) * 8 / 10, cookieIc.getImage().getHeight(null) * 8 / 10, null);
		}

	
		if (redScreen) {

			alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 125 / 255);
			g2.setComposite(alphaComposite);

			buffg.drawImage(redBg.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);

			alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 255 / 255);
			g2.setComposite(alphaComposite);
		}


	
		Util.drawFancyString(g2, Integer.toString(resultScore), 600, 58, 30, Color.WHITE);

	
		buffg.drawImage(lifeBar.getImage(), 20, 30, null);
		buffg.setColor(Color.BLACK);
		buffg.fillRect(84 + (int) (470 * ((double) c1.getHealth() / 1000)), 65,
				1 + 470 - (int) (470 * ((double) c1.getHealth() / 1000)), 21);

	
		buffg.drawImage(jumpBtn, 0, 360, 132, 100, null);
		buffg.drawImage(slideBtn, 650, 360, 132, 100, null);

		if (escKeyOn) {

		
			alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 100 / 255);
			g2.setComposite(alphaComposite);

			buffg.setColor(Color.BLACK);

			buffg.fillRect(0, 0, 850, 550);

		
			alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 255 / 255);
			g2.setComposite(alphaComposite);
		}

	
		g.drawImage(buffImage, 0, 0, this);

	}


	private void makeMo() {

		mo1 = new MapObjectImg(new ImageIcon("img/Objectimg/map1img/bg1.png"),
				new ImageIcon("img/Objectimg/map1img/bg2.png"), new ImageIcon("img/Objectimg/map1img/jelly1.png"),
				new ImageIcon("img/Objectimg/map1img/jelly2.png"), new ImageIcon("img/Objectimg/map1img/jelly3.png"),
				new ImageIcon("img/Objectimg/map1img/life.png"), new ImageIcon("img/Objectimg/map1img/effectTest.png"),
				new ImageIcon("img/Objectimg/map1img/fieldIc1.png"),
				new ImageIcon("img/Objectimg/map1img/fieldIc2.png"), new ImageIcon("img/Objectimg/map1img/tacle1.gif"),
				new ImageIcon("img/Objectimg/map1img/tacle2.png"), new ImageIcon("img/Objectimg/map1img/tacle3.png"),
				new ImageIcon("img/Objectimg/map1img/tacle3.png"));

		mo2 = new MapObjectImg(new ImageIcon("img/Objectimg/map2img/back1.png"),
				new ImageIcon("img/Objectimg/map2img/back2.png"), new ImageIcon("img/Objectimg/map1img/jelly1.png"),
				new ImageIcon("img/Objectimg/map1img/jelly2.png"), new ImageIcon("img/Objectimg/map1img/jelly3.png"),
				new ImageIcon("img/Objectimg/map1img/life.png"), new ImageIcon("img/Objectimg/map1img/effectTest.png"),
				new ImageIcon("img/Objectimg/map2img/field1.png"), new ImageIcon("img/Objectimg/map2img/field2.png"),
				new ImageIcon("img/Objectimg/map2img/tacle1.png"), new ImageIcon("img/Objectimg/map2img/tacle2.png"),
				new ImageIcon("img/Objectimg/map2img/tacle3.png"), new ImageIcon("img/Objectimg/map2img/tacle3.png"));

		mo3 = new MapObjectImg(new ImageIcon("img/Objectimg/map3img/bg.png"),
				new ImageIcon("img/Objectimg/map3img/bg2.png"), new ImageIcon("img/Objectimg/map1img/jelly1.png"),
				new ImageIcon("img/Objectimg/map1img/jelly2.png"), new ImageIcon("img/Objectimg/map1img/jelly3.png"),
				new ImageIcon("img/Objectimg/map1img/life.png"), new ImageIcon("img/Objectimg/map1img/effectTest.png"),
				new ImageIcon("img/Objectimg/map3img/field.png"), new ImageIcon("img/Objectimg/map3img/field2.png"),
				new ImageIcon("img/Objectimg/map3img/tacle1.png"), new ImageIcon("img/Objectimg/map3img/tacle2.png"),
				new ImageIcon("img/Objectimg/map3img/tacle3.png"), new ImageIcon("img/Objectimg/map3img/tacle3.png"));

		mo4 = new MapObjectImg(new ImageIcon("img/Objectimg/map4img/bback.png"),
				new ImageIcon("img/Objectimg/map4img/bback2.png"), new ImageIcon("img/Objectimg/map1img/jelly1.png"),
				new ImageIcon("img/Objectimg/map1img/jelly2.png"), new ImageIcon("img/Objectimg/map1img/jelly3.png"),
				new ImageIcon("img/Objectimg/map1img/life.png"), new ImageIcon("img/Objectimg/map1img/effectTest.png"),
				new ImageIcon("img/Objectimg/map4img/ffootTest.png"),
				new ImageIcon("img/Objectimg/map4img/ffootTest2.png"),
				new ImageIcon("img/Objectimg/map4img/tacle1.png"), new ImageIcon("img/Objectimg/map4img/tacle2.png"),
				new ImageIcon("img/Objectimg/map4img/tacle2.png"), new ImageIcon("img/Objectimg/map4img/tacle2.png"));

	}


	private void initCookieImg(CookieImg ci) {
	
		cookieIc = ci.getCookieIc();
		jumpIc = ci.getJumpIc();
		doubleJumpIc = ci.getDoubleJumpIc();
		fallIc = ci.getFallIc();
		slideIc = ci.getSlideIc();
		hitIc = ci.getHitIc();
	}


	private void initImageIcon(MapObjectImg mo) {

	
		jelly1Ic = mo.getJelly1Ic();
		jelly2Ic = mo.getJelly2Ic();
		jelly3Ic = mo.getJelly3Ic();
		jellyHPIc = mo.getJellyHPIc();

		jellyEffectIc = mo.getJellyEffectIc();

	
		field1Ic = mo.getField1Ic();
		field2Ic = mo.getField2Ic();

	
		tacle10Ic = mo.getTacle10Ic();
		tacle20Ic = mo.getTacle20Ic();
		tacle30Ic = mo.getTacle30Ic();
		tacle40Ic = mo.getTacle40Ic();
	}


	private void initMap(int num, int mapLength) {

		String tempMap = null;
		int tempMapLength = 0;

		if (num == 1) {
			tempMap = "img/map/map1.png";
		} else if (num == 2) {
			tempMap = "img/map/map2.png";
		} else if (num == 3) {
			tempMap = "img/map/map3.png";
		} else if (num == 4) {
			tempMap = "img/map/map4.png";
		}

	
		try {
			sizeArr = Util.getSize(tempMap);
			colorArr = Util.getPic(tempMap);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		tempMapLength = sizeArr[0];
		int maxX = sizeArr[0];
		int maxY = sizeArr[1];

		for (int i = 0; i < maxX; i += 1) {
			for (int j = 0; j < maxY; j += 1) {
				if (colorArr[i][j] == 16776960) {
				
					jellyList.add(new Jelly(jelly1Ic.getImage(), i * 40 + mapLength * 40, j * 40, 30, 30, 255, 1234));

				} else if (colorArr[i][j] == 13158400) {
				
					jellyList.add(new Jelly(jelly2Ic.getImage(), i * 40 + mapLength * 40, j * 40, 30, 30, 255, 2345));

				} else if (colorArr[i][j] == 9868800) {
				
					jellyList.add(new Jelly(jelly3Ic.getImage(), i * 40 + mapLength * 40, j * 40, 30, 30, 255, 3456));

				} else if (colorArr[i][j] == 16737280) {
				
					jellyList.add(new Jelly(jellyHPIc.getImage(), i * 40 + mapLength * 40, j * 40, 30, 30, 255, 4567));
				}
			}
		}

		for (int i = 0; i < maxX; i += 2) {
			for (int j = 0; j < maxY; j += 2) {
				if (colorArr[i][j] == 0) {
				
					fieldList.add(new Field(field1Ic.getImage(), i * 40 + mapLength * 40, j * 40, 80, 80));

				} else if (colorArr[i][j] == 6579300) {
				
					fieldList.add(new Field(field2Ic.getImage(), i * 40 + mapLength * 40, j * 40, 80, 80));
				}
			}
		}

		for (int i = 0; i < maxX; i += 2) {
			for (int j = 0; j < maxY; j += 2) {
				if (colorArr[i][j] == 16711680) {
				
					tacleList.add(new Tacle(tacle10Ic.getImage(), i * 40 + mapLength * 40, j * 40, 80, 80, 0));

				} else if (colorArr[i][j] == 16711830) {
				
					tacleList.add(new Tacle(tacle20Ic.getImage(), i * 40 + mapLength * 40, j * 40, 80, 160, 0));

				} else if (colorArr[i][j] == 16711935) {
				
					tacleList.add(new Tacle(tacle30Ic.getImage(), i * 40 + mapLength * 40, j * 40, 80, 240, 0));
				}
			}
		}

		this.mapLength = this.mapLength + tempMapLength;
	}


	private void initObject() {

	
		lifeBar = new ImageIcon("img/Objectimg/lifebar/lifeBar1.png");

	
		redBg = new ImageIcon("img/Objectimg/lifebar/redBg.png");

	
		jumpButtonIconUp = new ImageIcon("img/Objectimg/lifebar/jumpno.png");
		jumpButtonIconDown = new ImageIcon("img/Objectimg/lifebar/jumpdim.png");

	
		slideIconUp = new ImageIcon("img/Objectimg/lifebar/slideno.png");
		slideIconDown = new ImageIcon("img/Objectimg/lifebar/slidedim.png");

		jumpBtn = jumpButtonIconUp.getImage();
		slideBtn = slideIconUp.getImage();

		jellyList = new ArrayList<>();

		fieldList = new ArrayList<>();

		tacleList = new ArrayList<>();

		mapLengthList = new ArrayList<>();

	

		makeMo();

		initImageIcon(mo1);
		initMap(1, mapLength);
		mapLengthList.add(mapLength);

		initImageIcon(mo2);
		initMap(2, mapLength);
		mapLengthList.add(mapLength);

		initImageIcon(mo3);
		initMap(3, mapLength);
		mapLengthList.add(mapLength);

		initImageIcon(mo4);
		initMap(4, mapLength);

	
		backIc = mo1.getBackIc();
		secondBackIc = mo1.getSecondBackIc();

		backIc2 = mo2.getBackIc();
		secondBackIc2 = mo2.getSecondBackIc();

		backIc3 = mo3.getBackIc();
		secondBackIc3 = mo3.getSecondBackIc();

		backIc4 = mo4.getBackIc();
		secondBackIc4 = mo4.getSecondBackIc();

	
		c1 = new Cookie(cookieIc.getImage());

	
		face = c1.getX() + c1.getWidth();

	
		foot = c1.getY() + c1.getHeight();

	
		b11 = new Back(backIc.getImage(), 0, 0, backIc.getImage().getWidth(null), backIc.getImage().getHeight(null));

	
		b12 = new Back(backIc.getImage(), backIc.getImage().getWidth(null), 0,
				backIc.getImage().getWidth(null), backIc.getImage().getHeight(null));

	
		b21 = new Back(secondBackIc.getImage(), 0, 0, secondBackIc.getImage().getWidth(null),
				secondBackIc.getImage().getHeight(null));

	
		b22 = new Back(secondBackIc.getImage(), secondBackIc.getImage().getWidth(null), 0,
				secondBackIc.getImage().getWidth(null), secondBackIc.getImage().getHeight(null));

		backFade = new Color(0, 0, 0, 0);

	}


	private void initListener() {
		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					if (!escKeyOn) {
						escKeyOn = true;
						add(escButton);
						repaint();
					} else {
						remove(escButton);
						escKeyOn = false;
					}
				}

				if (!escKeyOn) {
					if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP) {
						jumpBtn = jumpButtonIconDown.getImage();
						if (c1.getCountJump() < 2) {
							jump();
						}
					}
					if (e.getKeyCode() == KeyEvent.VK_DOWN) {
						slideBtn = slideIconDown.getImage();
						downKeyOn = true;

						if (c1.getImage() != slideIc.getImage()
								&& !c1.isJump()
								&& !c1.isFall()) {

							c1.setImage(slideIc.getImage());

						}
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					slideBtn = slideIconUp.getImage();
					downKeyOn = false;

					if (c1.getImage() != cookieIc.getImage()
							&& !c1.isJump()
							&& !c1.isFall()) {

						c1.setImage(cookieIc.getImage());
					}
				}

				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					jumpBtn = jumpButtonIconUp.getImage();
				}
			}
		});
	}


	private void runRepaint() {
	
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					repaint();

					if (escKeyOn) {
						while (escKeyOn) {
							try {
								Thread.sleep(10);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}

					try {
						Thread.sleep(10);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}


	private void mapMove() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {

					if (runPage > 800) {
						c1.setHealth(c1.getHealth() - 10);
						runPage = 0;
					}

					runPage += gameSpeed;

					foot = c1.getY() + c1.getHeight();
					if (foot > 1999 || c1.getHealth() < 1) {
						main.getEndPanel().setResultScore(resultScore);
						cl.show(superFrame.getContentPane(), "end");
						main.setGamePanel(new GamePanel(superFrame, cl, main));
						superFrame.requestFocus();
						escKeyOn = true;
					}

				
					if (fadeOn == false) {
						if (mapLength > mapLengthList.get(2) * 40 + 800 && b11.getImage() != backIc4.getImage()) {
							fadeOn = true;

							new Thread(new Runnable() {

								@Override
								public void run() {

									backFadeOut();

									b11 = new Back(backIc4.getImage(), 0, 0, backIc4.getImage().getWidth(null),
											backIc4.getImage().getHeight(null));

									b12 = new Back(backIc4.getImage(), backIc4.getImage().getWidth(null), 0,
											backIc4.getImage().getWidth(null), backIc4.getImage().getHeight(null));

									b21 = new Back(secondBackIc4.getImage(), 0, 0,
											secondBackIc4.getImage().getWidth(null),
											secondBackIc4.getImage().getHeight(null));

									b22 = new Back(secondBackIc4.getImage(), secondBackIc4.getImage().getWidth(null), 0,
											secondBackIc4.getImage().getWidth(null),
											secondBackIc4.getImage().getHeight(null));

									backFadeIn();
									fadeOn = false;
								}
							}).start();

						} else if (mapLength > mapLengthList.get(1) * 40 + 800
								&& mapLength < mapLengthList.get(2) * 40 + 800
								&& b11.getImage() != backIc3.getImage()) {
							fadeOn = true;

							new Thread(new Runnable() {

								@Override
								public void run() {

									backFadeOut();

									b11 = new Back(backIc3.getImage(), 0, 0, backIc3.getImage().getWidth(null),
											backIc3.getImage().getHeight(null));

									b12 = new Back(backIc3.getImage(), backIc3.getImage().getWidth(null), 0,
											backIc3.getImage().getWidth(null), backIc3.getImage().getHeight(null));

									b21 = new Back(secondBackIc3.getImage(), 0, 0,
											secondBackIc3.getImage().getWidth(null),
											secondBackIc3.getImage().getHeight(null));

									b22 = new Back(secondBackIc3.getImage(), secondBackIc3.getImage().getWidth(null), 0,
											secondBackIc3.getImage().getWidth(null),
											secondBackIc3.getImage().getHeight(null));

									backFadeIn();
									fadeOn = false;
								}
							}).start();

						} else if (mapLength > mapLengthList.get(0) * 40 + 800
								&& mapLength < mapLengthList.get(1) * 40 + 800
								&& b11.getImage() != backIc2.getImage()) {
							fadeOn = true;

							new Thread(new Runnable() {

								@Override
								public void run() {

									backFadeOut();

									b11 = new Back(backIc2.getImage(), 0, 0, backIc2.getImage().getWidth(null),
											backIc2.getImage().getHeight(null));

									b12 = new Back(backIc2.getImage(), backIc2.getImage().getWidth(null), 0,
											backIc2.getImage().getWidth(null), backIc2.getImage().getHeight(null));

									b21 = new Back(secondBackIc2.getImage(), 0, 0,
											secondBackIc2.getImage().getWidth(null),
											secondBackIc2.getImage().getHeight(null));

									b22 = new Back(secondBackIc2.getImage(), secondBackIc2.getImage().getWidth(null), 0,
											secondBackIc2.getImage().getWidth(null),
											secondBackIc2.getImage().getHeight(null));

									backFadeIn();
									fadeOn = false;
								}
							}).start();
						}
					}

				
					mapLength += gameSpeed;

					if (b11.getX() < -(b11.getWidth() - 1)) {
						b11.setX(b11.getWidth());
					}
					if (b12.getX() < -(b12.getWidth() - 1)) {
						b12.setX(b12.getWidth());
					}

					if (b21.getX() < -(b21.getWidth() - 1)) {
						b21.setX(b21.getWidth());
					}
					if (b22.getX() < -(b22.getWidth() - 1)) {
						b22.setX(b22.getWidth());
					}

				
					b11.setX(b11.getX() - gameSpeed / 3);
					b12.setX(b12.getX() - gameSpeed / 3);

					b21.setX(b21.getX() - gameSpeed * 2 / 3);
					b22.setX(b22.getX() - gameSpeed * 2 / 3);

				
					for (int i = 0; i < fieldList.size(); i++) {

						Field tempField = fieldList.get(i);

						if (tempField.getX() < -90) {

							fieldList.remove(tempField);

						} else {

							tempField.setX(tempField.getX() - gameSpeed);

						}
					}

				
					for (int i = 0; i < jellyList.size(); i++) {

						Jelly tempJelly = jellyList.get(i);

						if (tempJelly.getX() < -90) {

							fieldList.remove(tempJelly);

						} else {

							tempJelly.setX(tempJelly.getX() - gameSpeed);
							if (tempJelly.getImage() == jellyEffectIc.getImage() && tempJelly.getAlpha() > 4) {
								tempJelly.setAlpha(tempJelly.getAlpha() - 5);
							}

							foot = c1.getY() + c1.getHeight();

							if (
							c1.getImage() != slideIc.getImage()
									&& tempJelly.getX() + tempJelly.getWidth() * 20 / 100 >= c1.getX()
									&& tempJelly.getX() + tempJelly.getWidth() * 80 / 100 <= face
									&& tempJelly.getY() + tempJelly.getWidth() * 20 / 100 >= c1.getY()
									&& tempJelly.getY() + tempJelly.getWidth() * 80 / 100 <= foot
									&& tempJelly.getImage() != jellyEffectIc.getImage()) {

								if (tempJelly.getImage() == jellyHPIc.getImage()) {
									if ((c1.getHealth() + 100) > 1000) {
										c1.setHealth(1000);
									} else {
										c1.setHealth(c1.getHealth() + 100);
									}
								}
								tempJelly.setImage(jellyEffectIc.getImage());
								resultScore = resultScore + tempJelly.getScore();

							} else if (
							c1.getImage() == slideIc.getImage()
									&& tempJelly.getX() + tempJelly.getWidth() * 20 / 100 >= c1.getX()
									&& tempJelly.getX() + tempJelly.getWidth() * 80 / 100 <= face
									&& tempJelly.getY() + tempJelly.getWidth() * 20 / 100 >= c1.getY()
											+ c1.getHeight() * 1 / 3
									&& tempJelly.getY() + tempJelly.getWidth() * 80 / 100 <= foot
									&& tempJelly.getImage() != jellyEffectIc.getImage()) {

								if (tempJelly.getImage() == jellyHPIc.getImage()) {
									if ((c1.getHealth() + 100) > 1000) {
										c1.setHealth(1000);
									} else {
										c1.setHealth(c1.getHealth() + 100);
									}
								}
								tempJelly.setImage(jellyEffectIc.getImage());
								resultScore = resultScore + tempJelly.getScore();

							}
						}
					}

				
					for (int i = 0; i < tacleList.size(); i++) {

						Tacle tempTacle = tacleList.get(i);

						if (tempTacle.getX() < -90) {

							fieldList.remove(tempTacle);

						} else {

							tempTacle.setX(tempTacle.getX() - gameSpeed);

							face = c1.getX() + c1.getWidth();
							foot = c1.getY() + c1.getHeight();

							if (
							!c1.isInvincible() && c1.getImage() != slideIc.getImage()
									&& tempTacle.getX() + tempTacle.getWidth() / 2 >= c1.getX()
									&& tempTacle.getX() + tempTacle.getWidth() / 2 <= face
									&& tempTacle.getY() + tempTacle.getHeight() / 2 >= c1.getY()
									&& tempTacle.getY() + tempTacle.getHeight() / 2 <= foot) {

								hit();

							} else if (
							!c1.isInvincible() && c1.getImage() != slideIc.getImage()
									&& tempTacle.getX() + tempTacle.getWidth() / 2 >= c1.getX()
									&& tempTacle.getX() + tempTacle.getWidth() / 2 <= face
									&& tempTacle.getY() <= c1.getY()
									&& tempTacle.getY() + tempTacle.getHeight() * 95 / 100 > c1.getY()) {

								hit();

							} else if (
							!c1.isInvincible() && c1.getImage() == slideIc.getImage()
									&& tempTacle.getX() + tempTacle.getWidth() / 2 >= c1.getX()
									&& tempTacle.getX() + tempTacle.getWidth() / 2 <= face
									&& tempTacle.getY() + tempTacle.getHeight() / 2 >= c1.getY()
											+ c1.getHeight() * 2 / 3
									&& tempTacle.getY() + tempTacle.getHeight() / 2 <= foot) {

								hit();

							} else if (
							!c1.isInvincible() && c1.getImage() == slideIc.getImage()
									&& tempTacle.getX() + tempTacle.getWidth() / 2 >= c1.getX()
									&& tempTacle.getX() + tempTacle.getWidth() / 2 <= face
									&& tempTacle.getY() < c1.getY() && tempTacle.getY()
											+ tempTacle.getHeight() * 95 / 100 > c1.getY() + c1.getHeight() * 2 / 3) {

								hit();
							}
						}
					}

				
					int tempField;
					int tempNowField;

				
					if (c1.isInvincible()) {
						tempNowField = 400;
					} else {
						tempNowField = 2000;
					}

					for (int i = 0; i < fieldList.size(); i++) {

						int tempX = fieldList.get(i).getX();

						if (tempX > c1.getX() - 60 && tempX <= face) {

							tempField = fieldList.get(i).getY();

							foot = c1.getY() + c1.getHeight();

						
						
							if (tempField < tempNowField && tempField >= foot) {

								tempNowField = tempField;

							}
						}
					}

					nowField = tempNowField;

					if (escKeyOn) {
						while (escKeyOn) {
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}

					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		}).start();
	}


	private void hit() {
		new Thread(new Runnable() {

			@Override
			public void run() {

				c1.setInvincible(true);

				System.out.println("hello world");

				redScreen = true;

				c1.setHealth(c1.getHealth() - 100);

				c1.setImage(hitIc.getImage());

				c1.setAlpha(80);

				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				redScreen = false;

				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (c1.getImage() == hitIc.getImage()) {

					c1.setImage(cookieIc.getImage());

				}

				for (int j = 0; j < 11; j++) {

					if (c1.getAlpha() == 80) {

						c1.setAlpha(160);

					} else {

						c1.setAlpha(80);

					}
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				c1.setAlpha(255);

				c1.setInvincible(false);
				System.out.println("eiei");
			}
		}).start();
	}


	private void fall() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {

					foot = c1.getY() + c1.getHeight();

				
					if (!escKeyOn
							&& foot < nowField
							&& !c1.isJump()
							&& !c1.isFall()) {

						c1.setFall(true);
						System.out.println("fdsafsafsafs");

						if (c1.getCountJump() == 2) {
							c1.setImage(fallIc.getImage());
						}

						long t1 = Util.getTime();
						long t2;
						int set = 1;

						while (foot < nowField) {

							t2 = Util.getTime() - t1;

							int fallY = set + (int) ((t2) / 40);

							foot = c1.getY() + c1.getHeight();

							if (foot + fallY >= nowField) {
								fallY = nowField - foot;
							}

							c1.setY(c1.getY() + fallY);

							if (c1.isJump()) {
								break;
							}

							if (escKeyOn) {
								long tempT1 = Util.getTime();
								long tempT2 = 0;
								while (escKeyOn) {
									try {
										Thread.sleep(10);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
								tempT2 = Util.getTime() - tempT1;
								t1 = t1 + tempT2;
							}

							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

						}
						c1.setFall(false);

						if (downKeyOn
								&& !c1.isJump()
								&& !c1.isFall()
								&& c1.getImage() != slideIc.getImage()) {

							c1.setImage(slideIc.getImage());

						} else if (!downKeyOn
								&& !c1.isJump()
								&& !c1.isFall()
								&& c1.getImage() != cookieIc.getImage()) {

							c1.setImage(cookieIc.getImage());
						}

						if (!c1.isJump()) {
							c1.setCountJump(0);
						}
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}


	private void jump() {
		new Thread(new Runnable() {

			@Override
			public void run() {

				c1.setCountJump(c1.getCountJump() + 1);

				int nowJump = c1.getCountJump();

				c1.setJump(true);

				if (c1.getCountJump() == 1) {

					System.out.println("กระโดด");
					c1.setImage(jumpIc.getImage());

				} else if (c1.getCountJump() == 2) {

					System.out.println("กระโดด 2 รอบ");
					c1.setImage(doubleJumpIc.getImage());

				}

				long t1 = Util.getTime();
				long t2;
				int set = 8;
				int jumpY = 1;

				while (jumpY >= 0) {

					t2 = Util.getTime() - t1;

					jumpY = set - (int) ((t2) / 40);

					c1.setY(c1.getY() - jumpY);

					if (nowJump != c1.getCountJump()) {
						break;
					}

					if (escKeyOn) {
						long tempT1 = Util.getTime();
						long tempT2 = 0;
						while (escKeyOn) {
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						tempT2 = Util.getTime() - tempT1;
						t1 = t1 + tempT2;
					}

					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				if (nowJump == c1.getCountJump()) {
					c1.setJump(false);
				}

			}
		}).start();
	}

	private void backFadeOut() {
		for (int i = 0; i < 256; i += 2) {
			backFade = new Color(0, 0, 0, i);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private void backFadeIn() {
		for (int i = 255; i >= 0; i -= 2) {
			backFade = new Color(0, 0, 0, i);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
