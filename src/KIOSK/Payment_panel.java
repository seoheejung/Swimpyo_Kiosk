package KIOSK;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Payment_panel extends JPanel implements ActionListener {

	Font font;

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
	// String format_time = format.format (System.currentTimeMillis());

	String name;
	String mobile;
	int seatNum;
	String useTime;
	int price;

	JLabel summary_LB;
	JLabel payment_LB;
	JLabel seatNum_LB;
	JLabel useTime_LB;
	JLabel startTime_LB;
	JLabel totalPrice_LB;
	JLabel inputMoney_LB;

	Image img = new ImageIcon("./src/Image/purchase.jpg").getImage();

	JButton login_btn; // ó������ ��ư
	JButton seat_btn; // �¼�ȭ�� ��ư
	JButton payment_btn; // �����ϱ� ��ư
	JButton cancel_btn; // ��� ��ư

	JButton cashPayment_btn; // ���ݰ��� ��ư
	JButton cardPayment_btn; // ī�� ���� ��ư

	JTextField inputMoney_tf; // ������ �ݾ� �Է�â
	JButton[] money_Btn;
	int[] arMoneyKind = { 50000, 10000, 5000, 1000 }; // ȭ�� ������ ����
	int inputMoney;

	// ���� ���ݰ��� ��Ȳ�� ����
	public Payment_panel(String name, String mobile, int seatNum, String useTime, int price) {
		this.name = name;
		this.mobile = mobile;
		this.seatNum = seatNum;
		this.useTime = useTime;
		this.price = price;

		setLayout(null); // ��ġ������ ���� x
		info(name, mobile);

		font = new Font("�����ٸ�����", Font.PLAIN, 28);
		summary_LB = new JLabel(" ���� ����");
		summary_LB.setFont(font);
		summary_LB.setForeground(Color.WHITE);
		summary_LB.setOpaque(true);
		summary_LB.setBackground(ColorInfo.instance.dial_button_color);
		summary_LB.setVisible(true);
		summary_LB.setBounds(50, 200, 120, 50);
		add(summary_LB);

		payment_LB = new JLabel(" ���� ���");
		payment_LB.setFont(font);
		payment_LB.setForeground(Color.WHITE);
		payment_LB.setOpaque(true);
		payment_LB.setBackground(ColorInfo.instance.dial_button_color);
		payment_LB.setVisible(true);
		payment_LB.setBounds(50, 450, 120, 50);
		add(payment_LB);

		font = new Font("�����ٸ�����", Font.PLAIN, 20);
		seatNum_LB = new JLabel("�� ���� �¼� : " + seatNum + "��");
		seatNum_LB.setFont(font);
		seatNum_LB.setForeground(Color.BLACK);
		seatNum_LB.setBackground(ColorInfo.instance.label_color);
		seatNum_LB.setOpaque(true);
		seatNum_LB.setVisible(true);
		seatNum_LB.setBounds(60, 260, 260, 40);
		add(seatNum_LB);

		useTime_LB = new JLabel("�� �̿� �ð� : " + useTime);
		useTime_LB.setFont(font);
		useTime_LB.setForeground(Color.BLACK);
		useTime_LB.setBackground(ColorInfo.instance.label_color);
		useTime_LB.setOpaque(true);
		useTime_LB.setVisible(true);
		useTime_LB.setBounds(60, 300, 360, 40);
		add(useTime_LB);

		totalPrice_LB = new JLabel("�� ������ �ݾ� : " + price + "��");
		totalPrice_LB.setFont(font);
		totalPrice_LB.setForeground(Color.BLACK);
		totalPrice_LB.setBackground(ColorInfo.instance.label_color);
		totalPrice_LB.setOpaque(true);
		totalPrice_LB.setVisible(true);
		totalPrice_LB.setBounds(60, 340, 260, 40);
		add(totalPrice_LB);

		startTime_LB = new JLabel("�� ��� ���� �ð� : " + format.format(System.currentTimeMillis()));
		startTime_LB.setFont(font);
		startTime_LB.setForeground(Color.BLACK);
		startTime_LB.setBackground(ColorInfo.instance.label_color);
		startTime_LB.setOpaque(true);
		startTime_LB.setVisible(true);
		startTime_LB.setBounds(60, 380, 360, 40);
		add(startTime_LB);

		paymentMethod();
	}

	void paymentMethod() {
		font = new Font("�����ٸ�����", Font.PLAIN, 20);
		cashPayment_btn = new JButton("���ݰ���");
		cashPayment_btn.setForeground(ColorInfo.instance.button_color);
		cashPayment_btn.setBackground(ColorInfo.instance.label_color);
		cashPayment_btn.setFont(font);
		cashPayment_btn.setBounds(50, 520, 120, 40);
		cashPayment_btn.setVisible(true);
		cashPayment_btn.addActionListener(this);
		add(cashPayment_btn);

		cardPayment_btn = new JButton("ī�����");
		cardPayment_btn.setForeground(ColorInfo.instance.button_color);
		cardPayment_btn.setBackground(ColorInfo.instance.label_color);
		cardPayment_btn.setFont(font);
		cardPayment_btn.setBounds(200, 520, 120, 40);
		cardPayment_btn.setVisible(true);
		cardPayment_btn.addActionListener(this);
		add(cardPayment_btn);

	}

	void cashPaymentSet() {
		inputMoney = 0;

		font = new Font("�����ٸ�����", Font.PLAIN, 20);
		inputMoney_LB = new JLabel("�� ������ �ݾ�  : ");
		inputMoney_LB.setFont(font);
		inputMoney_LB.setForeground(Color.BLACK);
		inputMoney_LB.setBackground(ColorInfo.instance.label_color);
		inputMoney_LB.setOpaque(true);
		inputMoney_LB.setVisible(true);
		inputMoney_LB.setBounds(60, 590, 130, 40);
		add(inputMoney_LB);

		font = new Font("�����ٸ�����", Font.PLAIN, 18);
		inputMoney_tf = new JTextField(inputMoney + "");
		inputMoney_tf.setBounds(200, 590, 120, 40);
		inputMoney_tf.setBackground(new Color(235, 255, 230));
		inputMoney_tf.setForeground(Color.BLACK);
		inputMoney_tf.setFont(font);
		add(inputMoney_tf);

		moneyButtonSet();

		font = new Font("�����ٸ�����", Font.PLAIN, 24);
		payment_btn = new JButton("����");
		payment_btn.setForeground(ColorInfo.instance.label_color);
		payment_btn.setBackground(ColorInfo.instance.button_color);
		payment_btn.setFont(font);
		payment_btn.setBounds(280, 840, 100, 50);
		payment_btn.setVisible(true);
		payment_btn.addActionListener(this);
		add(payment_btn);

		cancel_btn = new JButton("���");
		cancel_btn.setForeground(Color.gray);
		cancel_btn.setBackground(ColorInfo.instance.label_color);
		cancel_btn.setFont(font);
		cancel_btn.setBounds(400, 840, 100, 50);
		cancel_btn.setVisible(true);
		cancel_btn.addActionListener(this);
		add(cancel_btn);
	}

	void moneyButtonSet() {
		font = new Font("�����ٸ�����", Font.PLAIN, 22);
		money_Btn = new JButton[4];
		for (int i = 0; i < money_Btn.length; i++) {
			money_Btn[i] = new JButton(arMoneyKind[i] + "");
			money_Btn[i].setBackground(new Color(210, 210, 210));
			money_Btn[i].setForeground(Color.WHITE);
			money_Btn[i].setFont(font);
			money_Btn[i].setBorder(BorderFactory.createEmptyBorder(0 , 0 , 0 , 0));
			money_Btn[i].setBounds(60 + i * 110, 670, 90, 60);
			money_Btn[i].addActionListener(this);
			add(money_Btn[i]);
		}

		for (int i = 0; i < money_Btn.length; i++) {
			int n = i;
			money_Btn[i].addMouseListener(new MouseListener() {
				@Override
				public void mousePressed(MouseEvent e) {
					inputMoney += arMoneyKind[n];
					inputMoney_tf.setText(inputMoney + "");
					repaint();
				}

				@Override
				public void mouseReleased(MouseEvent e) {
				}

				@Override
				public void mouseExited(MouseEvent e) {
				}

				@Override
				public void mouseEntered(MouseEvent e) {
				}

				@Override
				public void mouseClicked(MouseEvent e) {
				}
			});

		}
	}

	public boolean purchase() {
		boolean result = false;

		int charge = inputMoney - price;

		// ������ �ݾ� �ľ�
		if (charge < 0) {
			return result;
		} else {
			result = true;
		}
		return result;
	}

	void info(String name, String mobile) {
		// ���� ���̺�
		font = new Font("�����ٸ�����", Font.BOLD, 40);
		JLabel name_LB = new JLabel(name);
		name_LB.setFont(font);
		name_LB.setForeground(Color.white);
		name_LB.setBounds(40, 60, 120, 40);
		add(name_LB);

		font = new Font("�����ٸ�����", Font.BOLD, 16);
		JLabel mobile_LB = new JLabel("HP." + mobile);
		mobile_LB.setFont(font);
		mobile_LB.setForeground(Color.white);
		mobile_LB.setBounds(40, 110, 200, 30);
		add(mobile_LB);

		font = new Font("�����ٸ�����", Font.BOLD, 22);
		seat_btn = new JButton("�¼�ȭ��");
		seat_btn.setBackground(ColorInfo.instance.label_color);
		seat_btn.setForeground(Color.black);
		seat_btn.setFont(font);
		seat_btn.setBounds(240, 70, 120, 60);
		seat_btn.addActionListener(this);
		add(seat_btn);

		login_btn = new JButton("ó������");
		login_btn.setBackground(ColorInfo.instance.label_color);
		login_btn.setForeground(Color.black);
		login_btn.setFont(font);
		login_btn.setBounds(380, 70, 120, 60);
		login_btn.addActionListener(this);
		add(login_btn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == login_btn) {
			MainSystem.frame.setContentPane(new Login_Panel());
			MainSystem.frame.revalidate();
		} else if (e.getSource() == seat_btn) {
			MainSystem.frame.setContentPane(new Seat_Panel(name, mobile));
			MainSystem.frame.revalidate();

		} else if (e.getSource() == cashPayment_btn) {
			cashPaymentSet();
			repaint();
		} else if (e.getSource() == cardPayment_btn) {
			JOptionPane.showMessageDialog(null, "�غ� ���Դϴ�.", "Message", JOptionPane.INFORMATION_MESSAGE);
		} else if (e.getSource() == cancel_btn) {
			JOptionPane.showMessageDialog(null, "������ ��ҵǾ����ϴ�.", "Message", JOptionPane.PLAIN_MESSAGE );
			MainSystem.frame.setContentPane(new Login_Panel());
			MainSystem.frame.revalidate();
		} else if (e.getSource() == payment_btn) {
			if (purchase()) {
				JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.", "Message", JOptionPane.PLAIN_MESSAGE );
				if (!useTime.equals("")) {
					String temp = "";
					System.out.println(useTime);
					for (int i = 0; i < useTime.length(); i++) {
						if (useTime.charAt(i) == '��') {
							break;
						}
						temp += useTime.charAt(i);
					}
					System.out.println(temp);
					useTime = temp.substring(1, temp.length());
				}
				FileManager.instance.checkIn(mobile, useTime, seatNum);
			} else {
				JOptionPane.showMessageDialog(null, "������ �ݾ��� �����մϴ�.", "Message", JOptionPane.INFORMATION_MESSAGE);
				return;
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