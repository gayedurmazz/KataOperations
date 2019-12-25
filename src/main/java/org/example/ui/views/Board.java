package org.example.ui.views;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.example.operation.Operations;
import org.example.ui.components.MyButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board extends VerticalLayout {

    private HorizontalLayout horizontalLayout;
    private int rowCount;
    private int columnCount;
    private MyButton[][] myButtons;
    private List<String> operationsList;

    public Board(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;

        myButtons = new MyButton[rowCount][columnCount];
        createBoard(myButtons);
    }

    private void createBoard(MyButton[][] myButtons) {
        Random random = new Random();
        int valueBtn;

        createOperationList();

        for (int i = 0; i < myButtons.length; i++) {
            horizontalLayout = new HorizontalLayout();
            for (int j = 0; j < myButtons[i].length; j++) {
                myButtons[i][j] = new MyButton(" ");

                // Sayı değeri atanacak butonlar
                if(j > 0 && j <6 && i > 0 && i <6){
                    valueBtn = random.nextInt(20)-10;
                    myButtons[i][j].setData(valueBtn);
                    myButtons[i][j].setCaption(String.valueOf(valueBtn));
                }
                horizontalLayout.addComponent(myButtons[i][j]);
                horizontalLayout.setSpacing(true);
            }

            setSpacing(true);
            addComponent(horizontalLayout);
        }
        myButtons[0][0].setIcon(FontAwesome.SMILE_O);
        myButtons[6][6].setIcon(FontAwesome.SMILE_O);
        buttonClicked();
    }

    private void buttonClicked() {
        double [] numbers = new double[5];

        // Üst satır işlem butonları
        int listIndex = 0;

        for (int i = 1; i < 6 ; i++) {
            int finalI = i;
            int finalI1 = i;
            myButtons[0][i].setCaption(operationsList.get(listIndex));
            myButtons[0][i].setStyleName(ValoTheme.BUTTON_PRIMARY);
            myButtons[0][i].addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    int clickedColumn = finalI1;
                    String operationName;
                    operationName = myButtons[0][finalI].getCaption();
                    for (int j = 1; j < 6 ; j++) {
                        numbers[j - 1] = Double.parseDouble(myButtons[j][clickedColumn].getData().toString());
                    }
                    double answer = doOperations(operationName, numbers);
                    myButtons[6][clickedColumn].setData(answer);
                    myButtons[6][clickedColumn].setCaption(String.valueOf(answer));
                }
            });
            listIndex++;
        }
        listIndex = 0;
        myButtons[0][6].setCaption("ANS");
        myButtons[0][6].setStyleName(ValoTheme.BUTTON_FRIENDLY);

        // Sağ sütun işlem butonları
        for (int i = 1; i < 6 ; i++) {
            int finalI = i;
            int finalI1 = i;
            myButtons[i][0].setCaption(operationsList.get(listIndex));
            myButtons[i][0].setStyleName(ValoTheme.BUTTON_PRIMARY);
            myButtons[i][0].addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    int clickedRow = finalI1;
                    String operationName;
                    operationName = myButtons[0][finalI].getCaption();
                    for (int j = 1; j < 6 ; j++) {
                        numbers[j - 1] = Double.parseDouble(myButtons[clickedRow][j].getData().toString());
                    }
                    double answer = doOperations(operationName, numbers);
                    myButtons[clickedRow][6].setData(answer);
                    myButtons[clickedRow][6].setCaption(String.valueOf(answer));
                }
            });
            listIndex++;
        }
        myButtons[6][0].setCaption("ANS");
        myButtons[6][0].setStyleName(ValoTheme.BUTTON_FRIENDLY);

    }

    private double doOperations(String operationName, double[] numbers) {
        Operations operations = new Operations();
        double answer;
        switch (operationName){
            case "/":
                answer = operations.divide(numbers);
                break;
            case "*":
                answer = operations.multiply(numbers);
                break;

            case "-":
                answer = operations.subtract(numbers);
                break;

            case "+":
                answer = operations.add(numbers);
                break;

            case "%":
                answer = operations.module(numbers);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + operationName);
        }
        return answer;
    }

    private void createOperationList() {
        operationsList = new ArrayList<>();
        operationsList.add("/");
        operationsList.add("*");
        operationsList.add("-");
        operationsList.add("+");
        operationsList.add("%");
    }
}
