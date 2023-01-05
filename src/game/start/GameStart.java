package game.start;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.view.GameView;

public class GameStart extends JFrame {
	
	public GameStart() {
		setTitle("똥 피하기 게임");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		
		JPanel jp = new JPanel();
		jp.setLayout(null);
		ImageIcon icon = new ImageIcon("images/background.jpg");
		JLabel background = new JLabel(icon);
		jp.add(background);
		
		JButton start = new JButton("게 임 시 작");
		start.setBounds(380, 300, 200, 50);
		start.setFont(new Font("한컴 고딕", Font.BOLD, 20));
		start.addActionListener(jbL);
		
		JButton way = new JButton("게 임 방 법");
		way.setBounds(380, 360, 200, 50);
		way.setFont(new Font("한컴 고딕", Font.BOLD, 20));
		way.addActionListener(jbL);
		
		add(way);
		add(start);
		add(jp);
	}
	
	ActionListener jbL = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()) {
				case "게 임 시 작" : 
					GameView view = new GameView();
					view.setVisible(true);
					setVisible(false);
					break;
				case "게 임 방 법" :
					GameWay way = new GameWay();
					way.setVisible(true);
					setVisible(false);
					break;
			}//switch
			
		}
		
	};

	public static void main(String[] args) {
		new GameStart();
	}

}
