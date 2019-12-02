package Model;

import java.util.*;

import Controller.*;
import View.*;

// 싱글턴 기법을 위한 ClassManagement
public class ClassManager {
	//모든 곳에서 같은 객체를 공유해야 할 경우에는 여기다가 객체를 만들어 넣고 사용하면 됩니다.
    //객체를 불러올때에는 ClassManagement.GetInstance().~~~
	
	private static ClassManager s_instance;
	private ArrayList<LectureVO> m_interestedList;
	private ArrayList<LectureVO> m_realList;
	private ArrayList<LectureVO> m_lectureList;
	private LectureListController m_lectureListController;
	private LectureListView m_lectureListView;
	private MainMenuController m_MainMenuController;
	private RealFilterController m_RealFilterController;
	private InterestedFilterController m_InterestedFilterController;
	

	public static ClassManager getInstance() {
		if (s_instance == null) s_instance = new ClassManager();
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
	public ArrayList<LectureVO> getLecture(){
		if(m_lectureList == null) m_lectureList = new ArrayList<LectureVO>();
		return m_lectureList;
	}
	
	public LectureListController getLectureListController() {
		if(m_lectureListController == null) m_lectureListController = new LectureListController();
		return m_lectureListController;
	}
	
	public LectureListView getLectureListView(boolean flag) {
		if(m_lectureListView == null) m_lectureListView = new LectureListView(m_lectureListController);
		m_lectureListView.isFavorite = flag;
		return m_lectureListView;
	}
	
	public LectureListView getLectureListView() {
		if(m_lectureListView == null) m_lectureListView = new LectureListView(m_lectureListController);
		return m_lectureListView;
	}
	
	public MainMenuController getMainMenuController() {
		if(m_MainMenuController == null) m_MainMenuController = new MainMenuController();
		return m_MainMenuController;
	}

	public RealFilterController getRealFilterController() {
		if(m_RealFilterController == null) m_RealFilterController = new RealFilterController();
		return m_RealFilterController;
	}

	public InterestedFilterController getInterestedFilterController() {
		if(m_InterestedFilterController == null) m_InterestedFilterController = new InterestedFilterController();
		return m_InterestedFilterController;
	}
}
