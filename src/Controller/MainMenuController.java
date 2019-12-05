package Controller;

import View.*;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import Model.ClassManager;
import common.FontConstants;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class MainMenuController {
	private JFileChooser fileChooser;
	private String strXlsxFilePath;

	public MainMenuController() {
		getMainMenuView().addMenuButtonListener(new MenuButtonsListener());
		getMainMenuView().addBrowseButtonListener(new BrowseButtonListener());

		initFileChooser();
	} // Constructor

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

	public void comeToMain() {
		ClassManager.getInstance().getMain().changePanel(getMainMenuView());
	} // comeToMain()

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
			btnEvent.setForeground(new Color(FontConstants.HOVERING_COLOR));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			JButton btnEvent = (JButton)e.getSource();
			btnEvent.setForeground(new Color(FontConstants.SIGNATURE_COLOR));
		}

		@Override
		public void mouseClicked(MouseEvent e) { }
		@Override
		public void mousePressed(MouseEvent e) { }
		@Override
		public void mouseReleased(MouseEvent e) {
		}
	} // MenuButtonListener Class

	private class BrowseButtonListener implements ActionListener, MouseListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (fileChooser.showOpenDialog(getMainMenuView()) == JFileChooser.APPROVE_OPTION) {
				strXlsxFilePath = fileChooser.getSelectedFile().getAbsolutePath();
				getMainMenuView().getTxtFilePath().setText(strXlsxFilePath);

				TimetableParser parser = new TimetableParser(strXlsxFilePath);
				getMainMenuView().getTxtFilePath().setFont(new Font(FontConstants.ENGLISH_CASUAL_FONT, Font.PLAIN, 12));

				try {
					parser.parseXlsxFile();
					getMainMenuView().getTxtFilePath().setForeground(Color.BLACK);
					getMainMenuView().setEnabledAllButton(true);
				} catch (XlsxParseException ex) {
					getMainMenuView().getTxtFilePath().setForeground(Color.RED);
					JOptionPane.showMessageDialog(getMainMenuView(), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} // try - catch
			} // if
		} // actionPerformed()

		@Override
		public void mouseEntered(MouseEvent e) {
			JButton btnEvent = (JButton)e.getSource();
			btnEvent.setForeground(new Color(FontConstants.HOVERING_COLOR));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			JButton btnEvent = (JButton)e.getSource();
			btnEvent.setForeground(new Color(FontConstants.SIGNATURE_COLOR));
		}

		@Override
		public void mouseClicked(MouseEvent e) { }
		@Override
		public void mousePressed(MouseEvent e) { }
		@Override
		public void mouseReleased(MouseEvent e) {
		}
	} // BrowseButtonListener Class
} // MainMenuController Class
