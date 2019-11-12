package Model;

import java.util.*;

// 싱글턴 기법을 위한 ClassManagement
public class ClassManagement {
	//모든 곳에서 같은 객체를 공유해야 할 경우에는 여기다가 객체를 만들어 넣고 사용하면 됩니다.
    //객체를 불러올때에는 ClassManagement.GetInstance().~~~
	
	private static ClassManagement s_instance;
	private ArrayList<LectureVO> m_interestedList;
	private ArrayList<LectureVO> m_realList;
	
	public static ClassManagement getInstance() {
		if (s_instance == null) s_instance = new ClassManagement();
		return s_instance;
	}
	
	public ArrayList<LectureVO> getInterested(){
		if(m_interestedList == null) m_interestedList = new ArrayList<LectureVO>();
		return m_interestedList;
	}
	
	public ArrayList<LectureVO> getReal(){
		if(m_realList == null) m_realList = new ArrayList<LectureVO>();
		return m_realList;
	}
}
