package game.start;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.view.GameView;

public class GameWay extends JFrame{
	
	public GameWay() {
		setTitle("게임방법");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		
		JPanel jp = new JPanel();
		jp.setLayout(null);
		jp.setBackground(Color.WHITE);
		
		JLabel jl = new JLabel();
		jl.setFont(new Font("한컴 고딕", Font.BOLD, 25));
		jl.setText("<html><br><br><br><br>지금부터 게임을 시작하지."
				+ "<br><br>1. 방향키(왼쪽, 오른쪽)를 사용하여 사람을 움직인다."
				+ "<br>2. 사람을 움직이면서 똥을 피한다."
				+ "<br>3. 똥에 맞았을 경우 -10점"
				+ "<br>4. 보석을 맞았을 경우 +5점이다."
				+ "<br><br>너의 실력에 맡긴다. 그럼 이만..</html>");
		
		JButton start = new JButton("게 임 시 작");
		start.setBounds(740, 500, 200, 50);
		start.setFont(new Font("한컴 고딕", Font.BOLD, 20));
		start.addActionListener(jbL);
		
		jp.add(jl);
		jp.add(start);
		add(jp);
	}
	
	ActionListener jbL = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			GameView view = new GameView();
			view.setVisible(true);
			setVisible(false);
		}
	};

	public static void main(String[] args) {
		new GameWay();
	}

}
