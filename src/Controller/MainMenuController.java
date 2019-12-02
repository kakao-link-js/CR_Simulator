package Controller;

import View.MainMenuView;
import View.RealFilterView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuController {

	static MainMenuView mainMenu ;

	public MainMenuController() {
		mainMenu = new MainMenuView(this);
		mainMenu.addMenuButtonListener(new MenuButtonListener());
		mainMenu.addBrowseButtonListener(new BrowseButtonListener());
	}

	public MainMenuView getMainMenuView() {return mainMenu;}

	private class MenuButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();

			for (int i = 0; i < 5; i++ ) {
				if (obj == mainMenu.btnMenu[i]) {
					switch (i) {
						case 0:

							break;
						case 1:
							// 관심과목 창으로 이동
							break;
						case 2:
							// 내 시간표 창으로 이동
							break;
						case 3:
							// 학점 계산기 창으로 이동
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
		JFrame frame = new JFrame("Class Registration Simulator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		MainMenuController controller = new MainMenuController();
		frame.getContentPane().add(controller.getMainMenuView());

		frame.pack();
		frame.setVisible(true);
	}

}
