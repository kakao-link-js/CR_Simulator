package Model;

import java.util.*;

import Controller.*;
import View.*;

/**
 * 싱글턴 기법 ClassManagement
 * 사용법 : ClassManagement.GetInstance().~
 */
public class ClassManager {
	private static ClassManager s_instance;

	// Main
	private ClassRegistrationSimulator m_Main;

	// Item list
	private ArrayList<LectureDTO> m_lectureList;
	private ArrayList<LectureDTO> m_realList;
	private ArrayList<LectureDTO> m_interestedList;

	// Controller
	private MainMenuController m_MainMenuController;
	private RealFilterController m_RealFilterController;
	private InterestedFilterController m_InterestedFilterController;
	private LectureListController m_lectureListController;
	private TimetableController m_TimetableController;

	// View
	private MainMenuView m_MainMenuView;
	private LectureListView m_lectureListView;
	private TimetableView m_TimetableView;
	private CalculatorPanelView m_CalculatorView;

	public static ClassManager getInstance() {
		if (s_instance == null) s_instance = new ClassManager();
		return s_instance;
	}

	// Main
	public ClassRegistrationSimulator getMain() {
		if (m_Main == null) m_Main = new ClassRegistrationSimulator();
		return m_Main;
	}

	// Item list
	public ArrayList<LectureDTO> getLecture() {
		if (m_lectureList == null) m_lectureList = new ArrayList<LectureDTO>();
		return m_lectureList;
	}
	public ArrayList<LectureDTO> getReal() {
		if (m_realList == null) m_realList = new ArrayList<LectureDTO>();
		return m_realList;
	}
	public ArrayList<LectureDTO> getInterested() {
		if (m_interestedList == null) m_interestedList = new ArrayList<LectureDTO>();
		return m_interestedList;
	}

	// Controller
	public MainMenuController getMainMenuController() {
		if (m_MainMenuController == null) m_MainMenuController = new MainMenuController();
		return m_MainMenuController;
	}

	public RealFilterController getRealFilterController() {
		if (m_RealFilterController == null) m_RealFilterController = new RealFilterController();
		return m_RealFilterController;
	}

	public InterestedFilterController getInterestedFilterController() {
		if (m_InterestedFilterController == null) m_InterestedFilterController = new InterestedFilterController();
		return m_InterestedFilterController;
	}

	public LectureListController getLectureListController() {
		if (m_lectureListController == null) m_lectureListController = new LectureListController();
		return m_lectureListController;
	}

	public TimetableController getTimetableController() {
		if (m_TimetableController == null) m_TimetableController = new TimetableController();
		return m_TimetableController;
	}

	public CalculatorPanelView getCalculatorView() {
		if (m_CalculatorView == null) m_CalculatorView = new CalculatorPanelView();
		return m_CalculatorView;
	}

	// View
	public MainMenuView getMainMenuView() {
		if (m_MainMenuView == null) m_MainMenuView = new MainMenuView();
		return m_MainMenuView;
	}

	public LectureListView getLectureListView() {
		if (m_lectureListView == null) m_lectureListView = new LectureListView(m_lectureListController);
		return m_lectureListView;
	}

	public TimetableView getTimetableView() {
		if (m_TimetableView == null) m_TimetableView = new TimetableView();
		return m_TimetableView;
	}
}
