package game.view;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import game.vo.Diamond;
import game.vo.Hail;

public class GameView extends JFrame {
	//20개의 우박 객체 참조값이 저장되는 배열
	Hail[] hails = new Hail[20]; 
	JLabel[] lblHails = new JLabel[hails.length];
	Diamond[] diamonds = new Diamond[hails.length-5];
	JLabel[] lblDiamonds = new JLabel[diamonds.length];
	JLabel charjl = new JLabel();
	JLabel scorejl = new JLabel("점수 : ");
	int score = 0;
	
	public GameView() {
		setLayout(null);
		getContentPane().setBackground(Color.WHITE);
		Random random = new Random();
		HailThread hThread = null;
		DiamondThread dThread = null;
		scorejl.setBounds(900, 20, 100, 30);
		add(scorejl);
		
		//20개의 우박 객체를 생성해서 배열에 저장
		for (int i = 0; i < hails.length; i++) {
			hails[i] = new Hail();
			hails[i].setX(i * 70);
			hails[i].setY(i * random.nextInt(70));
			hails[i].setW(35);
			hails[i].setH(40);
			hails[i].setImgName("images/똥.jpg");
			hails[i].setPoint(10);
			lblHails[i] = new JLabel(new ImageIcon(hails[i].getImgName()));
			lblHails[i].setBounds(hails[i].getX(), hails[i].getY(), hails[i].getW(), hails[i].getY());
			add(lblHails[i]);
			hThread = new HailThread(lblHails[i], hails[i]);
			hThread.start();
		}
		
		//10개의 다이아몬드 객체를 생성해서 배열에 저장
		for (int i = 0; i < diamonds.length; i++) {
			diamonds[i] = new Diamond();
			diamonds[i].setX(i * 140 + random.nextInt(70));
			diamonds[i].setY(i * random.nextInt(30));
			diamonds[i].setW(40);
			diamonds[i].setH(40);
			diamonds[i].setImgName("images/보석" + random.nextInt(10) + ".jpg");
			diamonds[i].setPoint(20);
			lblDiamonds[i] = new JLabel(new ImageIcon(diamonds[i].getImgName()));
			lblDiamonds[i].setBounds(diamonds[i].getX(), diamonds[i].getY(), diamonds[i].getW(), diamonds[i].getY());
			add(lblDiamonds[i]);
			dThread = new DiamondThread(lblDiamonds[i], diamonds[i]);
			dThread.start();
		}
		
		ImageIcon icon = new ImageIcon("images/말0.jpg");
		charjl.setIcon(icon);
		
		charjl.setBounds(470, 530, 50, 50);
		add(charjl);
		addKeyListener(keyL);
		
		setTitle("똥 피하기 게임");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		requestFocus();
	}
	
	public void changeScore() {
		for (int i = 0; i < lblHails.length; i++) {				
			if(charjl.getX() >= lblHails[i].getX() && charjl.getX() <= lblHails[i].getX()+lblHails[i].getWidth()) {
				if(charjl.getY() >= lblHails[i].getY() && charjl.getY() <= lblHails[i].getY()+lblHails[i].getHeight()) {
					score -= 10;
					scorejl.setText("점수: " + score + "점");
				}//if
			}//if
		}//for
		
		for (int i = 0; i < lblDiamonds.length; i++) {				
			if(charjl.getX() >= lblDiamonds[i].getX() && charjl.getX() <= lblDiamonds[i].getX()+lblDiamonds[i].getWidth()) {
				if(charjl.getY() >= lblDiamonds[i].getY() && charjl.getY() <= lblDiamonds[i].getY()+lblDiamonds[i].getHeight()) {
					score += 10;
					scorejl.setText("점수: " + score + "점");
				}//if
			}//if
		}//for
	}
	
	KeyAdapter keyL = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT :
				charjl.setLocation(charjl.getX()-10, charjl.getY()); break;
			case KeyEvent.VK_RIGHT :
				charjl.setLocation(charjl.getX()+10, charjl.getY()); break;
			}
			changeScore();
		}
	};
	
	public class HailThread extends Thread{
		JLabel hailLbl;
		Hail hail;
		
		public HailThread(JLabel hailLbl, Hail hail) {
			this.hailLbl = hailLbl;
			this.hail = hail;
		}
		
		
		@Override
		public void run() {
			while (true) {
				Random random = new Random();
				if( hailLbl.getY() <= 600)
					hailLbl.setLocation(hailLbl.getX(), hailLbl.getY() + 10);
				else {
					hailLbl.setLocation(hailLbl.getX(), random.nextInt(70));
				}
					
				
				try {
					sleep(20 * random.nextInt(100));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}
	
	
	public class DiamondThread extends Thread{
		JLabel diamondLbl;
		Diamond diamond;
		
		public DiamondThread(JLabel diamondLbl, Diamond diamond) {
			this.diamondLbl = diamondLbl;
			this.diamond = diamond;
		}
		
		
		@Override
		public void run() {
			while (true) {
				Random random = new Random();
				if( diamondLbl.getY() <= 600) {
					diamondLbl.setLocation(diamondLbl.getX(), diamondLbl.getY() + 10);
				} else {
					diamondLbl.setLocation(diamondLbl.getX(), random.nextInt(70));
				}//if
					
				try {
					sleep(20 * random.nextInt(40));
				} catch (InterruptedException e) {
	
				}
			}

		}
	}//class
	
}//class


