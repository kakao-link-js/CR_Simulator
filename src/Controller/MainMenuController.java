package Controller;

import View.*;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import Model.ClassManager;

import java.awt.event.*;
import java.io.File;

public class MainMenuController {
	private JFileChooser fileChooser;
	private String strXlsxFilePath;

	public MainMenuController() {
		getMainMenuView().addMenuButtonListener(new MenuButtonListener());
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

	private class MenuButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			JPanel selectedView = null;

			for (int i = 0; i < 5; i++ ) {
				if (obj == getMainMenuView().btnMenus[i]) {
					switch (i) {
						case 0: //수강신청
							selectedView = ClassManager.getInstance().getRealFilterController().getRealFilterView();
							break;
						case 1:
							selectedView = ClassManager.getInstance().getInterestedFilterController().getInterestedFilterView();
							break;
						case 2: //시간표
							selectedView = ClassManager.getInstance().getTimetableController().getTimetableView();
							break;
						case 3: // 학점 계산기 창으로 이동
							selectedView = ClassManager.getInstance().getCalculatorController().getCalculatorPanelView();
							break;
						case 4:
							System.exit(0);
							break;
					} // switch

					ClassManager.getInstance().getMain().changePanel(selectedView);
					break;
				} // if
			} // for
		} // actionPerformed()
	} // MenuButtonListener Class

	private class BrowseButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (fileChooser.showOpenDialog(getMainMenuView()) == JFileChooser.APPROVE_OPTION) {
				strXlsxFilePath = fileChooser.getSelectedFile().getAbsolutePath();
				getMainMenuView().lblPath.setText("PATH : " + strXlsxFilePath);

				TimetableParser parser = new TimetableParser(strXlsxFilePath);

				try {
					parser.parseXlsxFile();
					getMainMenuView().setEnabledAllButton(true);
				} catch (XlsxParseException ex) {
					JOptionPane.showMessageDialog(getMainMenuView(), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} // try - catch
			} // if
		} // actionPerformed()
	} // BrowseButtonListener Class
} // MainMenuController Class
