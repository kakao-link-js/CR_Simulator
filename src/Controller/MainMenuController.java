package Controller;

import javax.swing.JFrame;

import Model.ClassManagement;
import Model.LectureVO;
import View.*;

public class MainMenuController {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Up Down Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false); 
		
		addTestData();
		
		LectureListController LLC = new LectureListController();
		

		frame.getContentPane().add(LLC.showLLV());
		
		frame.pack();
		frame.setVisible(true);
	}
	
	//테스트 데이터 생성용
		public static void addTestData() {
			LectureVO new1 = new LectureVO("0","0","0","0","0","0","0","1","0","0","0");
			ClassManagement.getInstance().getInterested().add(new1);
			LectureVO new2 = new LectureVO("0","0","0","0","0","0","0","2","0","0","0");
			ClassManagement.getInstance().getInterested().add(new2);
			LectureVO new3 = new LectureVO("0","0","0","0","0","0","0","3","0","0","0");
			ClassManagement.getInstance().getInterested().add(new3);
			LectureVO new4 = new LectureVO("0","0","0","0","0","0","0","4","0","0","0");
			ClassManagement.getInstance().getInterested().add(new4);
			LectureVO new5 = new LectureVO("0","0","0","0","0","0","0","5","0","0","0");
			ClassManagement.getInstance().getInterested().add(new5);
			LectureVO new6 = new LectureVO("0","0","0","0","0","0","0","6","0","0","0");
			ClassManagement.getInstance().getInterested().add(new6);
			LectureVO new7 = new LectureVO("0","0","0","0","0","0","0","7","0","0","0");
			ClassManagement.getInstance().getInterested().add(new7);
			LectureVO new8 = new LectureVO("0","0","0","0","0","0","0","8","0","0","0");
			ClassManagement.getInstance().getInterested().add(new8);
			LectureVO new9 = new LectureVO("0","0","0","0","0","0","0","9","0","0","0");
			ClassManagement.getInstance().getInterested().add(new9);
			LectureVO new10 = new LectureVO("0","0","0","0","0","0","0","0","0","0","0");
			ClassManagement.getInstance().getInterested().add(new10);
		}

}
