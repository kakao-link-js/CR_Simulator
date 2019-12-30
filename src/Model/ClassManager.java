package Model;

import Controller.*;
import View.*;
import org.json.simple.JSONObject;

/**
 * 싱글턴 기법 ClassManagement
 * 사용법 : ClassManagement.GetInstance().~
 */
public class ClassManager {
	private static ClassManager s_instance;

	// Main
	private ClassRegistrationSimulator m_Main;

	//Controller
	private DAO m_DAO;

	// View
	private MainMenuView m_MainMenuView;
	private LectureListView m_lectureListView;
	private TimetableView m_TimetableView;
	private CalculatorView m_CalculatorView;
	private FilterView m_FilterView;
	private LoginView m_LoginView;
	private SignUpView m_SignUpView;

	public static ClassManager getInstance() {
		if (s_instance == null) s_instance = new ClassManager();
		return s_instance;
	}

	// Main
	public ClassRegistrationSimulator getMain() {
		if (m_Main == null) m_Main = new ClassRegistrationSimulator();
		return m_Main;
	}

	public CalculatorView getCalculatorView() {
		if (m_CalculatorView == null) m_CalculatorView = new CalculatorView();
		return m_CalculatorView;
	}

	// View
	public MainMenuView getMainMenuView() {
		if (m_MainMenuView == null) m_MainMenuView = new MainMenuView();
		return m_MainMenuView;
	}

	public LectureListView getLectureListView(JSONObject jsonObject) {
		if (m_lectureListView == null) m_lectureListView = new LectureListView();
		m_lectureListView.setLectureList(jsonObject);
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

	public DAO getDAO(){
		if(m_DAO == null) m_DAO = new DAO();
		return m_DAO;
	}

	public LoginView getLoginView() {
		if(m_LoginView == null) m_LoginView = new LoginView();
		return m_LoginView;
	}

	public SignUpView getSignUpView() {
		if(m_SignUpView == null) m_SignUpView = new SignUpView();
		return m_SignUpView;
	}
}
