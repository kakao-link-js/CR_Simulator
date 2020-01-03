package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import common.ClassManager;
import View.*;

/**
 * CalculatorController Class
 * 학점계산 클래스의 비즈니스 로직을 갖고 있는 클래스 입니다.
 */

public class CalculatorController implements ActionListener {

    CalculatorView calculatorPanelView;

    public CalculatorController(CalculatorView CPV) {
        this.calculatorPanelView = CPV;
    } //public CalculatorController()

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        double score, totalScore = 0, resultScore, cntScore = 0;
        if (calculatorPanelView.isBtnCalcul(obj)) {
            for (int i = 0; i < 8; i++) {
                if (calculatorPanelView.isInserted(i)) { //넣을 수 있는 상황이라면 (빈칸이 없는 경우)
                    score = Double.parseDouble(calculatorPanelView.scoreArr.get(i).getText());
                    cntScore += score;
                    totalScore += score * ChangeGrade((String) calculatorPanelView.comboArr.get(i).getSelectedItem());;
                } // if
            } // for
            resultScore = totalScore / cntScore;// 평점 계산
            resultScore = Math.round(resultScore * 100)/100.0;
            calculatorPanelView.setScore(Double.toString(resultScore));
        }// if btnCalcul
        else { //나가기
            ClassManager.getInstance().getMain().comeToMain();
        }
    } //public void ActionPerformed(ActionEvent e)

    // 성적을 환산한느 클래스입니다.
    private double ChangeGrade(String s) {
        switch(s) { //성적을 구분한다.
            case "A+" : return 4.5;
            case "A0" : return 4.0;
            case "B+" : return 3.5;
            case "B0" : return 3.0;
            case "C+": return 2.5;
            case "C0": return 2.0;
            case "D+": return 1.5;
            case "D0": return 1.0;
            case "F" : return 0.0;
        }
        return 0;
    }// ChangeGrade(String s)
}
