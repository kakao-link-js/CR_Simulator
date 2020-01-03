package Controller;

import View.*;
import javax.swing.*;
import common.ClassManager;
import common.*;
import java.awt.*;
import java.awt.event.*;

/**
 * MainMenuController Class
 * 메인메뉴 Class로 여러 View로 이동을 담당한다.
 */

public class MainMenuController implements ActionListener, MouseListener {
	MainMenuView menuView;

	public MainMenuController(MainMenuView MMV) {
		this.menuView = MMV;
	} // Constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btnSelected = (JButton)e.getSource();
		JPanel selectedView = null;

		switch (btnSelected.getText()) {
			case Constants.INSERT_TXT:
				selectedView = ClassManager.getInstance().getFilterView();
				break;
			case Constants.MODIFY_TXT:
				selectedView = ClassManager.getInstance().getStateModifyView();
				break;
			case Constants.MYLECTURE_TXT:
				selectedView = ClassManager.getInstance().getTimetableView();
				break;
			case Constants.CALCUL_TXT:
				selectedView = ClassManager.getInstance().getCalculatorView();
				break;
			case Constants.LOGOUT_TXT:
				selectedView = ClassManager.getInstance().getLoginView();
				break;
		}
		ClassManager.getInstance().getMain().changePanel(selectedView);
	}

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
	public void mouseClicked(MouseEvent e) {	}

	@Override
	public void mousePressed(MouseEvent e) {	}

	@Override
	public void mouseReleased(MouseEvent e) {	}
} // MainMenuController Class







