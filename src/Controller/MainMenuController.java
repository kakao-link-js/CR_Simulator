package Controller;

import View.*;

import javax.swing.*;

import Model.ClassManager;

import java.awt.event.*;

public class MainMenuController {

	MainMenuView mainMenu;
	static JFrame frame;

	public MainMenuController() {
		mainMenu = new MainMenuView(this);
		mainMenu.addMenuButtonListener(new MenuButtonListener());
		mainMenu.addBrowseButtonListener(new BrowseButtonListener());
	}

	public MainMenuView getMainMenuView() {return mainMenu;}
	
	public void comeToMain() {
		changePanel(ClassManager.getInstance().getMainMenuController().getMainMenuView());
	}
	
	public void changePanel(JPanel View) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(View);
		frame.pack();
		frame.repaint();
	}

	private class MenuButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();

			for (int i = 0; i < 5; i++ ) {
				if (obj == mainMenu.btnMenu[i]) {
					switch (i) {
						case 0: //수강신청
							changePanel(ClassManager.getInstance().getRealFilterController().getRealFilterView());
							break;
						case 1:
							changePanel(ClassManager.getInstance().getInterestedFilterController().getInterestedFilterView());
							break;
						case 2: //시간표
							break;
						case 3: // 학점 계산기 창으로 이동
							changePanel(ClassManager.getInstance().getCalculatorController().getCalculatorPanelView());
							break;
						case 4:
							System.exit(0);
							break;
					} // switch
				} // if
			} // for
		} // actionPerformed
	} // MenuButtonListener

	private class BrowseButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {

			mainMenu.infoSelectedFile = mainMenu.excFileChooser.showOpenDialog(mainMenu);

			if (mainMenu.infoSelectedFile == JFileChooser.APPROVE_OPTION) {
				mainMenu.filePath = mainMenu.excFileChooser.getSelectedFile().getAbsolutePath();
				mainMenu.checkFile = true; // 파일을 가져왔을 때에만 다른 메뉴(종료 제외)를 선택할 수 있도록 true로 설정
				mainMenu.setEnabledAllButton(true);
			}
			mainMenu.lblPath.setText("PATH : " + mainMenu.filePath);
			mainMenu.pathPanel.add(mainMenu.lblPath);
		}
	}



	public static void main(String[] arg) {
		frame = new JFrame("Class Registration Simulator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		MainMenuController controller = ClassManager.getInstance().getMainMenuController();
		frame.getContentPane().add(controller.getMainMenuView());

		frame.pack();
		frame.setVisible(true);
	}

}
