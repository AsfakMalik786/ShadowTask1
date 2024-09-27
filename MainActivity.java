package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView display;
    private String currentInput = "";
    private String operator = "";
    private double firstOperand = 0.0;
    private double secondOperand = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        int[] buttonIds = {R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9,
                R.id.buttonAdd, R.id.buttonSubtract, R.id.buttonMultiply, R.id.buttonDivide,
                R.id.buttonClear, R.id.buttonEquals};

        for (int id : buttonIds) {
            findViewById(id).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "C":
                clear();
                break;
            case "=":
                calculate();
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                setOperator(buttonText);
                break;
            default:
                appendNumber(buttonText);
                break;
        }
    }

    private void clear() {
        currentInput = "";
        operator = "";
        firstOperand = 0.0;
        secondOperand = 0.0;
        display.setText("0");
    }

    private void setOperator(String operator) {
        if (!currentInput.isEmpty()) {
            firstOperand = Double.parseDouble(currentInput);
            currentInput = "";
            this.operator = operator;
        }
    }

    private void appendNumber(String number) {
        currentInput += number;
        display.setText(currentInput);
    }

    private void calculate() {
        if (!currentInput.isEmpty()) {
            secondOperand  = Double.parseDouble(currentInput);

            double result = 0.0;
            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand ;
                    break;
                case "-":
                    result = firstOperand - secondOperand ;
                    break;
                case "*":
                    result = firstOperand * secondOperand ;
                    break;
                case "/":
                    result = firstOperand / secondOperand;
                    break;
            }

            display.setText(String.valueOf(result));
            currentInput = String.valueOf(result);
            operator = "";
        }
    }
}
