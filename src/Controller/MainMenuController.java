package Controller;

import View.*;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import Model.ClassManager;
import common.DesignConstants;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class MainMenuController {
	private JFileChooser fileChooser;

	//생성자를 통해 리스너 연결
	public MainMenuController() {
		getMainMenuView().addMenuButtonListener(new MenuButtonsListener());
		initFileChooser();
	} // Constructor

	//file을 주소로 연결하는 구성 메소드
	private void initFileChooser() {
		fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.addChoosableFileFilter(new FileFilter() {
			@Override
			public boolean accept(File f) {
				return f.getName().endsWith(".xlsx");
			}

			@Override
			public String getDescription() {
				return null;
			}
		});
	} // initFileChooser()

	//MainView로 돌아가는 메소드
	public void comeToMain() {
		ClassManager.getInstance().getMain().changePanel(getMainMenuView());
	} // comeToMain()

	//MainMenuView를 주는 메소드
	public MainMenuView getMainMenuView() {
		return ClassManager.getInstance().getMainMenuView();
	} // getMainMenuView()

	private class MenuButtonsListener implements ActionListener, MouseListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btnSelected = (JButton)e.getSource();
			JPanel selectedView = null;

			switch (btnSelected.getText()) {
				case "수강신청":
					selectedView = ClassManager.getInstance().getRealFilterController().getRealFilterView();
					break;
				case "관심과목":
					selectedView = ClassManager.getInstance().getInterestedFilterController().getInterestedFilterView();
					break;
				case "내 시간표":
					selectedView = ClassManager.getInstance().getTimetableController().getTimetableView();
					break;
				case "학점 계산기":
					selectedView = ClassManager.getInstance().getCalculatorController().getCalculatorPanelView();
					break;
				case "종료":
					System.exit(0);
					break;
			}
			ClassManager.getInstance().getMain().changePanel(selectedView);
		} // actionPerformed()

		@Override
		public void mouseEntered(MouseEvent e) {
			JButton btnEvent = (JButton)e.getSource();
			btnEvent.setForeground(new Color(DesignConstants.HOVERING_COLOR));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			JButton btnEvent = (JButton)e.getSource();
			btnEvent.setForeground(new Color(DesignConstants.SIGNATURE_COLOR));
		}
 
		@Override
		public void mouseClicked(MouseEvent e) { }
		@Override
		public void mousePressed(MouseEvent e) { }
		@Override
		public void mouseReleased(MouseEvent e) {
		}
	} // MenuButtonListener Class
} // MainMenuController Class
