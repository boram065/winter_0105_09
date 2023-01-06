package game.view;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import game.vo.Diamond;
import game.vo.Hail;

public class Level2 extends JFrame{
	//30개의 우박 객체 참조값이 저장되는 배열
	Hail[] hails = new Hail[30]; 
	JLabel[] lblHails = new JLabel[hails.length];
	Diamond[] diamonds = new Diamond[hails.length-10];
	JLabel[] lblDiamonds = new JLabel[diamonds.length];
	JLabel charjl = new JLabel();
	JLabel scorejl = new JLabel("점수 : ");
	int score = 0;
	private int FRAME_WIDTH = 1000;
	private int FRAME_HEIGHT = 600;
	
	public Level2() {
		setLayout(null);
		getContentPane().setBackground(Color.WHITE);
		Random random = new Random();
		HailThread hThread = null;
		DiamondThread dThread = null;
		scorejl.setBounds(900, 20, 100, 30);
		add(scorejl);
		
		//30개의 우박 객체를 생성해서 배열에 저장
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
		
		//20개의 다이아몬드 객체를 생성해서 배열에 저장
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
		
		charjl.setBounds(470, 510, 50, 50);
		add(charjl);
		addKeyListener(keyL);
		
		setTitle("Level2");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		requestFocus();
	}
	
	public void changeScore() {
		for (int i = 0; i < lblHails.length; i++) {				
			if(charjl.getX() >= lblHails[i].getX() && charjl.getX() <= lblHails[i].getX()+lblHails[i].getWidth()) {
				if(charjl.getY() >= lblHails[i].getY() && charjl.getY() <= lblHails[i].getY()+lblHails[i].getHeight()) {
					if(score > hails[i].getPoint()) {
						score -= hails[i].getPoint();
						scorejl.setText("점수: " + score + "점");
					}//if
				}//if
			}//if
		}//for
		
		for (int i = 0; i < lblDiamonds.length; i++) {				
			if(charjl.getX() >= lblDiamonds[i].getX() && charjl.getX() <= lblDiamonds[i].getX()+lblDiamonds[i].getWidth()) {
				if(charjl.getY() >= lblDiamonds[i].getY() && charjl.getY() <= lblDiamonds[i].getY()+lblDiamonds[i].getHeight()) {
					if(score <= 980) {
						score += diamonds[i].getPoint();
						scorejl.setText("점수: " + score + "점");
					}//if
				}//if
			}//if
		}//for
		
		if(score < 0) {
			JOptionPane.showMessageDialog(Level2.this, "게임이 종료되었습니다.", "게임종료", JOptionPane.ERROR_MESSAGE);
			setVisible(false);
		}else if(score >= 300){
			int answer = JOptionPane.showConfirmDialog(Level2.this, "300점을 달성하셨습니다.\nLevel3로 넘어가시겠습니까?", "Level3", JOptionPane.YES_NO_OPTION);
			switch(answer) {
				case JOptionPane.YES_OPTION :  
					Level3 level = new Level3();
					level.setVisible(true);
					setVisible(false);
				case JOptionPane.NO_OPTION :
					setVisible(false);
			}//switch
		}//if
	}
	
	KeyAdapter keyL = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_UP: 
				if(charjl.getY() > 0) {
					charjl.setLocation(charjl.getX(), charjl.getY()-10); break;
				}
			case KeyEvent.VK_DOWN:
				if(charjl.getY() < FRAME_HEIGHT - charjl.getHeight()*2) {
					charjl.setLocation(charjl.getX(), charjl.getY()+10); break;
				}
			case KeyEvent.VK_LEFT :
				if(charjl.getX() > 0) {
					charjl.setLocation(charjl.getX()-10, charjl.getY()); break;
				}
			case KeyEvent.VK_RIGHT :
				if(charjl.getX() < FRAME_WIDTH - charjl.getWidth())
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
				}//if
					
				
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
