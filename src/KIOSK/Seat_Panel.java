package KIOSK;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Seat_Panel extends JPanel implements ActionListener {
	// �̱��� ����

	Font font;
	String name;
	String moblie;

	Image img = new ImageIcon("./src/Image/seat.jpg").getImage();

	JButton back_btn; // �ڷΰ��� ��ư
	JButton update_btn; // �������� ��ư
	JButton[][] seat_btn; // �¼� ��ư
	SeatInfo[][] seats; // �¼� ��ü

	public Seat_Panel(String name, String mobile) {
		this.name = name;
		this.moblie = mobile;

		setLayout(null); // ��ġ������ ���� x
		info(name, mobile);

		font = new Font("��������", Font.BOLD, 26);
		JLabel seat_LB = new JLabel("�¼� �̿� ��Ȳ : ");
		seat_LB.setFont(font);
		seat_LB.setForeground(Color.black);
		seat_LB.setBounds(50, 200, 220, 50);
		add(seat_LB);

		button();
	}

	void button() {
		seat_btn = new JButton[4][7];
		int num = 1;
		font = new Font("��������", Font.BOLD, 9);
		for (int i = 0; i < seat_btn.length; i++) {
			for (int n = 0; n < seat_btn[i].length; n++) {
				seat_btn[i][n] = new JButton();
				seat_btn[i][n].setText(num + "");
				seat_btn[i][n].setFont(font);
				seat_btn[i][n].setBackground(Color.gray);
				if (FileManager.instance.seatManager.get(num).isSeatUse()) {
					seat_btn[i][n].setBackground(Color.black);
				}
				seat_btn[i][n].setForeground(Color.WHITE);
				
				if( i < 2) {
					seat_btn[i][n].setBounds(190 + (i * 90), 390 + (n * 58), 44, 44);
				}else if(i >= 2) {
					seat_btn[i][n].setBounds(165 + (i * 90), 390 + (n * 58), 44, 44);
				}
				
				add(seat_btn[i][n]);
				num += 1;
			}
		}
		

		
	}

	void info(String name, String mobile) {
		// ���� ���̺�
		font = new Font("��������", Font.BOLD, 40);
		JLabel name_LB = new JLabel(name);
		name_LB.setFont(font);
		name_LB.setForeground(Color.white);
		name_LB.setBounds(40, 60, 120, 40);
		add(name_LB);

		font = new Font("��������", Font.BOLD, 16);
		JLabel mobile_LB = new JLabel("HP." + mobile);
		mobile_LB.setFont(font);
		mobile_LB.setForeground(Color.white);
		mobile_LB.setBounds(40, 110, 200, 30);
		add(mobile_LB);

		font = new Font("��������", Font.BOLD, 22);
		update_btn = new JButton("��������");
		update_btn.setBackground(Color.WHITE);
		update_btn.setForeground(Color.black);
		update_btn.setFont(font);
		update_btn.setBounds(240, 70, 120, 60);
		update_btn.addActionListener(this);
		add(update_btn);

		back_btn = new JButton("�ڷΰ���");
		back_btn.setBackground(Color.WHITE);
		back_btn.setForeground(Color.black);
		back_btn.setFont(font);
		back_btn.setBounds(380, 70, 120, 60);
		back_btn.addActionListener(this);
		add(back_btn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back_btn) {
			MainSystem.frame.setContentPane(new Login_Panel());
			MainSystem.frame.revalidate();
		}
		for (int i = 0; i < seat_btn.length; i++) {
			for (int n = 0; n < seat_btn[i].length; n++) {
				if (e.getSource() == seat_btn[i][n]) {
					int temp = Integer.parseInt(seat_btn[i][n].getText());
					if(FileManager.instance.seatManager.get(temp).isSeatUse()) {
						JOptionPane.showMessageDialog(null, "������� �¼��Դϴ�.", "", JOptionPane.WARNING_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "�������� �ʴ� ��ȣ�Դϴ�.", "", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		}

	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, 540, 960, null);
		setOpaque(false);
		super.paintComponent(g);
	}

}