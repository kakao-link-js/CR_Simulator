package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.ClassManager;
import View.*;
public class CalculatorController {

    CalculatorPanelView CPV;

    public CalculatorController() {
        CPV= new CalculatorPanelView(this);
    }

    public CalculatorPanelView getCalculatorPanelView() {
        return CPV;
    }

    public ActionListener connectActionListener(){
        return new ButtonListener();
    }

    private class ButtonListener implements ActionListener {

        private double score, grade, totalScore = 0, resultScore, cntScore = 0;
        private void ChangeGrade(String s) {

            switch(s) {
                case "A+" :
                    grade = 4.5;
                    break;
                case "A0" :
                    grade = 4.0;
                    break;
                case"B+":
                    grade = 3.5;
                    break;
                case "B0":
                    grade = 3.0;
                    break;
                case "C+":
                    grade = 2.5;
                    break;
                case "C0":
                    grade = 2.0;
                    break;
                case "D+":
                    grade = 1.5;
                    break;
                case "D0":
                    grade = 1.0;
                    break;
                case "F" :
                    grade = 0.0;
                    break;
            }

        }// ChangeGrade()
        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();

            if (CPV.isBtnCalcul(obj)) {
                for (int i = 0; i < 8; i++) {

                    if (CPV.comboArr.get(i).getSelectedItem() != "선택안함" && CPV.scoreArr.get(i).getText() != null
                            && CPV.subjectArr.get(i).getText()!= null)
                    {
                        score = Double.parseDouble(CPV.scoreArr.get(i).getText());
                        ChangeGrade((String) CPV.comboArr.get(i).getSelectedItem());
                        cntScore += score;
                        totalScore += score * grade;

                    } // if
                } // for

                resultScore = totalScore / cntScore;
                resultScore = Math.round(resultScore * 100)/100.0;
                CPV.setScore(Double.toString(resultScore));
            }// if btnCalcul
            else { //나가기
            	ClassManager.getInstance().getMainMenuController().comeToMain();
            }
        } // actionPerformed()
    } // ButtonListener class
}
