package com.example.calculator;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class HelloController {
    @FXML
    Label drawGame;
    private int counterOfO = 0;
    private int counterOfX = 0;
    private char nowSym = 'X';
    @FXML
    Button a;
    @FXML
    Button b;
    @FXML
    Button c;
    @FXML
    Button d;
    @FXML
    Button e;
    @FXML
    Button f;
    @FXML
    Button g;
    @FXML
    Button h;
    @FXML
    Button i;
    @FXML
    Label winner;
    @FXML
    Label scoreOfX;
    @FXML
    Label scoreOfO;

    private char gameField[][] = new char[3][3];
    @FXML

    private boolean flag = false;
    @FXML
    void buttonClick(ActionEvent event) {
        boolean flagFull = false;
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                if (gameField[j][k] != 'X' || gameField[j][k] != 'O'){
                        flagFull = false;
                        break;
                }else flagFull = true;
            }
        }
        if (!flag && flagFull){
            restartGame();
            FadeTransition fd = new FadeTransition(Duration.millis(1000), drawGame);
            fd.setFromValue(0);
            fd.setToValue(1);

            fd.play();
        }

        Button btn = (Button)event.getSource();
        btn.setText(String.valueOf(nowSym));

        String checkStrX = "XXX";
        String checkStrO = "OOO";

        for (int i = 0; i < gameField.length; i++) {
            String check = "";
            for (int j = 0; j < gameField[i].length; j++) {
                check += gameField[i][j];
            }
            if (check.equals(checkStrX)){
                flag = true;
                counterOfX++;
                winner.setText("X wins!");
                winner.setOpacity(1.0);
            }if (checkStrO.equals(check)){
                flag = true;
                counterOfO++;
                winner.setText("O wins!");
                winner.setOpacity(1.0);

            }
        }
        String checkDiag1 = "";
        String checkDiag2 = "";
        for (int i = 0; i < gameField.length; i++) {

            for (int j = 0; j < gameField[i].length; j++) {

                if (i == j)checkDiag1 += gameField[i][j];
                if (i + j + 1 == 3) checkDiag2 += gameField[i][j];
            }

        }
        if (checkDiag1.equals(checkStrX) || checkDiag2.equals(checkStrX)){
            flag = true;
            counterOfX++;
            winner.setText("X wins!");
            winner.setOpacity(1.0);
        }if (checkStrO.equals(checkDiag1) || checkStrO.equals(checkDiag2)){
            flag = true;
            counterOfO++;
            winner.setText("O wins!");
            winner.setOpacity(1.0);

        }
        for (int i = 0; i < 3; i++) {

            String check = "";
            for (int j = 0; j < 3; j++) {
                check += gameField[j][i];
            }
            if (check.equals(checkStrX)){
                flag = true;
                counterOfX++;
                winner.setText("X wins!");
                winner.setOpacity(1.0);
            }if (checkStrO.equals(check)){
                flag = true;
                counterOfO++;
                winner.setText("O wins!");
                winner.setOpacity(1.0);

            }

        }




        int rowIndex = GridPane.getRowIndex(btn) == null ? 0 : GridPane.getRowIndex(btn);
        int columnIndex = GridPane.getColumnIndex(btn) == null ? 0 : GridPane.getColumnIndex(btn);

        System.out.println(rowIndex + " " + columnIndex);
        gameField[rowIndex][columnIndex] = nowSym;
        nowSym = nowSym == 'X' ? 'O' : 'X';



    }
    public void restartGame() {
        // Убедитесь, что все кнопки инициализированы
        if (a != null) a.setText("");
        if (b != null) b.setText("");
        if (c != null) c.setText("");
        if (d != null) d.setText("");
        if (e != null) e.setText("");
        if (f != null) f.setText("");
        if (g != null) g.setText("");
        if (h != null) h.setText("");
        if (i != null) i.setText("");

        // Сброс состояния игрового поля
        gameField = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};

        // Обновление счета
        scoreOfX.setText(String.valueOf(counterOfX));
        scoreOfO.setText(String.valueOf(counterOfO));

        // Сброс флага
        flag = false;
        winner.setOpacity(0);
    }



}
