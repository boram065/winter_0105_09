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
	ImageIcon icon = new ImageIcon("images/background.jpg");
	
	public GameStart() {
		setTitle("똥 피하기 게임");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	
		JLabel jl = new JLabel(icon);
		add(jl);
		
		JButton start = new JButton("게 임 시 작");
		start.setBounds(250, 350, 200, 50);
		start.setFont(new Font("한컴 고딕", Font.BOLD, 20));
		start.addActionListener(jbL);
		
		JButton way = new JButton("게 임 방 법");
		way.setBounds(550, 350, 200, 50);
		way.setFont(new Font("한컴 고딕", Font.BOLD, 20));
		way.addActionListener(jbL);
		
		add(way);
		add(start);
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
