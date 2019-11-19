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
			LectureVO new1 = new LectureVO("개설대학","개설학과전공","학수번호","분반","글쓰기와춤추기","이수구분","1","3","월 09:00~11:00","광101","홍길동");
			ClassManagement.getInstance().getLecture().add(new1);
			LectureVO new2 = new LectureVO("개설대학","개설학과전공","학수번호","분반","글쓰기와춤추기","이수구분","1","3","월 09:00~11:00","광101","홍길동");
			ClassManagement.getInstance().getLecture().add(new2);
			LectureVO new3 = new LectureVO("개설대학","개설학과전공","학수번호","분반","글쓰기와춤추기","이수구분","1","3","월 09:00~11:00","광101","홍길동");
			ClassManagement.getInstance().getLecture().add(new3);
			LectureVO new4 = new LectureVO("개설대학","개설학과전공","학수번호","분반","글쓰기와춤추기","이수구분","1","3","월 09:00~11:00","광101","홍길동");
			ClassManagement.getInstance().getLecture().add(new4);
			LectureVO new5 = new LectureVO("개설대학","개설학과전공","학수번호","분반","글쓰기와춤추기","이수구분","1","3","월 09:00~11:00","광101","홍길동");
			ClassManagement.getInstance().getLecture().add(new5);
			LectureVO new6 = new LectureVO("개설대학","개설학과전공","학수번호","분반","글쓰기와춤추기","이수구분","1","3","월 09:00~11:00","광101","홍길동");
			ClassManagement.getInstance().getLecture().add(new6);
			LectureVO new7 = new LectureVO("개설대학","개설학과전공","학수번호","분반","글쓰기와춤추기","이수구분","1","3","월 09:00~11:00","광101","홍길동");
			ClassManagement.getInstance().getLecture().add(new7);
			LectureVO new8 = new LectureVO("개설대학","개설학과전공","학수번호","분반","글쓰기와춤추기","이수구분","1","3","월 09:00~11:00","광101","홍길동");
			ClassManagement.getInstance().getLecture().add(new8);
			LectureVO new9 = new LectureVO("개설대학","개설학과전공","학수번호","분반","글쓰기와춤추기","이수구분","1","3","월 09:00~11:00","광101","홍길동");
			ClassManagement.getInstance().getLecture().add(new9);
			LectureVO new10 = new LectureVO("개설대학","개설학과전공","학수번호","분반","글쓰기와춤추기","이수구분","1","3","월 09:00~11:00","광101","홍길동");
			ClassManagement.getInstance().getLecture().add(new10);
		}

}
