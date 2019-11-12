package Controller;

import javax.swing.JFrame;

import View.*;

public class MainMenuController {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Up Down Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false); 
		
		LectureListView LLV = new LectureListView(true);
		

		frame.getContentPane().add(LLV);
		
		frame.pack();
		frame.setVisible(true);
	}

}
