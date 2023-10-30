package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class EndPanel extends JPanel {
	
	ImageIcon btn = new ImageIcon("img/end/button.png");
	JButton btnNewButton;
	JLabel lblNewLabel_1;
	JLabel lblNewLabel_2;
	JLabel lblNewLabel;
	
	
	private int resultScore;
	
	public void setResultScore(int resultScore) {
		lblNewLabel_2.setText(resultScore+"");
	}

	public EndPanel(Object o) {

		btnNewButton = new JButton(btn);
		btnNewButton.setName("endAccept");
		btnNewButton.addMouseListener((MouseListener) o);
		btnNewButton.setBounds(200, 360, 210, 81);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setContentAreaFilled(false);
		add(btnNewButton);

		lblNewLabel_1 = new JLabel("SCORE");	
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 37));
		lblNewLabel_1.setBounds(251, 100, 205, 55);
		add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("0");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 49));
		lblNewLabel_2.setBounds(233, 152, 459, 87);
		add(lblNewLabel_2);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBackground(SystemColor.activeCaptionText);
		lblNewLabel.setIcon(new ImageIcon("img/end/Gameresult.jpg"));
		lblNewLabel.setBounds(0, 0, 784, 461);
		add(lblNewLabel);
	}
}
