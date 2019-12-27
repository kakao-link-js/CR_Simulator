package Model;

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

	// Controller
	private LectureListController m_lectureListController;
	private TimetableController m_TimetableController;

	// View
	private MainMenuView m_MainMenuView;
	private LectureListView m_lectureListView;
	private TimetableView m_TimetableView;
	private CalculatorPanelView m_CalculatorView;
	private FilterView m_FilterView;

	public static ClassManager getInstance() {
		if (s_instance == null) s_instance = new ClassManager();
		return s_instance;
	}

	// Main
	public ClassRegistrationSimulator getMain() {
		if (m_Main == null) m_Main = new ClassRegistrationSimulator();
		return m_Main;
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

	public FilterView getFilterView() {
		if(m_FilterView == null) m_FilterView = new FilterView();
		return m_FilterView;
	}

}
