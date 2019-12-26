package Controller;

import View.*;
import javax.swing.*;
import Model.ClassManager;
import common.*;
import java.awt.*;
import java.awt.event.*;


public class MainMenuController implements ActionListener, MouseListener {
	MainMenuView MMV;

	public MainMenuController(MainMenuView MMV) {
		this.MMV = MMV;
	} // Constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btnSelected = (JButton)e.getSource();
		JPanel selectedView = null;

		switch (btnSelected.getText()) {
			case Constants.INSERT_TXT:
				selectedView = ClassManager.getInstance().getRealFilterController().getRealFilterView();
				break;
			case Constants.INTERESTED_TXT:
				selectedView = ClassManager.getInstance().getInterestedFilterController().getInterestedFilterView();
				break;
			case Constants.MYLECTURE_TXT:
				selectedView = ClassManager.getInstance().getTimetableController().getTimetableView();
				break;
			case Constants.CALCUL_TXT:
				selectedView = ClassManager.getInstance().getCalculatorView();
				break;
			case Constants.END_TXT:
				System.exit(0);
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







