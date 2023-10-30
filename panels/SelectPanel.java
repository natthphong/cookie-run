package panels;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ingame.CookieImg;
import sound.SoundPlayer;

public class SelectPanel extends JPanel {

	private ImageIcon[] chIcons = {
			new ImageIcon("img/select/selectCh1.png"),
			new ImageIcon("img/select/selectCh2.png"),
			new ImageIcon("img/select/selectCh3.png"),
			new ImageIcon("img/select/selectCh4.png")
	};

	private ImageIcon[] selectedChIcons = {
			new ImageIcon("img/select/selectedCh1.png"),
			new ImageIcon("img/select/selectedCh2.png"),
			new ImageIcon("img/select/selectedCh3.png"),
			new ImageIcon("img/select/selectedCh4.png")
	};

	private JButton[] chButtons = new JButton[4];
	private CookieImg ci;
	private JButton StartBtn;

	private SoundPlayer click = new SoundPlayer("sound/wav/jump.wav");

	public CookieImg getCi() {
		return ci;
	}

	public SelectPanel(Object o) {
		setupButtons();
		setupStartButton(o);
		JLabel selectBg = new JLabel("");
		selectBg.setForeground(Color.ORANGE);
		selectBg.setHorizontalAlignment(SwingConstants.CENTER);
		selectBg.setIcon(new ImageIcon("img/select/selectBg.png"));
		selectBg.setBounds(0, 0, 784, 461);
		add(selectBg);


		JLabel selectTxt = new JLabel("");
		selectTxt.setHorizontalAlignment(SwingConstants.CENTER);
		selectTxt.setIcon(new ImageIcon("img/select/selectTxt.png"));
		selectTxt.setBounds(174, 20, 397, 112);
		add(selectTxt);
	}

	private void setupButtons() {
		for (int i = 0; i < chButtons.length; i++) {
			chButtons[i] = new JButton(chIcons[i]);
			chButtons[i].setName("ch" + (i + 1));
			int index = i;
			chButtons[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					click.startPlay(1);
					for (int j = 0; j < chButtons.length; j++) {
						chButtons[j].setIcon((j == index) ? selectedChIcons[j] : chIcons[j]);
					}
					setCookieImg(index + 1);
				}
			});
			chButtons[i].setBounds(90 + i * 148, 102, 150, 200);
			add(chButtons[i]);
			chButtons[i].setBorderPainted(false);
			chButtons[i].setContentAreaFilled(false);
			chButtons[i].setFocusPainted(false);
		}

	}

	private void setupStartButton(Object o) {
		StartBtn = new JButton(new ImageIcon("img/select/GameStartBtn.png"));
		StartBtn.setName("StartBtn");
		StartBtn.addMouseListener((MouseListener) o);
		StartBtn.setBounds(254, 334, 291, 81);
		add(StartBtn);
		StartBtn.setBorderPainted(false);
		StartBtn.setContentAreaFilled(false);
		StartBtn.setFocusPainted(false);
	}

	private void setCookieImg(int index) {
		String cookieImagePath = "img/cookieimg/cookie" + index + "/";
		ci = new CookieImg(
				new ImageIcon(cookieImagePath + "normal.gif"),
				new ImageIcon(cookieImagePath + "jump.gif"),
				new ImageIcon(cookieImagePath + "doublejump.gif"),
				new ImageIcon(cookieImagePath + "fall.png"),
				new ImageIcon(cookieImagePath + "slide.gif"),
				new ImageIcon(cookieImagePath + "hit.png")
		);
	}

}
