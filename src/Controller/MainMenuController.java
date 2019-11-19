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
			LectureVO new2 = new LectureVO("개설대학","컴퓨터공학과","101002","001","자료구조및 실습","이수구분","1","3","월 09:00~11:00","광101","홍길동");
			ClassManagement.getInstance().getLecture().add(new2);
			LectureVO new3 = new LectureVO("개설대학","소프트웨어학과","202495","001","확률과통계","이수구분","1","3","월 09:00~11:00","광101","홍길동");
			ClassManagement.getInstance().getLecture().add(new3);
			LectureVO new4 = new LectureVO("개설대학","지능기전공학부","406949","001","이산수학및 프로그래밍","이수구분","1","3","월 09:00~11:00","광101","홍길동");
			ClassManagement.getInstance().getLecture().add(new4);
			LectureVO new5 = new LectureVO("개설대학","컴퓨터공학과","004118","001","디지털시스템","전공필수","2","3","화 목 16:00~18:00","율305A","Rajendra Dhakal");
			ClassManagement.getInstance().getLecture().add(new5);
			LectureVO new6 = new LectureVO("국문학과","컴퓨터공학과","009913","002","고급C프로그래밍및실습","전공필수","1","4","화 목 13:30~15:00, 화 18:00~20:00","율401","김도년");
			ClassManagement.getInstance().getLecture().add(new6);
			LectureVO new7 = new LectureVO("나는야딴따라","컴퓨터공학과","009912","002","C프로그래밍및실습","전공필수","1","4","화 목 13:30~15:00, 화 18:00~20:00","율301","박태순");
			ClassManagement.getInstance().getLecture().add(new7);
			LectureVO new8 = new LectureVO("전자정보통신대학","컴퓨터공학과","010351","001","대학생활과진로설계","중핵필수","1","1","금 18:00~19:00","충B107","공성곤");
			ClassManagement.getInstance().getLecture().add(new8);
			LectureVO new9 = new LectureVO("소프트웨어융합대학","컴퓨터공학과","002505","002","인공지능","전공선택","4","3","월 수 13:30~15:00","율204","김용국");
			ClassManagement.getInstance().getLecture().add(new9);
			LectureVO new10 = new LectureVO("소프트웨어융합대학","디지털콘텐츠학과","009665","001","패턴인식","전공선택","4","3","화 목 15:00~16:30","광714","권순일");
			ClassManagement.getInstance().getLecture().add(new10);
		}

}
